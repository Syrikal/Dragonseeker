package syric.dragonseeker.item.tool;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class DingstickItem extends Item {

    public DingstickItem() {
        super(new Properties()
                .stacksTo(1)
                .durability(500)
                .tab(ItemGroup.TAB_TOOLS)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        itemstack.hurtAndBreak(1, playerIn, (entity) -> playerIn.broadcastBreakEvent(playerIn.getUsedItemHand()));

        float cowDistance = getCowDistance(worldIn, playerIn);
        if (cowDistance == 0) {
            return ActionResult.success(itemstack);
        } else if (cowDistance <= 10) {
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ANVIL_LAND, SoundCategory.NEUTRAL, 1/cowDistance, 1F);
        } else if (cowDistance <= 20) {
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.NOTE_BLOCK_PLING, SoundCategory.NEUTRAL, 0.1F, 1F);
        }
        return ActionResult.success(itemstack);
    }

    public static float getCowDistance(World world, PlayerEntity player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        AxisAlignedBB box = new AxisAlignedBB(x-500,y-500,z-500,x+500,y+500,z+500);
        EntityPredicate pred = new EntityPredicate();
        List<CowEntity> listOfCows = world.getNearbyEntities(CowEntity.class,pred,player,box);

        float min = 0;
        for (CowEntity cow : listOfCows) {
            float distance = cow.distanceTo(player);
            if ((min == 0) || distance < min) {
                min = distance;
            }
        }
        return min;
    }


}
