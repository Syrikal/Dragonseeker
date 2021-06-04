package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TestDragonseekerSpecific2 extends TestDragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = 125;
    private static final int maxDist = 175;
    private static final double minPing = 0.1;
    private static final double maxPing = 0.75;

    //Ping characteristic stats
    private static final int minSig = 100;
    private static final double pow = 1.5;
    private static final float minVol = 0.05F;
    private static final float maxVol = 0.4F;
    private static final float minPitch = 0.5F;
    private static final float maxPitch = 0.8F;
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.NOTE_BLOCK_HARP;

    //Other stats
    private static final boolean detectsCorpses = false;
    private static final boolean detectsTame = true;
    private static final int durability = 256;
    private static final Rarity rarity = Rarity.RARE;
    private static final Item repairItem = IafItemRegistry.DRAGONSTEEL_FIRE_INGOT;
    private static final int seekerType = 2;


    //Constructor
    public TestDragonseekerSpecific2() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem,seekerType);
    }

}
