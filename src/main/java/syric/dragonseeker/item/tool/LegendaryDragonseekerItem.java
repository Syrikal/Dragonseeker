package syric.dragonseeker.item.tool;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;
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
//import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
//import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
//import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;

import java.util.ArrayList;
import java.util.List;

public class LegendaryDragonseekerItem extends Item {

    //Defining statistics
    private int opDist = 200;
    private int maxDist = 300;
    private int minSig = 75;

    private double minPing = 0.05;
    private double maxPing = 0.8;
    private double minVol = 0.05;
    private double maxVol = 1;
//    private double minPitch;
//    private double maxPitch;

//    private boolean detectsCorpses;
//    private boolean detectsTame;
//    private int durability;
//    private Rarity rarity;

    //Constructor
    public LegendaryDragonseekerItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(1000)
                .tab(ItemGroup.TAB_TOOLS)
                .rarity(Rarity.RARE)
        );
    }

    //Using the Item
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        itemstack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        double distance = getDistance(world, player);
        String s = Double.toString(distance);
        ITextComponent text = new StringTextComponent(s);
        player.sendMessage(text,player.getUUID());
        double chance = getPingChance(distance);
        float vol = (float) getPingVolume(distance);

        double rand = random.nextDouble();
        if (rand <= chance) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, vol, 1F);
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
//        List<LivingEntity> list1 = world.getNearbyEntities(EntityFireDragon.class,pred,player,box);
//        List<LivingEntity> list2 = world.getNearbyEntities(EntityIceDragon.class,pred,player,box);
//        List<LivingEntity> list3 = world.getNearbyEntities(EntityLightningDragon.class,pred,player,box);
//        List<LivingEntity> listOfTargets = new ArrayList<LivingEntity>();
//        listOfTargets.addAll(list1);
//        listOfTargets.addAll(list2);
//        listOfTargets.addAll(list3);

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
