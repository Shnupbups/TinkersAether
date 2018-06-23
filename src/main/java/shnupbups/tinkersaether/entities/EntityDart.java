package shnupbups.tinkersaether.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;

public class EntityDart extends EntityProjectileBase {

    private int ticksInAir;

    public EntityDart(World world) {
        super(world);
    }

    public EntityDart(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    public EntityDart(World world, EntityPlayer player, float speed, float inaccuracy, float power, ItemStack stack, ItemStack launchingStack) {
        super(world, player, speed, inaccuracy, power, stack, launchingStack);
    }

    @Override
    protected void playHitBlockSound(float speed, IBlockState state) {
        this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksInAir == 500) {
            this.setDead();
        }

        if (!this.onGround) {
            ++this.ticksInAir;
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void setNoGravity(boolean flight) {

    }
}
