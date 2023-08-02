//package syric.dragonseeker.item.tool;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.audio.TickableSound;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.SoundEvents;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class SeekerSound extends TickableSound {
//    private int activeCounter;
//    private Minecraft mc;
//    private boolean isActive;
//
//    public SeekerSound(Minecraft mc) {
//        super(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, SoundCategory.BLOCKS);
//        this.mc = mc;
//        this.priority = true;
//        this.looping = true;
//        this.attenuation = AttenuationType.NONE;
//        this.volume = 0.0F;
//        this.pitch = 0.5F;
//    }
//
//    public void perpetuate() {
//        if (!this.isActive) {
//            this.mc.getSoundManager().play(this);
//            this.isActive = true;
//        }
//
//        this.activeCounter = 2;
//    }
//
//    @Override
//    public void tick() {
//        if (this.activeCounter > 0) {
//            this.volume = Math.min(this.volume + 0.03F, 0.5F);
//            --this.activeCounter;
//        } else {
//            this.volume = Math.max(this.volume - 0.027F, 0.0F);
//            if (this.volume == 0.0F) {
//                this.isActive = false;
//                this.mc.getSoundManager().stop(this);
//            }
//        }
//    }
//
//    public boolean canStartSilent() {
//        return true;
//    }
//
//}
