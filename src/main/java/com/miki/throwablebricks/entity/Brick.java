package com.miki.throwablebricks.entity;

import com.miki.throwablebricks.Config;
import com.miki.throwablebricks.item.ItemInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class Brick extends ThrowableItemProjectile {
    public Brick(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public static Brick create(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        return new Brick(entityType, level);
    }

    public Brick(Level p_37399_, LivingEntity p_37400_) {
        super(EntityInit.BRICK.get(), p_37400_, p_37399_);
    }

    protected @NotNull Item getDefaultItem() {
        return Items.BRICK;
    }

    private ParticleOptions getParticle() {
        ItemStack stack = this.getItemRaw();
        return stack.isEmpty() ? new ItemParticleOption(ParticleTypes.ITEM, ItemInit.BRICK.get().getDefaultInstance()) : new ItemParticleOption(ParticleTypes.ITEM, stack);
    }

    public void handleEntityEvent(byte type) {
        if (type == 3) {
            ParticleOptions particle = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        hitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), Config.damage);
    }

    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }

    }
}
