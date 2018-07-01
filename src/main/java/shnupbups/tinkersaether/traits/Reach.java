package shnupbups.tinkersaether.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shnupbups.tinkersaether.TinkersAether;
import shnupbups.tinkersaether.network.MessageExtendedAttack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;
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

    @SubscribeEvent
    public void onMouseClick(PlayerInteractEvent.LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if(isToolWithTrait(stack)) {
            Vec3d playerVision = player.getLookVec();
            AxisAlignedBB reachDistance = player.getEntityBoundingBox().grow(10.0D);
            List<Entity> locatedEntities = player.world.getEntitiesWithinAABB(Entity.class, reachDistance);
            Entity found = null;
            double foundLen = 0.0D;
            for (Object o : locatedEntities) {
                if (o == player) {
                    continue;
                }
                Entity ent = (Entity) o;
                if (!ent.canBeCollidedWith()) {
                    continue;
                }
                Vec3d vec = new Vec3d(ent.posX - player.posX, ent.getEntityBoundingBox().minY + ent.height / 2f - player.posY - player.getEyeHeight(), ent.posZ - player.posZ);
                double len = vec.lengthVector();
                if (len > 10.0F) {
                    continue;
                }
                vec = vec.normalize();
                double dot = playerVision.dotProduct(vec);
                if (dot < 1.0 - 0.125 / len || !player.canEntityBeSeen(ent)) {
                    continue;
                }
                if (foundLen == 0.0 || len < foundLen) {
                    found = ent;
                    foundLen = len;
                }
            }
            if (found != null && player.getRidingEntity() != found) {
                TinkersAether.network.sendToServer(new MessageExtendedAttack(found.getEntityId()));
            }
        }
    }
}
