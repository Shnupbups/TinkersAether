package shnupbups.tinkersaether.modules;

import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import shnupbups.tinkersaether.Materials;
import shnupbups.tinkersaether.TinkersAether;
import shnupbups.tinkersaether.blocks.TABlock;
import shnupbups.tinkersaether.config.TAConfig;
import shnupbups.tinkersaether.fluids.FluidHelper;
import shnupbups.tinkersaether.items.TAItem;
import shnupbups.tinkersaether.misc.MiscUtils;
import shnupbups.tinkersaether.misc.OreDict;
import shnupbups.tinkersaether.traits.*;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.tools.TinkerTools;

import static slimeknights.tconstruct.library.utils.HarvestLevels.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

@Mod.EventBusSubscriber(modid = TinkersAether.modid)
public class ModuleBase {
    public static ModuleBase base = new ModuleBase();

    public ModuleBase() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final Material skyroot = Materials.mat("skyroot", 0x6C633E);
    public static final Material holystone = Materials.mat("holystone", 0xA8AAA8);
    public static final Material goldenAmber = Materials.mat("golden_amber", 0xFFE41C);
    public static final Material zanite = Materials.mat("zanite", 0x6611DD);
    public static final Material gravitite = Materials.mat("gravitite", 0xCC55AA);
    public static final Material valkyrie = Materials.mat("valkyrie", 0xEEEEDD);
    public static final Material swet = Materials.mat("swet", 0x29A6D9);
    public static final Material candyCane = Materials.mat("candy_cane", 0xFF3333);
    public static final Material aercloudBlue = Materials.mat("aercloud_blue", 0x99B2C2);
    public static final Material aercloudCold = Materials.mat("aercloud_cold", 0xAAAAAA);
	public static final Material aercloudGold = Materials.mat("aercloud_gold", 0xFFF1A1);
	public static final Material icestone = Materials.mat("icestone", 0x99999F);

	public static final Material skyrootLeaf = Materials.mat("skyroot_leaf", 0xBFFF5F);
	public static final Material goldenOakLeaf = Materials.mat("golden_oak_leaf", 0xEAF84F);
	public static final Material crystalLeaf = Materials.mat("crystal_leaf", 0x29AAD9);
	public static final Material holidayLeaf = Materials.mat("holiday_leaf", 0xC7A0EB);
	public static final Material goldenFeather = Materials.mat("golden_feather", 0xFFF25C);

    public static final TAItem valkyrieIngot = new TAItem("valkyrie_ingot").setBeaconPayment();
    public static final TAItem valkyrieNugget = new TAItem("valkyrie_nugget");
    public static final TABlock valkyrieBlock = new TABlock("valkyrie_block", net.minecraft.block.material.Material.IRON).setBeaconBase();

    public static final TAItem swetCrystal = new TAItem("swet_crystal");

    public void preInit() {
        TinkersAether.logger.info("Base Module - Begin PreInit");

        if(TAConfig.skyroot) {
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
            skyroot.addTrait(Skyrooted.skyrooted, MaterialTypes.HEAD);
            skyroot.addTrait(ecological, MaterialTypes.HEAD);
            skyroot.addTrait(ecological);
            MaterialIntegration skyrootMi = new MaterialIntegration(skyroot).setRepresentativeItem("plankSkyroot");
            TinkerRegistry.integrate(skyrootMi).preInit();
        }

        if(TAConfig.holystone) {
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
        }

        if(TAConfig.zanite) {
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
        }

        if(TAConfig.gravitite) {
            TinkerRegistry.addMaterialStats(gravitite,
                    new HeadMaterialStats(950, 7.50f, 5.00f, OBSIDIAN),
                    new HandleMaterialStats(0.9f, 90),
                    new ExtraMaterialStats(90),
                    TinkersAether.plzNo);
            gravitite.setCraftable(false).setCastable(true);
            gravitite.addItem("blockEnchantedGravitite", 1, Material.VALUE_Ingot);
            gravitite.addTrait(Antigrav.antigrav, MaterialTypes.HEAD);
            gravitite.addTrait(Launching.launching, MaterialTypes.HEAD);
            gravitite.addTrait(Gilded.gilded, MaterialTypes.HEAD);
            gravitite.addTrait(Launching.launching);
            MaterialIntegration gravititeMi = new MaterialIntegration(null, gravitite, FluidHelper.createFluid(gravitite, 900), null).setRepresentativeItem("blockEnchantedGravitite");
            TinkerRegistry.integrate(gravititeMi).preInit();
        }

        if(TAConfig.goldenAmber) {
            TinkerRegistry.addMaterialStats(goldenAmber,
                    new HeadMaterialStats(300, 1.50f, 7.20f, STONE),
                    new HandleMaterialStats(0.7f, 40),
                    new ExtraMaterialStats(30),
                    TinkersAether.plzNo);
            goldenAmber.setCraftable(true).setCastable(false);
            goldenAmber.addItem("gemGoldenAmber", 1, Material.VALUE_Ingot);
            goldenAmber.addTrait(Gilded.gilded);
            MaterialIntegration goldenAmberMi = new MaterialIntegration(goldenAmber).setRepresentativeItem("gemGoldenAmber");
            TinkerRegistry.integrate(goldenAmberMi).preInit();
        }

        if(TAConfig.valkyrie) {
            TinkerRegistry.addMaterialStats(valkyrie,
                    new HeadMaterialStats(1000, 8.0f, 6.50f, COBALT),
                    new HandleMaterialStats(1.0f, 80),
                    new ExtraMaterialStats(70),
                    TinkersAether.plzNo);
            valkyrie.setCraftable(false).setCastable(true);
            valkyrie.addItem("blockValkyrie", 1, Material.VALUE_Block);
            valkyrie.addItem(valkyrieBlock, Material.VALUE_Block);
            valkyrie.addItem("ingotValkryie", 1, Material.VALUE_Ingot);
            valkyrie.addItem(valkyrieIngot, 1, Material.VALUE_Ingot);
            valkyrie.addItem("nuggetValkryie", 1, Material.VALUE_Nugget);
            valkyrie.addItem(valkyrieNugget, 1, Material.VALUE_Nugget);
            valkyrie.addTrait(Gilded.gilded, MaterialTypes.HEAD);
            valkyrie.addTrait(Reach.reach, MaterialTypes.HEAD);
            valkyrie.addTrait(Reach.reach);
            MaterialIntegration valkyrieMi = new MaterialIntegration(null, valkyrie, FluidHelper.createFluid(valkyrie, 1000), "Valkyrie");
            TinkerRegistry.integrate(valkyrieMi).preInit();
        }

        if(TAConfig.swet) {
            TinkerRegistry.addMaterialStats(swet,
                    new HeadMaterialStats(1100, 4.5f, 2.0f, STONE),
                    new HandleMaterialStats(0.7f, -100),
                    new ExtraMaterialStats(360),
                    new BowMaterialStats(1.0f, 1.5f, 0.5f));
            swet.setCraftable(true).setCastable(false);
            swet.addItem("slimecrystalSwet", 1, Material.VALUE_Ingot);
            swet.addItem(swetCrystal, 1, Material.VALUE_Ingot);
            swet.addTrait(Swetty.swetty);
            MaterialIntegration swetMi = new MaterialIntegration(swet).setRepresentativeItem("slimecrystalSwet");
            TinkerRegistry.integrate(swetMi).preInit();
        }

        if(TAConfig.candyCane) {
            TinkerRegistry.addMaterialStats(candyCane,
                    new HeadMaterialStats(250, 2.5f, 5.0f, STONE),
                    new HandleMaterialStats(1.2f, -120),
                    new ExtraMaterialStats(120),
                    TinkersAether.plzNo);
            candyCane.setCraftable(true).setCastable(false);
            candyCane.addItem("candyCane", 1, Material.VALUE_Ingot);
            candyCane.addTrait(Festive.festive);
            candyCane.addTrait(tasty);
            MaterialIntegration candyCaneMi = new MaterialIntegration(candyCane).setRepresentativeItem("candyCane");
            TinkerRegistry.integrate(candyCaneMi).preInit();
        }

        if(TAConfig.aercloudCold) {
            TinkerRegistry.addMaterialStats(aercloudCold,
                    new HeadMaterialStats(2000, 0.5f, 0.0f, STONE),
                    new HandleMaterialStats(0.2f, -500),
                    new ExtraMaterialStats(0),
                    TinkersAether.plzNo);
            aercloudCold.setCraftable(true).setCastable(false);
            aercloudCold.addItem("aercloudCold", 1, Material.VALUE_Ingot);
            aercloudCold.addTrait(Cushy.cushy);
            MaterialIntegration aercloudColdMi = new MaterialIntegration(aercloudCold).setRepresentativeItem("aercloudCold");
            TinkerRegistry.integrate(aercloudColdMi).preInit();
        }

        if(TAConfig.aercloudBlue) {
            TinkerRegistry.addMaterialStats(aercloudBlue,
                    new HeadMaterialStats(2000, 0.5f, 0.0f, STONE),
                    new HandleMaterialStats(0.2f, -500),
                    new ExtraMaterialStats(0),
                    TinkersAether.plzNo);
            aercloudBlue.setCraftable(true).setCastable(false);
            aercloudBlue.addItem("aercloudBlue", 1, Material.VALUE_Ingot);
            aercloudBlue.addTrait(Cushy.cushy);
            aercloudBlue.addTrait(Cushy.cushy, MaterialTypes.HEAD);
            aercloudBlue.addTrait(Launching.launching, MaterialTypes.HEAD);
            MaterialIntegration aercloudBlueMi = new MaterialIntegration(aercloudBlue).setRepresentativeItem("aercloudBlue");
            TinkerRegistry.integrate(aercloudBlueMi).preInit();
        }

        if(TAConfig.aercloudGold) {
            TinkerRegistry.addMaterialStats(aercloudGold,
                    new HeadMaterialStats(2500, 1.0f, 0.1f, STONE),
                    new HandleMaterialStats(0.25f, -400),
                    new ExtraMaterialStats(20),
                    TinkersAether.plzNo);
            aercloudGold.setCraftable(true).setCastable(false);
            aercloudGold.addItem("aercloudGold", 1, Material.VALUE_Ingot);
            aercloudGold.addTrait(Cushy.cushy);
            MaterialIntegration aercloudGoldMi = new MaterialIntegration(aercloudGold).setRepresentativeItem("aercloudGold");
            TinkerRegistry.integrate(aercloudGoldMi).preInit();
        }

		if(TAConfig.icestone) {
			TinkerRegistry.addMaterialStats(icestone,
					new HeadMaterialStats(250, 4.20f, 3.50f, IRON),
					new HandleMaterialStats(0.50f, -20),
					new ExtraMaterialStats(40),
					TinkersAether.plzNo);
			icestone.setCraftable(true).setCastable(false);
			icestone.addItem("icestone", 1, Material.VALUE_Ingot);
			icestone.addTrait(Refrigeration.refrigeration);
			MaterialIntegration icestoneMi = new MaterialIntegration(icestone).setRepresentativeItem("icestone");
			TinkerRegistry.integrate(icestoneMi).preInit();
		}

		if(TAConfig.skyrootLeaf) {
			TinkerRegistry.addMaterialStats(skyrootLeaf, new FletchingMaterialStats(0.5f, 1.6f));
			skyrootLeaf.setCraftable(true).setCastable(false);
			skyrootLeaf.addItem("treeLeavesSkyroot", 1, Material.VALUE_Shard);
			MaterialIntegration skyrootLeafMi = new MaterialIntegration(skyrootLeaf).setRepresentativeItem("treeLeavesSkyroot");
			TinkerRegistry.integrate(skyrootLeafMi).preInit();
		}

		if(TAConfig.goldenOakLeaf) {
			TinkerRegistry.addMaterialStats(goldenOakLeaf, new FletchingMaterialStats(0.7f, 1.7f));
			goldenOakLeaf.setCraftable(true).setCastable(false);
			goldenOakLeaf.addItem("treeLeavesGoldenOak", 1, Material.VALUE_Shard);
			MaterialIntegration goldenOakLeafMi = new MaterialIntegration(goldenOakLeaf).setRepresentativeItem("treeLeavesGoldenOak");
			TinkerRegistry.integrate(goldenOakLeafMi).preInit();
		}

		if(TAConfig.crystalLeaf) {
			TinkerRegistry.addMaterialStats(crystalLeaf, new FletchingMaterialStats(0.8f, 2f));
			crystalLeaf.setCraftable(true).setCastable(false);
			crystalLeaf.addItem("treeLeavesCrystal", 1, Material.VALUE_Shard);
			MaterialIntegration crystalLeafMi = new MaterialIntegration(crystalLeaf).setRepresentativeItem("treeLeavesCrystal");
			TinkerRegistry.integrate(crystalLeafMi).preInit();
		}

		if(TAConfig.holidayLeaf) {
			TinkerRegistry.addMaterialStats(holidayLeaf, new FletchingMaterialStats(0.9f, 1.8f));
			holidayLeaf.setCraftable(true).setCastable(false);
			holidayLeaf.addItem("treeLeavesHoliday", 1, Material.VALUE_Shard);
			MaterialIntegration holidayLeafMi = new MaterialIntegration(holidayLeaf).setRepresentativeItem("treeLeavesHoliday");
			TinkerRegistry.integrate(holidayLeafMi).preInit();
		}

		if(TAConfig.goldenFeather) {
			TinkerRegistry.addMaterialStats(goldenFeather, new FletchingMaterialStats(1.0f, 2f));
			goldenFeather.setCraftable(true).setCastable(false);
			goldenFeather.addItem("featherGold", 1, Material.VALUE_Ingot);
			MaterialIntegration goldenFeatherMi = new MaterialIntegration(goldenFeather).setRepresentativeItem("featherGold");
			TinkerRegistry.integrate(goldenFeatherMi).preInit();
		}

        TinkersAether.logger.info("Base Module - Materials Registered");

        TinkersAether.logger.info("Base Module - End PreInit");
    }

    public void init() {
        TinkersAether.logger.info("Base Module - Begin Init");

        OreDict.register();

        TinkersAether.logger.info("Base Module - OreDict Registered");

        TinkersAether.logger.info("Base Module - End Init");
    }

    public void postInit() {
        if(TAConfig.gravitite) {
            TinkerRegistry.registerMelting("blockEnchantedGravitite", gravitite.getFluid(), Material.VALUE_Ingot);
            TinkerRegistry.registerBasinCasting(new CastingRecipe(MiscUtils.stackFromOreDict("blockEnchantedGravitite"), gravitite.getFluid(), Material.VALUE_Ingot, 180));
            TinkersAether.logger.info("Base Module - Gravitite Stuffs Registered");
        }
    }

    @SubscribeEvent
	public void disableOreSmelting(TinkerRegisterEvent.MeltingRegisterEvent event) {
    	if(event.getRecipe().input.getInputs().contains(MiscUtils.stackFromOreDict("oreGravitite"))) {
    		event.setCanceled(!TAConfig.gravititeOreMelt);
		}
	}

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        if(TAConfig.gravititeForge) {
            TinkerTools.registerToolForgeBlock(registry, "blockEnchantedGravitite");
        }
        if(TAConfig.valkyrie && TAConfig.valkyrieForge) {
            TinkerTools.registerToolForgeBlock(registry, "blockValkyrie");
        }
        TinkersAether.logger.info("Base Module - Recipes Registered");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        if(TAConfig.valkyrie) {
            registry.register(valkyrieIngot);
            registry.register(valkyrieNugget);
            Item valkBlock = new ItemBlock(valkyrieBlock).setRegistryName(valkyrieBlock.getRegistryName()).setUnlocalizedName(valkyrieBlock.getUnlocalizedName());
            registry.register(valkBlock);
            TinkersAether.proxy.registerItemRenderer(valkyrieIngot, 0, "valkyrie_ingot");
            TinkersAether.proxy.registerItemRenderer(valkyrieNugget, 0, "valkyrie_nugget");
            TinkersAether.proxy.registerItemRenderer(valkBlock,0,"valkyrie_block");
            OreDictionary.registerOre("ingotValkyrie",valkyrieIngot);
            OreDictionary.registerOre("nuggetValkyrie",valkyrieNugget);
            OreDictionary.registerOre("blockValkyrie",valkyrieBlock);
        }
        if(TAConfig.swet) {
            registry.register(swetCrystal);
            TinkersAether.proxy.registerItemRenderer(swetCrystal, 0, "swet_crystal");
            OreDictionary.registerOre("slimecrystal",swetCrystal);
            OreDictionary.registerOre("slimecrystalSwet",swetCrystal);
        }
        TinkersAether.logger.info("Base Module - Items Registered");
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        if(TAConfig.valkyrie) {
            valkyrieBlock.setHarvestLevel("pickaxe",3);
            valkyrieBlock.setHardness(4.0f);
            registry.register(valkyrieBlock);
        }
        TinkersAether.logger.info("Base Module - Blocks Registered");
    }

	@SubscribeEvent
	public void onRegisterEnchantmentEvent(RegistryEvent.Register<AetherEnchantment> event) {
		IForgeRegistry<AetherEnchantment> registry = event.getRegistry();
    	if(TAConfig.valkyrie&&TAConfig.valkyrieMedalEnchant) {
			registry.register(new AetherEnchantment(new ItemStack(ItemsAether.victory_medal),new ItemStack(valkyrieNugget),250));
		}
		TinkersAether.logger.info("Base Module - Enchantment Recipes Registered");
	}

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
    	if(TAConfig.valkyrie&&TAConfig.valkyrieDungeonChest) {
			if(event.getName().toString().equals("aether_legacy:chests/silver_dungeon_chest")) {
				LootEntry entry = new LootEntryTable(new ResourceLocation(TinkersAether.modid,"inject/silver_dungeon_chest"),100,10,new LootCondition[0], "ta_inject_entry");
				event.getTable().addPool(new LootPool(new LootEntry[]{entry},new LootCondition[0],new RandomValueRange(1),new RandomValueRange(0,1),"ta_inject_pool"));
			} else if(event.getName().toString().equals("aether_legacy:chests/silver_dungeon_reward")) {
				LootEntry entry = new LootEntryTable(new ResourceLocation(TinkersAether.modid,"inject/silver_dungeon_reward"),100,0,new LootCondition[0], "ta_inject_entry");
				event.getTable().addPool(new LootPool(new LootEntry[]{entry},new LootCondition[0],new RandomValueRange(1),new RandomValueRange(0,1),"ta_inject_pool"));
			}
		}
	}
}
