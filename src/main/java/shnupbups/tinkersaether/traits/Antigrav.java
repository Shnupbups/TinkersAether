package shnupbups.tinkersaether.traits;

import com.gildedgames.the_aether.entities.block.EntityFloatingBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class Antigrav extends TATrait{
    public static final Antigrav antigrav = new Antigrav();

    public Antigrav() {
        super("antigrav",0xCC55AA);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void floatBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack heldItem = event.getItemStack();
        if (event.getWorld().isRemote
                || !event.getEntityPlayer().isSneaking()
                || heldItem == null
                || !isToolWithTrait(heldItem)
                || ToolHelper.getCurrentDurability(event.getItemStack()) < 4)
            return;
        if ((heldItem.getDestroySpeed(world.getBlockState(pos)) > 1.0f || ForgeHooks.isToolEffective(world, pos, heldItem)) && world.isAirBlock(pos.up())) {
            if (world.getTileEntity(pos) != null || world.getBlockState(pos).getBlockHardness(world, pos) == -1.0F) {
                return;
            }

            if (!world.isRemote) {
                EntityFloatingBlock ent = new EntityFloatingBlock(world, pos.getX(), pos.getY(), pos.getZ(), world.getBlockState(pos));
                world.spawnEntity(ent);
                world.setBlockToAir(pos);
            }

            ToolHelper.damageTool(event.getItemStack(), 4, event.getEntityLiving());
        }
    }
}
