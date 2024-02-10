package syric.dragonseeker;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import syric.dragonseeker.registry.DSItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Dragonseeker.MODID)
public class Dragonseeker
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "dragonseeker";

    public Dragonseeker() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonseekerConfig.COMMON_SPEC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        DSItems.init();
    }

}
