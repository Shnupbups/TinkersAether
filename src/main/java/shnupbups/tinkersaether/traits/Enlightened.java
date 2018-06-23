package shnupbups.tinkersaether.traits;

import com.legacy.aether.items.ItemsAether;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Enlightened extends AbstractTrait {
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
}
