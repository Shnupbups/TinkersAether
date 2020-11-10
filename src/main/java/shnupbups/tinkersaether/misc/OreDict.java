package shnupbups.tinkersaether.misc;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDict {

	private static final int WILDCARD = OreDictionary.WILDCARD_VALUE;

    public static void register() {
        OreDictionary.registerOre("plankSkyroot", BlocksAether.skyroot_plank);
        OreDictionary.registerOre("logSkyroot",new ItemStack(BlocksAether.aether_log,1,WILDCARD));
        OreDictionary.registerOre("stickSkyroot", ItemsAether.skyroot_stick);

        OreDictionary.registerOre("holystone",BlocksAether.holystone);
		OreDictionary.registerOre("icestone",BlocksAether.icestone);

        OreDictionary.registerOre("gemGoldenAmber",ItemsAether.golden_amber);

        OreDictionary.registerOre("slimeball",ItemsAether.swetty_ball);
        OreDictionary.registerOre("slimeballSwet",ItemsAether.swetty_ball);

        OreDictionary.registerOre("aercloud",new ItemStack(BlocksAether.aercloud,1,WILDCARD));
        OreDictionary.registerOre("aercloudCold",new ItemStack(BlocksAether.aercloud,1,0));
        OreDictionary.registerOre("aercloudBlue",new ItemStack(BlocksAether.aercloud,1,1));
        OreDictionary.registerOre("aercloudGold",new ItemStack(BlocksAether.aercloud,1,2));

        OreDictionary.registerOre("candyCane",ItemsAether.candy_cane);

		OreDictionary.registerOre("feather",ItemsAether.golden_feather);
		OreDictionary.registerOre("featherGold",ItemsAether.golden_feather);

		OreDictionary.registerOre("treeLeavesSkyroot", new ItemStack(BlocksAether.aether_leaves, 1, 0));
		OreDictionary.registerOre("treeLeavesGoldenOak", new ItemStack(BlocksAether.aether_leaves, 1, 1));
		OreDictionary.registerOre("treeLeavesCrystal", new ItemStack(BlocksAether.crystal_leaves, 1, WILDCARD));
		OreDictionary.registerOre("treeLeavesHoliday", new ItemStack(BlocksAether.holiday_leaves, 1, WILDCARD));
    }
}
