package syric.dragonseeker.item.tool;

//import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import syric.dragonseeker.Dragonseeker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class dragonseekerTicking extends Item {

    public dragonseekerTicking(Properties prop) {
        super(prop);
    }

    public void inventoryTick(ItemStack stack, World world, Entity holdingEntity, int slot, boolean isSelected) {
        if (!world.isClientSide) {
            //If held in either hand:
            if (isSelected || (holdingEntity instanceof PlayerEntity && ((PlayerEntity) holdingEntity).getOffhandItem() == stack)) {
                CompoundNBT nbt = stack.getOrCreateTag();
                int cycleTicks = nbt.getInt("Ticks");
                if (cycleTicks > 0) {
                    nbt.putInt("Ticks", cycleTicks - 1);
                    stack.setTag(nbt);
                } else if (cycleTicks < 0) {
                    //Reset timer
                    nbt.putInt("Ticks", interval_ticks(false, 0));
                    stack.setTag(nbt);
                } else {

                //Check for dragons
                    double intensity = getIntensity(world, holdingEntity);
                    double activationChance = activation_chance(intensity);
                    Random random = new Random();
                    boolean activate = random.nextDouble() < activationChance;

                    if (activate) {
                        Dragonseeker.LOGGER.debug("Activated!");
                        //Reset timer
                        nbt.putInt("Ticks", interval_ticks(true, intensity));
                        stack.setTag(nbt);
                    } else {
                        Dragonseeker.LOGGER.debug("Not Activated");
                        //Reset timer
                        nbt.putInt("Ticks", interval_ticks(false, intensity));
                        stack.setTag(nbt);
                    }
                }

            //Make sure it doesn't keep playing the sound if you stop selecting it!
            //When not selected, ticks should go to -1
            } else {
                CompoundNBT nbt = stack.getOrCreateTag();
                nbt.putInt("Ticks", -1);
                stack.setTag(nbt);
            }
        }
    }

    //Calculates and sums the intensities of all nearby dragons
    private double getIntensity(World world, Entity player) {
        AxisAlignedBB box = new AxisAlignedBB(player.getX() - 300, -40, player.getZ() - 300, player.getX() + 300, 120, player.getZ() + 300);
//        List<EntityDragonBase> dragons = world.getEntitiesOfClass(EntityDragonBase.class, box);
        List<CreeperEntity> dragons = world.getEntitiesOfClass(CreeperEntity.class, box);

        int total = 0;

        for (CreeperEntity dragon : dragons) {
            float distance = dragon.distanceTo(player);
//            int age = dragon.getDragonStage();
            int age = 4;

            double base_intensity = 75000 * Math.pow(2, age);
            double adjusted_intensity = base_intensity / Math.pow(distance, 2);
            double final_intensity = adjusted_intensity - Math.pow(2, age);
            total += (final_intensity > 0) ? final_intensity : 0;
        }
        Dragonseeker.LOGGER.debug("Detected " + dragons.size() + " dragon(s) (creepers). Total intensity: " + total);
        return total;
    }

    //Gets the activation chance for a given intensity
    private double base_activation_chance(double intensity) {
        if (intensity < 0) {
            intensity = 0;
        }
        if (intensity > 100) {
            intensity = 100;
        }

        return 0.0055 * intensity + 0.05;
    }

    private double activation_chance(double intensity) {
        Dragonseeker.LOGGER.debug("For intensity " + intensity + ", activation chance is " + base_activation_chance(intensity));
        return base_activation_chance(intensity);
    }

    //Find the length of the next interval
    private int interval_ticks(boolean activated, double intensity) {
        int[] rolls = new int[6];
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            rolls[i] = random.nextInt(20) + 1;
        }
        Arrays.sort(rolls);
        int sum = rolls[0] + rolls[1];
        double multiplied = sum * 0.75;

        //if low signal strength and activated, reduce duration.
        if (activated && intensity < 10) {
            intensity = Math.max(intensity, 0);
            double multiplier = intensity / 10;
            multiplier = Math.max(multiplier, 0.5);
            multiplied *= multiplier;
        }

        Dragonseeker.LOGGER.debug("new interval for basic dragonseeker: " + (int) Math.round(multiplied) + " ticks");
        return (int) Math.round(multiplied);
    }

}
