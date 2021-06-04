package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class OldEpicDragonseekerItem extends Item {

    //Defining statistics
    private int opDist = 125;
    private int maxDist = 250;
    private int minSig = 125;

    private double minPing = 0.1;
    private double maxPing = 0.67;
    private double minVol = 0.05;
    private double maxVol = 0.3;
//    private double minPitch;
//    private double maxPitch;

    private boolean detectsCorpses = false;
    private boolean detectsTame = true;

    //Constructor
    public OldEpicDragonseekerItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(500)
                .tab(ItemGroup.TAB_TOOLS)
                .rarity(Rarity.RARE)
        );
    }


    //Repairing
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.NETHERITE_INGOT;
    }


    //Using the Item
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            itemstack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));

            double distance = getDistance(world, player);
            double chance = getPingChance(distance);
            float vol = (float) getPingVolume(distance);

//            printDistance(distance, world, player);

            double rand = random.nextDouble();
            if (rand <= chance) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_HARP, SoundCategory.MASTER, vol, 0.8F);
            } else {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BASS, SoundCategory.MASTER, 0.05F, 0.5F);
            }
            return ActionResult.success(itemstack);
        }
        return ActionResult.fail(itemstack);
    }


    //methods
    private double getDistance(World world, PlayerEntity player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        AxisAlignedBB box = new AxisAlignedBB(x-300,0,z-300,x+300,y+200,z+300);
        EntityPredicate pred = new EntityPredicate();
//        List<LivingEntity> listOfTargets = world.getNearbyEntities(ShulkerEntity.class,pred,player,box);
        List<EntityDragonBase> listOfTargets = world.getNearbyEntities(EntityDragonBase.class,pred,player,box);

        float min = 0;
        for (EntityDragonBase target : listOfTargets) {
            if ((detectsCorpses || !target.isModelDead()) && (detectsTame || !target.isTame())) {
                float distance = target.distanceTo(player);
                if ((min == 0) || distance < min) {
                    min = distance;
//                    String s = "Found dragon, updating minimum distance";
//                    ITextComponent text = new StringTextComponent(s);
//                    player.sendMessage(text, player.getUUID());
                } else if (distance >= min) {
//                    String s = "Found further dragon, ignoring";
//                    ITextComponent text = new StringTextComponent(s);
//                    player.sendMessage(text, player.getUUID());
                }
            } else if (!(detectsCorpses || !target.isModelDead())) {
//                String s = "Found corpse, ignoring";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
            } else if (!(detectsTame || !target.isTame())) {
//                String s = "Found tamed dragon, ignoring";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
            }
        }
        
        return min;
    }

    private double getPingChance(double distance) {
        double chance;
        if (distance < opDist && distance != 0) {
            chance = maxPing;
        } else if ((distance > maxDist) || (distance == 0)) {
            chance = minPing;
        } else {
            chance = minPing + ((maxDist-distance)/(maxDist-opDist))*(maxPing-minPing);
        }
        return chance;
    }

    private double getPingVolume(double distance) {
        double vol;
        if (distance < minSig && distance != 0) {
            vol = maxVol;
        } else if ((distance > maxDist) || (distance == 0)) {
            vol = minVol;
        } else {
            vol = minVol + ((maxDist-distance)/(maxDist-minSig))*(maxVol-minVol);
        }
        return vol;
    }

    private void printDistance(double distance, World world, PlayerEntity player) {
        if (!world.isClientSide) {
            int distancenew = (int) Math.round(distance);
            String s = String.valueOf(distancenew);
            ITextComponent text = new StringTextComponent(s);
            player.sendMessage(text, player.getUUID());
        }
    }

}
