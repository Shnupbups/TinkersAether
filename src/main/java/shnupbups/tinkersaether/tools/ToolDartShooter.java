package shnupbups.tinkersaether.tools;

import com.google.common.collect.ImmutableList;
import com.legacy.aether.entities.projectile.darts.EntityDartEnchanted;
import com.legacy.aether.entities.projectile.darts.EntityDartGolden;
import com.legacy.aether.entities.projectile.darts.EntityDartPoison;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import shnupbups.tinkersaether.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ranged.BowCore;
import slimeknights.tconstruct.library.utils.ToolHelper;

import javax.annotation.Nonnull;
import java.util.List;

public class ToolDartShooter extends BowCore {

    public ToolDartShooter() {
        super(PartMaterialType.bow(ModuleTools.tube),
                PartMaterialType.extra(ModuleTools.mouthpiece));

        this.setUnlocalizedName("dart_shooter").setRegistryName("dart_shooter");
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.isInCreativeTab(tab)) {
            addDefaultSubItems(subItems, null, null);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        // has to be done in onUpdate because onTickUsing is too early and gets overwritten. bleh.
        preventSlowDown(entityIn, 1.0f);

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public int getDrawTime() {
        return 1;
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public float baseProjectileDamage() {
        return 1.0f;
    }

    @Override
    public float projectileDamageModifier() {
        return 1.0f;
    }

    @Override
    public void playShootSound(float power, World world, EntityPlayer entityPlayer) {
        world.playSound(null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 0.5f + itemRand.nextFloat() * 0.1f);
    }

    @Override
    public ProjectileLauncherNBT buildTagData(List<Material> materials) {
        ProjectileLauncherNBT data = new ProjectileLauncherNBT();
        BowMaterialStats tube = materials.get(0).getStatsOrUnknown(MaterialTypes.BOW);
        HeadMaterialStats head = materials.get(0).getStatsOrUnknown(MaterialTypes.HEAD);
        ExtraMaterialStats mouthpiece = materials.get(1).getStatsOrUnknown(MaterialTypes.EXTRA);
        HandleMaterialStats handle = materials.get(1).getStatsOrUnknown(MaterialTypes.HANDLE);

        data.limb(tube);
        data.head(head);
        data.extra(mouthpiece);
        data.handle(handle);

        data.bonusDamage *= 0.5f;

        return data;
    }

    @Override
    public float damagePotential() {
        return 0.2f;
    }

    @Override
    public double attackSpeed() {
        return 4;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if(!ToolHelper.isBroken(itemStackIn)) {
            super.onPlayerStoppedUsing(itemStackIn, worldIn, playerIn, 0);
        } else {
            return super.onItemRightClick(worldIn, playerIn, hand);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
    }

    @Nonnull
    protected ItemStack getCreativeProjectileStack() {
        return new ItemStack(ItemsAether.dart);
    }

    private ImmutableList<Item> dartMatches = null;

    @Override
    protected List<Item> getAmmoItems() {
        if(dartMatches == null) {
            ImmutableList.Builder<Item> builder = ImmutableList.builder();
            if(ModuleTools.dart != null) {
                builder.add(ModuleTools.dart);
            }
            builder.add(ItemsAether.dart);
            dartMatches = builder.build();
        }
        return dartMatches;
    }

    @Override
    public EntityArrow getProjectileEntity(ItemStack ammo, ItemStack bow, World world, EntityPlayer player, float power, float inaccuracy, float progress, boolean usedAmmo) {
        if(ammo.getItem() == ItemsAether.dart) {
            EntityArrow projectile;
            if(ammo.getMetadata()==0) {
                projectile = new EntityDartGolden(world, player);
            } else if(ammo.getMetadata()==1) {
                projectile = new EntityDartPoison(world, player);
            } else {
                projectile = new EntityDartEnchanted(world, player);
            }
            projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, power, inaccuracy);
            if(player.capabilities.isCreativeMode) {
                projectile.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            }
            else if(!usedAmmo) {
                projectile.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
            }
            return projectile;
        }
        return super.getProjectileEntity(ammo,bow,world,player,power,inaccuracy,progress,usedAmmo);
    }
}
