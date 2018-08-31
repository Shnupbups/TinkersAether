package shnupbups.tinkersaether.traits;

import com.legacy.aether.items.ItemsAether;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Enlightened extends TATrait {
    public static final Enlightened enlightened = new Enlightened();

    public Enlightened() {
        super("enlightened",0xEEEE11);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if (!world.isRemote && world.rand.nextInt(100) <= 5) {
            EntityItem amb = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
            amb.setItem(new ItemStack(ItemsAether.ambrosium_shard, 1));
            world.spawnEntity(amb);
        }
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(!target.isEntityAlive() && !target.getEntityWorld().isRemote && random.nextInt(20) == 0) {
            EntityItem amb = new EntityItem(target.getEntityWorld(), target.getPosition().getX(), target.getPosition().getY(), target.getPosition().getZ());
            amb.setItem(new ItemStack(ItemsAether.ambrosium_shard, 1));
            target.getEntityWorld().spawnEntity(amb);
        }
    }
}
