package shnupbups.tinkersaether.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void setRenderInfo(Material mat, int color) {
    }

    public void setRenderInfo(Material mat, int lo, int mid, int hi) {
    }

    public void registerFluidModels(Fluid fluid) {
    }

    public void registerToolModel(ToolCore tc) {
    }

    public void registerModifierModel(IModifier mod, ResourceLocation rl) {
    }

    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
    }

    public void initToolGuis() {
    }

    public void registerModels() {
    }
}
