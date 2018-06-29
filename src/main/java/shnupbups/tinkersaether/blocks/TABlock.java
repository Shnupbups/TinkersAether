package shnupbups.tinkersaether.blocks;

import com.legacy.aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shnupbups.tinkersaether.TinkersAether;

public class TABlock extends Block {

    public TABlock(String name, Material material) {
        super(material);
        this.setUnlocalizedName(name);
        this.setRegistryName(TinkersAether.modid, name);
        this.setCreativeTab(AetherCreativeTabs.blocks);
    }
}
