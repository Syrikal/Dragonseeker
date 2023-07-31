package syric.dragonseeker.item.tool;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import syric.dragonseeker.DragonseekerConfig;

public class epicDragonseekerItem extends dragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = DragonseekerConfig.COMMON.epic_optimalDistance.get();
    private static final int maxDist = DragonseekerConfig.COMMON.epic_maxDistance.get();
    private static final double minPing = DragonseekerConfig.COMMON.epic_minPingChance.get();
    private static final double maxPing = DragonseekerConfig.COMMON.epic_maxPingChance.get();

    //Ping characteristic stats
    private static final int minSig = DragonseekerConfig.COMMON.epic_pingCapRadius.get();
    private static final double pow = DragonseekerConfig.COMMON.epic_sigPower.get();
    private static final Double minVol = DragonseekerConfig.COMMON.epic_minVol.get();
    private static final Double maxVol = DragonseekerConfig.COMMON.epic_maxVol.get();
    private static final Double minPitch = DragonseekerConfig.COMMON.epic_minPitch.get();
    private static final Double maxPitch = DragonseekerConfig.COMMON.epic_maxPitch.get();
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.NOTE_BLOCK_HARP;

    //Other stats
    private static final boolean detectsCorpses = DragonseekerConfig.COMMON.epic_detectsCorpses.get();
    private static final boolean detectsTame = DragonseekerConfig.COMMON.epic_detectsTame.get();
//    private static final int durability = DragonseekerConfig.COMMON.epic_durability.get();
    private static final int durability = 256;
    private static final Rarity rarity = Rarity.RARE;
    private static final Item repairItem = Items.NETHERITE_INGOT;
    private static final int seekerType = 2;


    //Constructor
    public epicDragonseekerItem() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem,seekerType);
    }

}
