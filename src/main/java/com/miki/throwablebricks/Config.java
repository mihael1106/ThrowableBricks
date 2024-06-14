package com.miki.throwablebricks;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ThrowableBricks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue DAMAGE = BUILDER
            .comment("Amount of damage the brick does (default:4) [0-2147483647]")
            .defineInRange("damage", 4, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int damage;

    @SubscribeEvent
    static void onLoad(final ModConfig.ModConfigEvent event)
    {
        damage = DAMAGE.get();
    }
}
