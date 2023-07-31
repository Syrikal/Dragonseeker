package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import syric.dragonseeker.DragonseekerConfig;

import java.util.List;
import java.util.Random;

public class dragonseekerGeneric extends Item {

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
    private final SoundEvent negSound;
    private final SoundEvent pingSound;

    //Other stats
    private boolean detectsCorpses;
    private boolean detectsTame;
//    private int durability;
//    private Rarity rarity;
    private final Item repairItem;
    private final int seekerType;
    private boolean isDefault;

    //Constructor
//    public dragonseekerGeneric() {
//        super(new Properties()
//                .stacksTo(1)
//                .tab(IceAndFire.TAB_ITEMS)
//        );
//    }

    public dragonseekerGeneric(int opDistIn, int maxDistIn, double minPingIn, double maxPingIn, int minSigIn, double powIn, double minVolIn, double maxVolIn, double minPitchIn, double maxPitchIn, SoundEvent negSoundIn, SoundEvent pingSoundIn, boolean detectsCorpsesIn, boolean detectsTameIn, int durabilityIn, Rarity rarityIn, Item repairItemIn, int seekerTypeIn) {
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
        minVol = (float) minVolIn;
        maxVol = (float) maxVolIn;
        minPitch = (float) minPitchIn;
        maxPitch = (float) maxPitchIn;
        negSound = negSoundIn;
        pingSound = pingSoundIn;
        detectsCorpses = detectsCorpsesIn;
        detectsTame = detectsTameIn;
//        durability = durabilityIn;
//        rarity = rarityIn;
        repairItem = repairItemIn;
        seekerType = seekerTypeIn;
        isDefault = true;
    }


    //Repairing
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == repairItem;
    }


    //Using the Item
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {

//            String s = "Before update, item OpDist is: " + opDist;
//            ITextComponent text = new StringTextComponent(s);
//            player.sendMessage(text, player.getUUID());

            if (isDefault) {
//                String a = "Item stats default, updating";
//                ITextComponent texta = new StringTextComponent(a);
//                player.sendMessage(texta, player.getUUID());
                importConfig();
                isDefault = false;
            } else {
//                String b = "Item stats not default, not updating";
//                ITextComponent textb = new StringTextComponent(b);
//                player.sendMessage(textb, player.getUUID());
            }

//            s = "After update, item OpDist is: " + opDist;
//            ITextComponent text2 = new StringTextComponent(s);
//            player.sendMessage(text2, player.getUUID());

            itemstack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));

//            String s = "basic durability config = " + DragonseekerConfig.COMMON.basic_durability.get();
//            ITextComponent text = new StringTextComponent(s);
//            player.sendMessage(text, player.getUUID());

            double distance = getDistance(world, player);
            double chance = getPingChance(distance);
            float vol = (float) getPingVolume(distance);

//            printDistance(distance, world, player);

            Random random = new Random();

            double rand = random.nextDouble();
            if (rand <= chance) {
                //Positive result
                world.playSound(null, player.getX(), player.getY(), player.getZ(), pingSound, SoundSource.MASTER, vol, maxPitch);
//                String s = "PING";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
            } else {
                //Negative result
                world.playSound(null, player.getX(), player.getY(), player.getZ(), negSound, SoundSource.MASTER, minVol, minPitch);
//                String s = "PONG";
//                ITextComponent text = new StringTextComponent(s);
//                player.sendMessage(text, player.getUUID());
            }

            return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
        }
        return InteractionResultHolder.fail(itemstack);
    }


    //methods
    private double getDistance(Level world, Player player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        AABB box = new AABB(x-300,0,z-300,x+300,y+200,z+300);
//        List<LivingEntity> listOfTargets = world.getNearbyEntities(ShulkerEntity.class,pred,player,box);
        List<EntityDragonBase> listOfTargets = world.getEntitiesOfClass(EntityDragonBase.class, box);

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
        if (seekerType == 4) {
            String s = "";
            if (closest != null) {
                s = Math.round(min) + ", x=" + (int) closest.getX() + ", y="+ (int) closest.getY() + ", z=" + (int) closest.getZ();
            } else {
                s = "No dragon found";
            }
            player.displayClientMessage(new TextComponent(s), false);
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

    private void printDistance(double distance, Level world, Player player) {
        if (!world.isClientSide) {
            int distancenew = (int) Math.round(distance);
            String s = String.valueOf(distancenew);
            player.displayClientMessage(new TextComponent(s), false);
        }
    }

    public void importConfig() {
        if (seekerType == 1) {
            opDist = DragonseekerConfig.COMMON.basic_optimalDistance.get();
            maxDist = DragonseekerConfig.COMMON.basic_maxDistance.get();
            minPing = DragonseekerConfig.COMMON.basic_minPingChance.get();
            maxPing = DragonseekerConfig.COMMON.basic_maxPingChance.get();

            //Ping characteristic stats
            minSig = DragonseekerConfig.COMMON.basic_pingCapRadius.get();
            pow = DragonseekerConfig.COMMON.basic_sigPower.get();
            minVol = DragonseekerConfig.COMMON.basic_minVol.get().floatValue();
            maxVol = DragonseekerConfig.COMMON.basic_maxVol.get().floatValue();
            minPitch = DragonseekerConfig.COMMON.basic_minPitch.get().floatValue();
            maxPitch = DragonseekerConfig.COMMON.basic_maxPitch.get().floatValue();

            //Other stats
            detectsCorpses = DragonseekerConfig.COMMON.basic_detectsCorpses.get();
            detectsTame = DragonseekerConfig.COMMON.basic_detectsTame.get();
            isDefault = false;

        } else if (seekerType == 2) {
            opDist = DragonseekerConfig.COMMON.epic_optimalDistance.get();
            maxDist = DragonseekerConfig.COMMON.epic_maxDistance.get();
            minPing = DragonseekerConfig.COMMON.epic_minPingChance.get();
            maxPing = DragonseekerConfig.COMMON.epic_maxPingChance.get();

            //Ping characteristic stats
            minSig = DragonseekerConfig.COMMON.epic_pingCapRadius.get();
            pow = DragonseekerConfig.COMMON.epic_sigPower.get();
            minVol = DragonseekerConfig.COMMON.epic_minVol.get().floatValue();
            maxVol = DragonseekerConfig.COMMON.epic_maxVol.get().floatValue();
            minPitch = DragonseekerConfig.COMMON.epic_minPitch.get().floatValue();
            maxPitch = DragonseekerConfig.COMMON.epic_maxPitch.get().floatValue();

            //Other stats
            detectsCorpses = DragonseekerConfig.COMMON.epic_detectsCorpses.get();
            detectsTame = DragonseekerConfig.COMMON.epic_detectsTame.get();
            isDefault = false;

        } else if (seekerType == 3) {
            opDist = DragonseekerConfig.COMMON.legendary_optimalDistance.get();
            maxDist = DragonseekerConfig.COMMON.legendary_maxDistance.get();
            minPing = DragonseekerConfig.COMMON.legendary_minPingChance.get();
            maxPing = DragonseekerConfig.COMMON.legendary_maxPingChance.get();

            //Ping characteristic stats
            minSig = DragonseekerConfig.COMMON.legendary_pingCapRadius.get();
            pow = DragonseekerConfig.COMMON.legendary_sigPower.get();
            minVol = DragonseekerConfig.COMMON.legendary_minVol.get().floatValue();
            maxVol = DragonseekerConfig.COMMON.legendary_maxVol.get().floatValue();
            minPitch = DragonseekerConfig.COMMON.legendary_minPitch.get().floatValue();
            maxPitch = DragonseekerConfig.COMMON.legendary_maxPitch.get().floatValue();

            //Other stats
            detectsCorpses = DragonseekerConfig.COMMON.legendary_detectsCorpses.get();
            detectsTame = DragonseekerConfig.COMMON.legendary_detectsTame.get();
            isDefault = false;

        } else if (seekerType == 4) {
            opDist = DragonseekerConfig.COMMON.mythic_optimalDistance.get();
            maxDist = DragonseekerConfig.COMMON.mythic_maxDistance.get();
            minPing = DragonseekerConfig.COMMON.mythic_minPingChance.get();
            maxPing = DragonseekerConfig.COMMON.mythic_maxPingChance.get();

            //Ping characteristic stats
            minSig = DragonseekerConfig.COMMON.mythic_pingCapRadius.get();
            pow = DragonseekerConfig.COMMON.mythic_sigPower.get();
            minVol = DragonseekerConfig.COMMON.mythic_minVol.get().floatValue();
            maxVol = DragonseekerConfig.COMMON.mythic_maxVol.get().floatValue();
            minPitch = DragonseekerConfig.COMMON.mythic_minPitch.get().floatValue();
            maxPitch = DragonseekerConfig.COMMON.mythic_maxPitch.get().floatValue();

            //Other stats
            detectsCorpses = DragonseekerConfig.COMMON.mythic_detectsCorpses.get();
            detectsTame = DragonseekerConfig.COMMON.mythic_detectsTame.get();
            isDefault = false;
        } else {
            return;
        }
    }

}
