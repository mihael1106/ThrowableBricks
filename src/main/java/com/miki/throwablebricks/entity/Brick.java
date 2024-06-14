package com.miki.throwablebricks.entity;

import com.miki.throwablebricks.Config;
import com.miki.throwablebricks.item.ItemInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class Brick extends ProjectileItemEntity {
    public Brick(EntityType<? extends ProjectileItemEntity> entityType, World level) {
        super(entityType, level);
    }

    public static Brick create(EntityType<? extends ProjectileItemEntity> entityType, World level) {
        return new Brick(entityType, level);
    }

    public Brick(World p_37399_, LivingEntity p_37400_) {
        super(EntityInit.BRICK.get(), p_37400_, p_37399_);
    }

    protected Item getDefaultItem() {
        return ItemInit.BRICK.get();
    }

    private IParticleData getParticle() {
        ItemStack stack = this.getItemRaw();
        return stack.isEmpty() ? new ItemParticleData(ParticleTypes.ITEM, ItemInit.BRICK.get().getDefaultInstance()) : new ItemParticleData(ParticleTypes.ITEM, stack);
    }


    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte type) {
        if (type == 3) {
            IParticleData particle = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    @ParametersAreNonnullByDefault
    protected void onHitEntity(EntityRayTraceResult traceResult) {
        super.onHitEntity(traceResult);
        traceResult.getEntity().hurt(new IndirectEntityDamageSource("throwablebricks:brick", this, this.getOwner()).setProjectile(), Config.damage);
    }

    @ParametersAreNonnullByDefault
    protected void onHit(RayTraceResult traceResult) {
        super.onHit(traceResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }

    }
}
