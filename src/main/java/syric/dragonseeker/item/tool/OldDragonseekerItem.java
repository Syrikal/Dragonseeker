package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class OldDragonseekerItem extends Item {

    //Defining statistics
    private int opDist = 50;
    private int maxDist = 200;
//    private int minSig = 10;

    private double minPing = 0.15;
    private double maxPing = 0.5;
//    private double minVol = 0.05;
//    private double maxVol = 0.05;
//    private double minPitch;
//    private double maxPitch;

    private boolean detectsCorpses = true;
    private boolean detectsTame = true;
//    private int durability;
//    private Rarity rarity;

    //Constructor
    public OldDragonseekerItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(200)
                .tab(ItemGroup.TAB_TOOLS)
                .rarity(Rarity.UNCOMMON)
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
            float vol = 0.05F;

//            printDistance(distance, world, player);

            double rand = random.nextDouble();
            if (rand <= chance) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BASS, SoundCategory.MASTER, vol, 0.8F);
            } else {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BASS, SoundCategory.MASTER, vol, 0.5F);
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

    private void printDistance(double distance, World world, PlayerEntity player) {
        if (!world.isClientSide) {
            int distancenew = (int) Math.round(distance);
            String s = String.valueOf(distancenew);
            ITextComponent text = new StringTextComponent(s);
            player.sendMessage(text, player.getUUID());
        }
    }

//    private double getPingVolume(double distance) {
//        double vol;
//        if (distance < minSig) {
//            vol = maxVol;
//        } else if ((distance > maxDist) || (distance == 0)) {
//            vol = minVol;
//        } else {
//            vol = minVol + ((maxDist-distance)/(maxDist-minSig))*(maxVol-minVol);
//        }
//        return vol;
//    }
}
