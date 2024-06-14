package com.miki.throwablebricks.entity;

import com.miki.throwablebricks.ThrowableBricks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, ThrowableBricks.MODID);

    public static final RegistryObject<EntityType<Brick>> BRICK = ENTITY_TYPE.register("brick",
            () -> EntityType.Builder.of(Brick::create, EntityClassification.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(new ResourceLocation(ThrowableBricks.MODID, "brick").toString()));
}
