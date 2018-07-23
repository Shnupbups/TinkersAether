package shnupbups.tinkersaether.traits;

import com.legacy.aether.mc1122.blocks.BlocksAether;
import com.legacy.aether.mc1122.blocks.util.EnumLogType;
import com.legacy.aether.mc1122.items.ItemsAether;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class Gilded extends AbstractTrait {
    public static final Gilded gilded = new Gilded();

    public Gilded() {
        super("gilded",0xFFDD11);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if (!world.isRemote && state.getBlock().equals(BlocksAether.aether_log) && wasEffective) {
            if (state.getValue(PropertyEnum.create("aether_logs", EnumLogType.class)) == EnumLogType.Oak) {
                EntityItem amb = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
                amb.setItem(new ItemStack(ItemsAether.golden_amber, 1 + random.nextInt(2)));
                world.spawnEntity(amb);
            }
        }
    }
}
