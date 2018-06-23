package shnupbups.tinkersaether.render;

import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;
import shnupbups.tinkersaether.entities.EntityDart;
import slimeknights.tconstruct.library.client.renderer.RenderProjectileBase;

public class RenderDart extends RenderProjectileBase<EntityDart> {
    public RenderDart(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected void customCustomRendering(EntityDart entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glRotatef(90,1,0,0);
        GL11.glTranslatef(0,0.2f,0f);
    }
}
