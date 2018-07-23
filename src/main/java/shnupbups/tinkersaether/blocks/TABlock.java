package shnupbups.tinkersaether.blocks;

import com.legacy.aether.mc1122.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shnupbups.tinkersaether.TinkersAether;

public class TABlock extends Block {

    private boolean beaconBase = false;

    public TABlock(String name, Material material) {
        super(material);
        this.setUnlocalizedName(name);
        this.setRegistryName(TinkersAether.modid, name);
        this.setCreativeTab(AetherCreativeTabs.blocks);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
        return beaconBase;
    }

    public TABlock setBeaconBase() {
        this.beaconBase = true;
        return this;
    }
}
