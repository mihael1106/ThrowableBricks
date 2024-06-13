package com.miki.throwablebricks.entity;

import com.miki.throwablebricks.ThrowableBricks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThrowableBricks.MODID);

    public static final RegistryObject<EntityType<Brick>> BRICK = ENTITY_TYPE.register("brick",
            () -> EntityType.Builder.of(Brick::create, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceLocation.fromNamespaceAndPath(ThrowableBricks.MODID, "brick").toString())
    );
}
