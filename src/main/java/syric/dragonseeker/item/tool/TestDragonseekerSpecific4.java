package syric.dragonseeker.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TestDragonseekerSpecific4 extends TestDragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = 300;
    private static final int maxDist = 300;
    private static final double minPing = 0;
    private static final double maxPing = 1;

    //Ping characteristic stats
    private static final int minSig = 0;
    private static final double pow = 3.5;
    private static final float minVol = 0.05F;
    private static final float maxVol = 1F;
    private static final float minPitch = 0.5F;
    private static final float maxPitch = 0.8F;
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.NOTE_BLOCK_BASS;

    //Other stats
    private static final boolean detectsCorpses = false;
    private static final boolean detectsTame = false;
    private static final int durability = -1;
    private static final Rarity rarity = Rarity.EPIC;
    private static final Item repairItem = Items.NETHERITE_INGOT;
    private static final int seekerType = 4;

    //Constructor
    public TestDragonseekerSpecific4() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem,seekerType);
    }

}
