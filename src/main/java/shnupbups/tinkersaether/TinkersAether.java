package shnupbups.tinkersaether;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shnupbups.tinkersaether.config.TAConfig;
import shnupbups.tinkersaether.entities.EntityDart;
import shnupbups.tinkersaether.misc.MiscUtils;
import shnupbups.tinkersaether.modules.ModuleBase;
import shnupbups.tinkersaether.proxy.CommonProxy;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.tools.TinkerMaterials;

@Mod(modid = TinkersAether.modid, name = TinkersAether.name, version = TinkersAether.version, acceptedMinecraftVersions = "[1.12.2]", dependencies = "required-after:mantle;required-after:tconstruct;required-after:aether_legacy;")
public class TinkersAether {
    public static final String modid = "tinkersaether";
    public static final String name = "MoreTiC";
    public static final String version = "1.1.0";

    @Mod.Instance(modid)
    public static TinkersAether instance;

    @SidedProxy(serverSide = "shnupbups.tinkersaether.proxy.CommonProxy", clientSide = "shnupbups.tinkersaether.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(modid);

    public static BowMaterialStats plzNo = new BowMaterialStats(0.2f, 0.4f, -1f);

    public TinkersAether() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModuleBase.aether.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(TAConfig.darts) {
            proxy.initToolGuis();
        }

        ModuleBase.aether.init();

        if(TAConfig.skyroot) {
            MiscUtils.displace(TinkerMaterials.wood.getIdentifier()); // Skyroot needs priority
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        if(TAConfig.darts) {
            EntityRegistry.registerModEntity(new ResourceLocation(TinkersAether.modid,"dart"), EntityDart.class, "dart",13, TinkersAether.instance, 64, 1, false);
        }
        TinkersAether.logger.info("Aether Tools Module - Entities Registered");
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        proxy.registerModels();
    }
}
