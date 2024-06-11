package com.miki.throwablebricks;

import com.miki.throwablebricks.entity.EntityInit;
import com.miki.throwablebricks.item.ItemInit;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ThrowableBricks.MODID)
public class ThrowableBricks
{
    public static final String MODID = "throwablebricks";

    public ThrowableBricks()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientSetup);

        ItemInit.OVERRIDE_ITEMS.register(modEventBus);
        EntityInit.ENTITY_TYPE.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(EntityInit.BRICK.get(), ThrownItemRenderer::new);
    }
}
