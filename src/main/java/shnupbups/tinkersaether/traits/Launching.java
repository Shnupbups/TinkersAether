package shnupbups.tinkersaether.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;

public class Launching extends TATrait {

    public static final Launching launching = new Launching();

    public Launching() {
        super("launching",0xCC55AA);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        target.addVelocity(0.0D, 1.0D, 0.0D);
        if (target instanceof EntityPlayerMP) {
            ((EntityPlayerMP)target).connection.sendPacket(new SPacketEntityVelocity(target));
        }
    }
}
