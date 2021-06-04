package syric.dragonseeker.registry;

import net.minecraft.item.Item;
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
//    public static final RegistryObject<Item> DINGSTICK = ITEMS.register("dingstick", DingstickItem::new);
    public static final RegistryObject<Item> DRAGONSEEKER = ITEMS.register("dragonseeker", DragonseekerItem::new);
    public static final RegistryObject<Item> EPICDRAGONSEEKER = ITEMS.register("epic_dragonseeker", EpicDragonseekerItem::new);
    public static final RegistryObject<Item> LEGENDARYDRAGONSEEKER = ITEMS.register("legendary_dragonseeker", LegendaryDragonseekerItem::new);
    public static final RegistryObject<Item> GODLYDRAGONSEEKER = ITEMS.register("godly_dragonseeker", GodlyDragonseekerItem::new);

    public static final RegistryObject<Item> GENERICDRAGONSEEKER = ITEMS.register("generic_dragonseeker", TestDragonseekerGeneric::new);
    public static final RegistryObject<Item> SPECIFICDRAGONSEEKER = ITEMS.register("specific_dragonseeker", TestDragonseekerSpecific::new);
    public static final RegistryObject<Item> SPECIFICDRAGONSEEKER2 = ITEMS.register("specific_dragonseeker_2", TestDragonseekerSpecific2::new);
}
