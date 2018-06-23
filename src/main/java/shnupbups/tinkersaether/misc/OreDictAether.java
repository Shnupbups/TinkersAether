package shnupbups.tinkersaether.misc;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictAether {

    public static void register() {
        OreDictionary.registerOre("plankSkyroot", BlocksAether.skyroot_plank);
        OreDictionary.registerOre("logSkyroot",BlocksAether.aether_log);
        OreDictionary.registerOre("logSkyroot",new ItemStack(BlocksAether.aether_log,1,1));
        OreDictionary.registerOre("stickSkyroot", ItemsAether.skyroot_stick);
        OreDictionary.registerOre("holystone",BlocksAether.holystone);
    }
}
