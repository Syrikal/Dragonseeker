package syric.dragonseeker.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.*;

public class dragonseekerItem extends dragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = 75;
    private static final int maxDist = 150;
    private static final double minPing = 0.15;
    private static final double maxPing = 0.67;

    //Ping characteristic stats
    private static final int minSig = 125;
    private static final double pow = 1.5;
    private static final float minVol = 0.05F;
    private static final float maxVol = 0.05F;
    private static final float minPitch = 0.5F;
    private static final float maxPitch = 0.8F;
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.NOTE_BLOCK_BASS;

    //Other stats
    private static final boolean detectsCorpses = true;
    private static final boolean detectsTame = true;
    private static final int durability = 128;
    private static final Rarity rarity = Rarity.UNCOMMON;
    private static final Item repairItem = Items.NETHERITE_INGOT;
    private static final int seekerType = 1;

    //Constructor
    public dragonseekerItem() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem,seekerType);
    }

}
