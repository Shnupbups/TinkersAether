package shnupbups.tinkersaether.traits;

import com.legacy.aether.entities.passive.mountable.EntitySwet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Swetty extends TATrait {

    public static final Swetty swetty = new Swetty();

    private static float chance = 0.0033f;

    public Swetty() {
        super("swetty", 0x7777FF);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if(wasEffective && !world.isRemote && random.nextFloat() < chance) {
            spawnSwet(player, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world);
        }
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(!target.isEntityAlive() && !target.getEntityWorld().isRemote && random.nextFloat() < chance) {
            spawnSwet(player, target.posX, target.posY, target.posZ, target.getEntityWorld());
        }
    }

    protected void spawnSwet(EntityLivingBase player, double x, double y, double z, World world) {
        EntitySwet entity = new EntitySwet(world);
        entity.setPosition(x, y, z);
        world.spawnEntity(entity);
        entity.setLastAttackedEntity(player);
        entity.playLivingSound();
    }
}
