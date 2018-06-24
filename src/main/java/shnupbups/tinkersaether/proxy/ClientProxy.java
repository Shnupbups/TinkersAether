package shnupbups.tinkersaether.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import shnupbups.tinkersaether.TinkersAether;
import shnupbups.tinkersaether.entities.EntityDart;
import shnupbups.tinkersaether.modules.ModuleTools;
import shnupbups.tinkersaether.render.RenderDart;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

import javax.annotation.Nonnull;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(TinkersAether.modid + ":" + id, "inventory"));
    }

    @Override
    public void setRenderInfo(Material mat, int color) {
        mat.setRenderInfo(color);
    }

    @Override
    public void setRenderInfo(Material mat, int lo, int mid, int hi) {
        mat.setRenderInfo(new MaterialRenderInfo.MultiColor(lo, mid, hi));
    }

    @Override
    public void registerFluidModels(Fluid fluid) {
        if (fluid == null) return;
        Block block = fluid.getBlock();
        if (block != null) {
            Item item = Item.getItemFromBlock(block);
            FluidStateMapper mapper = new FluidStateMapper(fluid);
            if (item != null) {
                ModelBakery.registerItemVariants(item);
                ModelLoader.setCustomMeshDefinition(item, mapper);
            }
            ModelLoader.setCustomStateMapper(block, mapper);
        }
    }

    @Override
    public void registerModels() {
        super.registerModels();

        // entities
        RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, RenderDart::new);
        TinkersAether.logger.info("Aether Tools Module - Models Registered");
    }

    @Override
    public void registerToolModel(ToolCore tc) {
        ModelRegisterUtil.registerToolModel(tc);
    }

    @Override
    public void registerModifierModel(IModifier mod, ResourceLocation rl) {
        ModelRegisterUtil.registerModifierModel(mod, rl);
    }

    @Override
    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
        ModelRegisterUtil.registerPartModel(part);
    }

    @Override
    public void initToolGuis() {
        if (ModuleTools.dartShooter != null) {
            ToolBuildGuiInfo dartShooterInfo = new ToolBuildGuiInfo(ModuleTools.dartShooter);
            dartShooterInfo.addSlotPosition(32 - 9, 41 - 9);
            dartShooterInfo.addSlotPosition(32 + 9, 41 + 9);
            TinkerRegistryClient.addToolBuilding(dartShooterInfo);
        }
        if (ModuleTools.dart != null) {
            ToolBuildGuiInfo dartInfo = new ToolBuildGuiInfo(ModuleTools.dart);
            dartInfo.addSlotPosition(32, 41);
            dartInfo.addSlotPosition(32 - 18, 41 - 18);
            dartInfo.addSlotPosition(32 + 18, 41 + 18);
            TinkerRegistryClient.addToolBuilding(dartInfo);
        }
    }

    public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
        public final Fluid fluid;
        public final ModelResourceLocation location;

        public FluidStateMapper(Fluid fluid) {
            this.fluid = fluid;
            this.location = new ModelResourceLocation(new ResourceLocation(TinkersAether.modid, "fluid_block"),
                    fluid.getName());
        }

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return location;
        }

        @Nonnull
        @Override
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            return location;
        }
    }
}
