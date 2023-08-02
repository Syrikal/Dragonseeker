package syric.dragonseeker.registry;

//import com.github.alexthe666.iceandfire.IceAndFire;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import syric.dragonseeker.Dragonseeker;
import syric.dragonseeker.item.tool.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DSItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dragonseeker.MODID);

    public static void init() {
        // attach DeferredRegister to the event bus
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // register item
//    public static final RegistryObject<Item> DRAGONSEEKER = ITEMS.register("dragonseeker", dragonseekerItem::new);
//    public static final RegistryObject<Item> EPICDRAGONSEEKER = ITEMS.register("epic_dragonseeker", epicDragonseekerItem::new);
//    public static final RegistryObject<Item> LEGENDARYDRAGONSEEKER = ITEMS.register("legendary_dragonseeker", legendaryDragonseekerItem::new);
//    public static final RegistryObject<Item> GODLYDRAGONSEEKER = ITEMS.register("godly_dragonseeker", godlyDragonseekerItem::new);
    public static final RegistryObject<Item> DRAGONSEEKER_TICKING = ITEMS.register("dragonseeker_ticking",
            () -> new dragonseekerTicking(new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.COMMON)));

}
