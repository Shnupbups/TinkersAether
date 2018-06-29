package shnupbups.tinkersaether.traits;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.UUID;

public class Reach extends AbstractTrait {
    public static final Reach reach = new Reach();

    public static final AttributeModifier reachModifier = new AttributeModifier(UUID.randomUUID(), "Tinkers Aether Reach Modifier", 5.0D, 0);

    public Reach() {
        super("reach",0xEEEEDD);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if ((event.getEntityLiving() instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ItemStack stack = player.getHeldItemMainhand();
            if (!player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).hasModifier(this.reachModifier) && isToolWithTrait(stack)) {
                player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).applyModifier(this.reachModifier);
            } else if (player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).hasModifier(this.reachModifier) && !isToolWithTrait(stack)) {
                player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).removeModifier(this.reachModifier);
            }
        }
    }
}
