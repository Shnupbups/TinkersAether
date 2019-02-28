package shnupbups.tinkersaether.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shnupbups.tinkersaether.traits.Reach;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class HandlerExtendedAttack implements IMessageHandler<MessageExtendedAttack,IMessage> {
    @Override
    public IMessage onMessage(MessageExtendedAttack message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        player.getServer().addScheduledTask( new Runnable() {
            @Override
            public void run() {
                Entity entity = player.getEntityWorld().getEntityByID(message.getEntityId());
                ItemStack stack = player.getHeldItemMainhand();
                if(stack.isEmpty() || !(stack.getItem() instanceof TinkerToolCore) || entity == null) {
                    return;
                }
                if(ToolHelper.getTraits(stack).contains(Reach.reach)) {
                    player.attackTargetEntityWithCurrentItem(entity);
                }
            }
        });
        return null;
    }
}
