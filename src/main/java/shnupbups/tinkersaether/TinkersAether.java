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
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shnupbups.tinkersaether.config.TAConfig;
import shnupbups.tinkersaether.entities.EntityDart;
import shnupbups.tinkersaether.misc.MiscUtils;
import shnupbups.tinkersaether.modules.ModuleBase;
import shnupbups.tinkersaether.network.HandlerExtendedAttack;
import shnupbups.tinkersaether.network.MessageExtendedAttack;
import shnupbups.tinkersaether.proxy.CommonProxy;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.tools.TinkerMaterials;

@Mod(modid = TinkersAether.modid, name = TinkersAether.name, version = TinkersAether.version, acceptedMinecraftVersions = "[1.12.2]", dependencies = "required-after:mantle;required-after:tconstruct;required-after:aether_legacy@[1.12.2-v1.4.0,);")
public class TinkersAether {
    public static final String modid = "tinkersaether";
    public static final String name = "Tinkers Aether";
    public static final String version = "1.2.2";

    @Mod.Instance(modid)
    public static TinkersAether instance;

    @SidedProxy(serverSide = "shnupbups.tinkersaether.proxy.CommonProxy", clientSide = "shnupbups.tinkersaether.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(modid+"network");

    public static final Logger logger = LogManager.getLogger(modid);

    public static BowMaterialStats plzNo = new BowMaterialStats(0.2f, 0.4f, -1f);

    public TinkersAether() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModuleBase.base.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        int packet = 0;
        network.registerMessage(HandlerExtendedAttack.class,MessageExtendedAttack.class, packet++, Side.SERVER);

        if(TAConfig.darts) {
            proxy.initToolGuis();
        }

        ModuleBase.base.init();

        if(TAConfig.skyroot) {
            MiscUtils.displace(TinkerMaterials.wood.getIdentifier()); // Skyroot needs priority
        }
		if(TAConfig.skyrootLeaf||TAConfig.goldenOakLeaf||TAConfig.crystalLeaf||TAConfig.holidayLeaf) {
			MiscUtils.displace(TinkerMaterials.leaf.getIdentifier()); // Leaves need priority
		}
		if(TAConfig.goldenFeather) {
			MiscUtils.displace(TinkerMaterials.feather.getIdentifier()); // Golden Feather needs priority
		}
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModuleBase.base.postInit();
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
