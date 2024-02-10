package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class legendaryDragonseekerItem extends dragonseekerGeneric {

    //Defining statistics
    //Ping chance stats
//    private static final int opDist = DragonseekerConfig.COMMON.legendary_optimalDistance.get();
//    private static final int maxDist = DragonseekerConfig.COMMON.legendary_maxDistance.get();
//    private static final double minPing = DragonseekerConfig.COMMON.legendary_minPingChance.get();
//    private static final double maxPing = DragonseekerConfig.COMMON.legendary_maxPingChance.get();

    //Ping characteristic stats
//    private static final int minSig = DragonseekerConfig.COMMON.legendary_pingCapRadius.get();
//    private static final double pow = DragonseekerConfig.COMMON.legendary_sigPower.get();
//    private static final Double minVol = DragonseekerConfig.COMMON.legendary_minVol.get();
//    private static final Double maxVol = DragonseekerConfig.COMMON.legendary_maxVol.get();
//    private static final Double minPitch = DragonseekerConfig.COMMON.legendary_minPitch.get();
//    private static final Double maxPitch = DragonseekerConfig.COMMON.legendary_maxPitch.get();
    private static final SoundEvent negSound = SoundEvents.NOTE_BLOCK_BASS;
    private static final SoundEvent pingSound = SoundEvents.EXPERIENCE_ORB_PICKUP;

    //Other stats
//    private static final boolean detectsCorpses = DragonseekerConfig.COMMON.legendary_detectsCorpses.get();
//    private static final boolean detectsTame = DragonseekerConfig.COMMON.legendary_detectsTame.get();
//    private static final int durability = DragonseekerConfig.COMMON.legendary_durability.get();
    private static final int durability = 512;
    private static final Rarity rarity = Rarity.RARE;
    private static final Item repairItem = IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get();
    private static final int seekerType = 3;

    //Constructor
    public legendaryDragonseekerItem() {
        super(200,250,.04,.95,100,2.5,.05,.7,.5,.8,negSound,pingSound,false,false,durability,rarity,repairItem, seekerType);
    }

    //Repairing
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return ((repair.getItem() == IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get()) || (repair.getItem() == IafItemRegistry.DRAGONSTEEL_ICE_INGOT.get()) || (repair.getItem() == IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT.get()));
    }

}
