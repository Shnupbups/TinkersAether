package shnupbups.tinkersaether.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import shnupbups.tinkersaether.entities.EntityDart;
import shnupbups.tinkersaether.modules.ModuleBase;
import shnupbups.tinkersaether.modules.ModuleTools;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileNBT;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

public class ToolDart extends ProjectileCore {

    public ToolDart() {
        super(PartMaterialType.arrowShaft(TinkerTools.arrowShaft),
                PartMaterialType.arrowHead(ModuleTools.dartTip),
                PartMaterialType.fletching(TinkerTools.fletching));

        addCategory(Category.NO_MELEE, Category.PROJECTILE);

        this.setUnlocalizedName("dart").setRegistryName("dart");
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.isInCreativeTab(tab)) {
            addDefaultSubItems(subItems, ModuleBase.skyroot, null, TinkerMaterials.feather);
        }
    }

    @Override
    public ProjectileNBT buildTagData(List<Material> materials) {
        ProjectileNBT data = new ProjectileNBT();

        ArrowShaftMaterialStats shaft = materials.get(0).getStatsOrUnknown(MaterialTypes.SHAFT);
        HeadMaterialStats tip = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        FletchingMaterialStats fletching = materials.get(2).getStatsOrUnknown(MaterialTypes.FLETCHING);

        data.head(tip);
        data.fletchings(fletching);
        data.shafts(this, shaft);

        data.attack += 1;

        return data;
    }

    @Override
    public float damagePotential() {
        return 0.5f;
    }

    @Override
    public EntityProjectileBase getProjectile(ItemStack stack, ItemStack bow, World world, EntityPlayer player, float speed, float inaccuracy, float power, boolean usedAmmo) {
        inaccuracy -= (1f - 1f / ProjectileNBT.from(stack).accuracy) * speed / 2f;
        return new EntityDart(world, player, speed, inaccuracy, power, getProjectileStack(stack, world, player, usedAmmo), bow);
    }

    @Override
    public double attackSpeed() {
        return 1;
    }
}
