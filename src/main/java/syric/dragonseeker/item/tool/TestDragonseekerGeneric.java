package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class TestDragonseekerGeneric extends Item {

    //Defining statistics
    //Ping chance stats
    private int opDist;
    private int maxDist;
    private double minPing;
    private double maxPing;

    //Ping characteristic stats
    private int minSig;
    private double pow;
    private float minVol;
    private float maxVol;
    private float minPitch;
    private float maxPitch;
    private SoundEvent negSound;
    private SoundEvent pingSound;

    //Other stats
    private boolean detectsCorpses;
    private boolean detectsTame;
//    private int durability;
//    private Rarity rarity;
    private Item repairItem;
    private int seekerType;

    //Constructor
    public TestDragonseekerGeneric() {
        super(new Properties()
                .stacksTo(1)
                .tab(IceAndFire.TAB_ITEMS)
        );
    }

//    public TestDragonseekerGeneric(int durability, Rarity rarity) {
//        super(new Properties()
//                .stacksTo(1)
//                .tab(IceAndFire.TAB_ITEMS)
//                .durability(durability)
//                .rarity(rarity)
//        );
//    }

    public TestDragonseekerGeneric(int opDistIn, int maxDistIn, double minPingIn, double maxPingIn, int minSigIn, double powIn, float minVolIn, float maxVolIn, float minPitchIn, float maxPitchIn, SoundEvent negSoundIn, SoundEvent pingSoundIn, boolean detectsCorpsesIn, boolean detectsTameIn, int durabilityIn, Rarity rarityIn, Item repairItemIn, int seekerTypeIn) {
        super(new Properties()
                .stacksTo(1)
                .tab(IceAndFire.TAB_ITEMS)
                .durability(durabilityIn)
                .rarity(rarityIn)
        );
        opDist = opDistIn;
        maxDist = maxDistIn;
        minPing = minPingIn;
        maxPing = maxPingIn;
        minSig = minSigIn;
        pow = powIn;
        minVol = minVolIn;
        maxVol = maxVolIn;
        minPitch = minPitchIn;
        maxPitch = maxPitchIn;
        negSound = negSoundIn;
        pingSound = pingSoundIn;
        detectsCorpses = detectsCorpsesIn;
        detectsTame = detectsTameIn;
//        durability = durabilityIn;
//        rarity = rarityIn;
        repairItem = repairItemIn;
        seekerType = seekerTypeIn;
    }


    //Repairing
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == repairItem;
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
                world.playSound(null, player.getX(), player.getY(), player.getZ(), pingSound, SoundCategory.MASTER, vol, maxPitch);
//                String s = "PING";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
            } else {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), negSound, SoundCategory.MASTER, minVol, minPitch);
//                String s = "PONG";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
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
        EntityDragonBase closest = null;
        for (EntityDragonBase target : listOfTargets) {
            if ((detectsCorpses || !target.isModelDead()) && (detectsTame || !target.isTame())) {
                float distance = target.distanceTo(player);
                if ((min == 0) || distance < min) {
                    min = distance;
                    closest = target;
//                    String s = "Found dragon, updating minimum distance";
//                    ITextComponent text = new StringTextComponent(s);
//                    player.sendMessage(text, player.getUUID());
                } else if (distance >= min) {
//                    String s = "Found further dragon, ignoring";
//                    ITextComponent text = new StringTextComponent(s);
//                    player.sendMessage(text, player.getUUID());
                }
            } else if (!(detectsCorpses || target.isAlive())) {
                String s = "Found corpse, ignoring";
                ITextComponent text = new StringTextComponent(s);
                player.sendMessage(text, player.getUUID());
            } else if (!(detectsTame || !target.isTame())) {
                String s = "Found tamed dragon, ignoring";
                ITextComponent text = new StringTextComponent(s);
                player.sendMessage(text, player.getUUID());
            }
        }
        if (seekerType == 4) {
            String s = "";
            if (closest != null) {
                s = Math.round(min) + ", x=" + (int) closest.getX() + ", y="+ (int) closest.getY() + ", z=" + (int) closest.getZ();
            } else {
                s = "No dragon found";
            }
            ITextComponent text = new StringTextComponent(s);
            player.sendMessage(text,player.getUUID());
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
            vol = minVol + Math.pow(((maxDist-distance)/(maxDist-minSig)),pow)*(maxVol-minVol);
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
