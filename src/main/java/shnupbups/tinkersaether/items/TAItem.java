package shnupbups.tinkersaether.items;

import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.item.Item;
import shnupbups.tinkersaether.TinkersAether;

public class TAItem extends Item {

    public TAItem(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setRegistryName(TinkersAether.modid, name);
        this.setCreativeTab(AetherCreativeTabs.material);
    }
}
