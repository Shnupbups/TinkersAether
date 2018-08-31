package shnupbups.tinkersaether.fluids;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shnupbups.tinkersaether.TinkersAether;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class FluidHelper {
    public static FluidMolten createFluid(Material material, int temperature) {
        FluidMolten fluid = new FluidMolten(material.identifier, material.materialTextColor);
        fluid.setTemperature(temperature);
        FluidRegistry.registerFluid(fluid);
        BlockMolten blockFluid = new BlockMolten(fluid);
        blockFluid.setUnlocalizedName("molten_"+fluid.getName());
        blockFluid.setRegistryName("molten_"+fluid.getName());
        ForgeRegistries.BLOCKS.register(blockFluid);
        FluidRegistry.addBucketForFluid(fluid);
        TinkersAether.proxy.registerFluidModels(fluid);
        return fluid;
    }
}
