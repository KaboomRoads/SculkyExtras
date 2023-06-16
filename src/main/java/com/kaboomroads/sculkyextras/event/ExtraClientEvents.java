package com.kaboomroads.sculkyextras.event;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.entity.ExtraBlockEntities;
import com.kaboomroads.sculkyextras.entity.ExtraEntityTypes;
import com.kaboomroads.sculkyextras.entity.client.ModBoatRenderer;
import com.kaboomroads.sculkyextras.entity.custom.ModBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SculkyExtras.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ExtraClientEvents {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition boatModel = BoatModel.createBodyModel();
        LayerDefinition chestBoatModel = ChestBoatModel.createBodyModel();
        for (ModBoat.Type type : ModBoat.Type.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(type, false), () -> boatModel);
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(type, true), () -> chestBoatModel);
        }
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ExtraBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ExtraBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        event.registerEntityRenderer(ExtraEntityTypes.BOAT.get(), context -> new ModBoatRenderer(context, false, false));
        event.registerEntityRenderer(ExtraEntityTypes.CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true, false));
    }
}
