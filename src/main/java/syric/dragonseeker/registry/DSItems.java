package syric.dragonseeker.registry;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.item.IafTabRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import syric.dragonseeker.Dragonseeker;
import syric.dragonseeker.item.tool.dragonseekerItem;
import syric.dragonseeker.item.tool.epicDragonseekerItem;
import syric.dragonseeker.item.tool.godlyDragonseekerItem;
import syric.dragonseeker.item.tool.legendaryDragonseekerItem;

@Mod.EventBusSubscriber(
        modid = "dragonseeker",
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class DSItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dragonseeker.MODID);

    public static void init() {
        // attach DeferredRegister to the event bus
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // register item
    public static final RegistryObject<Item> DRAGONSEEKER = ITEMS.register("dragonseeker", dragonseekerItem::new);
    public static final RegistryObject<Item> EPICDRAGONSEEKER = ITEMS.register("epic_dragonseeker", epicDragonseekerItem::new);
    public static final RegistryObject<Item> LEGENDARYDRAGONSEEKER = ITEMS.register("legendary_dragonseeker", legendaryDragonseekerItem::new);
    public static final RegistryObject<Item> GODLYDRAGONSEEKER = ITEMS.register("godly_dragonseeker", godlyDragonseekerItem::new);


    @SubscribeEvent
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == IafTabRegistry.TAB_ITEMS.getKey()) {
            event.getEntries().putAfter(new ItemStack(IafItemRegistry.DRAGON_STAFF.get()), new ItemStack(DRAGONSEEKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(DRAGONSEEKER.get()), new ItemStack(EPICDRAGONSEEKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EPICDRAGONSEEKER.get()), new ItemStack(LEGENDARYDRAGONSEEKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(LEGENDARYDRAGONSEEKER.get()), new ItemStack(GODLYDRAGONSEEKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
