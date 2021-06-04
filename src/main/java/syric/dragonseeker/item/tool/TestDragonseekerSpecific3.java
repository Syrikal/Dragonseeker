package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TestDragonseekerSpecific3 extends TestDragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
    private static final int opDist = 175;
    private static final int maxDist = 200;
    private static final double minPing = 0.05;
    private static final double maxPing = 0.84;

    //Ping characteristic stats
    private static final int minSig = 75;
    private static final double pow = 2.5;
    private static final float minVol = 0.05F;
    private static final float maxVol = 0.7F;
    private static final float minPitch = 0.5F;
    private static final float maxPitch = 0.8F;
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.EXPERIENCE_ORB_PICKUP;

    //Other stats
    private static final boolean detectsCorpses = false;
    private static final boolean detectsTame = false;
    private static final int durability = 1024;
    private static final Rarity rarity = Rarity.RARE;
    private static final Item repairItem = Items.NETHERITE_INGOT;
    private static final int seekerType = 3;

    //Constructor
    public TestDragonseekerSpecific3() {
        super(opDist,maxDist,minPing,maxPing,minSig,pow,minVol,maxVol,minPitch,maxPitch,negSound,pingSound,detectsCorpses,detectsTame,durability,rarity,repairItem, seekerType);
    }

    //Repairing
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return ((repair.getItem() == IafItemRegistry.DRAGONSTEEL_FIRE_INGOT) || (repair.getItem() == IafItemRegistry.DRAGONSTEEL_ICE_INGOT) || (repair.getItem() == IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT));
    }

}
