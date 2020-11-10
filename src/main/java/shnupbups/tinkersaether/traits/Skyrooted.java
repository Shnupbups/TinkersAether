package shnupbups.tinkersaether.traits;

import com.gildedgames.the_aether.Aether;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class Skyrooted extends TATrait {
    public static final Skyrooted skyrooted = new Skyrooted();

    public Skyrooted() {
        super("skyrooted",0x6C633E);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase entity, boolean wasEffective) {
        if(entity instanceof EntityPlayer) {
            if(tool.getDestroySpeed(world.getBlockState(pos)) > 1.0f || ForgeHooks.isToolEffective(world, pos, tool)) {
                if(state.getProperties().containsKey(PropertyBool.create(Aether.doubleDropNotifier()))) {
                    boolean dropDouble = state.getValue(PropertyBool.create(Aether.doubleDropNotifier())).equals(true);
                    if(dropDouble && !world.isRemote) {
                        NonNullList<ItemStack> drops = NonNullList.create();
                        state.getBlock().getDrops(drops, world, pos, state, 0);
                        for (ItemStack stack:drops) {
                            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
                            item.setItem(stack);
                            world.spawnEntity(item);
                        }
                    }
                }
            }
        }
    }
}
