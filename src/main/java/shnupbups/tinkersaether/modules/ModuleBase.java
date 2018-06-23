package shnupbups.tinkersaether.modules;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import shnupbups.tinkersaether.Materials;
import shnupbups.tinkersaether.TinkersAether;
import shnupbups.tinkersaether.fluids.FluidHelper;
import shnupbups.tinkersaether.misc.MiscUtils;
import shnupbups.tinkersaether.misc.OreDictAether;
import shnupbups.tinkersaether.traits.*;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.tools.TinkerTools;

import static slimeknights.tconstruct.library.utils.HarvestLevels.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class ModuleBase {
    public static ModuleBase aether = new ModuleBase();

    public ModuleBase() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final Material skyroot = Materials.mat("skyroot", 0x6C633E);
    public static final Material holystone = Materials.mat("holystone", 0xA8AAA8);
    public static final Material goldenAmber = Materials.mat("goldenamber", 0xFFDD11);
    public static final Material zanite = Materials.mat("zanite", 0x6611DD);
    public static final Material gravitite = Materials.mat("gravitite", 0xCC55AA);
    public static final Material valkyrie = Materials.mat("valkyrie", 0xEEEEDD);

    public void preInit() {
        TinkersAether.logger.info("Base Module - Begin PreInit");

        TinkerRegistry.addMaterialStats(skyroot,
                new HeadMaterialStats(40, 2.10f, 2.00f, STONE),
                new HandleMaterialStats(1.10f, 30),
                new ExtraMaterialStats(20),
                new BowMaterialStats(1.05f, 1.05f, 0.05f),
                new ArrowShaftMaterialStats(1.05f, 0));
        skyroot.setCraftable(true).setCastable(false);
        skyroot.addItem("stickSkyroot", 1, Material.VALUE_Shard);
        skyroot.addItem("plankSkyroot", 1, Material.VALUE_Ingot);
        skyroot.addItem("logSkyroot", 1, Material.VALUE_Ingot * 4);
        skyroot.addTrait(Rooted.rooted, MaterialTypes.HEAD);
        skyroot.addTrait(ecological, MaterialTypes.HEAD);
        skyroot.addTrait(ecological);
        MaterialIntegration skyrootMi = new MaterialIntegration(skyroot).setRepresentativeItem("plankSkyroot");
        TinkerRegistry.integrate(skyrootMi).preInit();

        TinkerRegistry.addMaterialStats(holystone,
                new HeadMaterialStats(130, 4.10f, 3.00f, IRON),
                new HandleMaterialStats(0.50f, -50),
                new ExtraMaterialStats(25),
                TinkersAether.plzNo);
        holystone.setCraftable(true).setCastable(false);
        holystone.addItem("holystone", 1, Material.VALUE_Ingot);
        holystone.addTrait(Enlightened.enlightened, MaterialTypes.HEAD);
        holystone.addTrait(cheapskate, MaterialTypes.HEAD);
        holystone.addTrait(cheap);
        MaterialIntegration holystoneMi = new MaterialIntegration(holystone).setRepresentativeItem("holystone");
        TinkerRegistry.integrate(holystoneMi).preInit();

        TinkerRegistry.addMaterialStats(zanite,
                new HeadMaterialStats(210, 2.00f, 4.00f, DIAMOND),
                new HandleMaterialStats(0.9f, 65),
                new ExtraMaterialStats(50),
                TinkersAether.plzNo);
        zanite.setCraftable(true).setCastable(false);
        zanite.addItem("gemZanite", 1, Material.VALUE_Ingot);
        zanite.addItem("blockZanite", 1, Material.VALUE_Block);
        zanite.addTrait(Zany.zany, MaterialTypes.HEAD);
        zanite.addTrait(jagged, MaterialTypes.HEAD);
        zanite.addTrait(jagged);
        MaterialIntegration zaniteMi = new MaterialIntegration(zanite).setRepresentativeItem("gemZanite");
        TinkerRegistry.integrate(zaniteMi).preInit();

        TinkerRegistry.addMaterialStats(gravitite,
                new HeadMaterialStats(950, 7.50f, 5.00f, OBSIDIAN),
                new HandleMaterialStats(0.9f, 90),
                new ExtraMaterialStats(90),
                TinkersAether.plzNo);
        gravitite.setCraftable(false).setCastable(true);
        gravitite.addItem("blockEnchantedGravitite", 1, Material.VALUE_Ingot);
        gravitite.addTrait(Antigrav.antigrav, MaterialTypes.HEAD);
        gravitite.addTrait(Launching.launching, MaterialTypes.HEAD);
        gravitite.addTrait(Launching.launching);
        MaterialIntegration gravititeMi = new MaterialIntegration(null, gravitite, FluidHelper.createFluid(gravitite, 900), null).setRepresentativeItem("blockEnchantedGravitite");
        TinkerRegistry.integrate(gravititeMi).preInit();

        TinkersAether.logger.info("Base Module - Materials Registered");

        TinkersAether.logger.info("Base Module - End PreInit");
    }

    public void init() {
        TinkersAether.logger.info("Base Module - Begin Init");

        OreDictAether.register();

        TinkersAether.logger.info("Base Module - OreDict Registered");

        TinkerRegistry.registerMelting("blockEnchantedGravitite", gravitite.getFluid(), Material.VALUE_Ingot);
        TinkerRegistry.registerBasinCasting(new CastingRecipe(MiscUtils.stackFromOreDict("blockEnchantedGravitite"), gravitite.getFluid(), Material.VALUE_Ingot, 180));

        TinkersAether.logger.info("Base Module - Gravitite Stuffs Registered");

        TinkersAether.logger.info("Base Module - End Init");
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        TinkerTools.registerToolForgeBlock(registry, "blockEnchantedGravitite");
        TinkersAether.logger.info("Base Module - Recipes Registered");
    }
}
