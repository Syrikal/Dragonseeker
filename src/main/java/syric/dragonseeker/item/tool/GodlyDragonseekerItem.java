package syric.dragonseeker.item.tool;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class GodlyDragonseekerItem extends Item {

    //Defining statistics
    private int opDist = 300;
    private int maxDist = 300;
    private int minSig = 0;

    private double minPing = 0;
    private double maxPing = 1;
    private double minVol = 0.05;
    private double maxVol = 1;
//    private double minPitch;
//    private double maxPitch;

//    private boolean detectsCorpses;
//    private boolean detectsTame;
//    private int durability;
//    private Rarity rarity;

    //Constructor
    public GodlyDragonseekerItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(1000)
                .tab(ItemGroup.TAB_TOOLS)
                .rarity(Rarity.EPIC)
        );
    }

    //Using the Item
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        double distance = getDistance(world, player);
        String s = Double.toString(distance);
        ITextComponent text = new StringTextComponent(s);
        player.sendMessage(text,player.getUUID());
        double chance = getPingChance(distance);
        float vol = (float) getPingVolume(distance);

        double rand = random.nextDouble();
        if (rand <= chance) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, vol, 1.5F);
        } else {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BASS, SoundCategory.MASTER, vol, 0.5F);
        }

        return ActionResult.success(itemstack);
    }


    //methods
    private static double getDistance(World world, PlayerEntity player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        AxisAlignedBB box = new AxisAlignedBB(x-500,y-500,z-500,x+500,y+500,z+500);
        EntityPredicate pred = new EntityPredicate();
        List<LivingEntity> listOfTargets = world.getNearbyEntities(ShulkerEntity.class,pred,player,box);

        float min = 0;
        for (LivingEntity target : listOfTargets) {
            float distance = target.distanceTo(player);
            if ((min == 0) || distance < min) {
                min = distance;
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
}
