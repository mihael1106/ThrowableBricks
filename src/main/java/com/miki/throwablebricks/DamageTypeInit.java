package com.miki.throwablebricks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypeInit {
    public static final ResourceKey<DamageType> BRICK = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ThrowableBricks.MODID, "brick"));
}
