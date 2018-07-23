package shnupbups.tinkersaether.traits;

import com.legacy.aether.mc1122.items.ItemsAether;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Festive extends AbstractTrait {
    public static final Festive festive = new Festive();

    public Festive() {
        super("festive",0xEEEE11);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if (!world.isRemote && world.rand.nextBoolean()) {
            EntityItem amb = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
            amb.setItem(new ItemStack(ItemsAether.candy_cane, 1));
            world.spawnEntity(amb);
        }
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(!target.isEntityAlive() && !target.getEntityWorld().isRemote && random.nextBoolean()) {
            EntityItem amb = new EntityItem(target.getEntityWorld(), target.getPosition().getX(), target.getPosition().getY(), target.getPosition().getZ());
            amb.setItem(new ItemStack(ItemsAether.candy_cane, 1));
            target.getEntityWorld().spawnEntity(amb);
        }
    }
}
