package syric.dragonseeker.item.tool;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import syric.dragonseeker.DragonseekerConfig;

public class godlyDragonseekerItem extends dragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = DragonseekerConfig.COMMON.mythic_optimalDistance.get();
    private static final int maxDist = DragonseekerConfig.COMMON.mythic_maxDistance.get();
    private static final double minPing = DragonseekerConfig.COMMON.mythic_minPingChance.get();
    private static final double maxPing = DragonseekerConfig.COMMON.mythic_maxPingChance.get();

    //Ping characteristic stats
    private static final int minSig = DragonseekerConfig.COMMON.mythic_pingCapRadius.get();
    private static final double pow = DragonseekerConfig.COMMON.mythic_sigPower.get();
    private static final Double minVol = DragonseekerConfig.COMMON.mythic_minVol.get();
    private static final Double maxVol = DragonseekerConfig.COMMON.mythic_maxVol.get();
    private static final Double minPitch = DragonseekerConfig.COMMON.mythic_minPitch.get();
    private static final Double maxPitch = DragonseekerConfig.COMMON.mythic_maxPitch.get();
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.EXPERIENCE_ORB_PICKUP;

    //Other stats
    private static final boolean detectsCorpses = DragonseekerConfig.COMMON.mythic_detectsCorpses.get();
    private static final boolean detectsTame = DragonseekerConfig.COMMON.mythic_detectsTame.get();
//    private static final int durability = DragonseekerConfig.COMMON.mythic_durability.get();
    private static final int durability = -1;
    private static final Rarity rarity = Rarity.EPIC;
    private static final Item repairItem = Items.NETHERITE_INGOT;
    private static final int seekerType = 4;

    //Constructor
    public godlyDragonseekerItem() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem,seekerType);
    }

}
