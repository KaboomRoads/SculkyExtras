package com.kaboomroads.sculkyextras.event;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.block.entity.ExtraBlockEntities;
import com.kaboomroads.sculkyextras.entity.ExtraEntityTypes;
import com.kaboomroads.sculkyextras.entity.client.ModBoatRenderer;
import com.kaboomroads.sculkyextras.entity.custom.ModBoat;
import com.kaboomroads.sculkyextras.item.ExtraItems;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
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

    @SubscribeEvent
    public static void onCreativeModeTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(SculkyExtras.MOD_ID, "sculkyextras"), builder -> builder
                .icon(() -> new ItemStack(ExtraItems.WARDEN_HEART.get()))
                .title(Component.translatable("itemGroup.sculkyextras"))
                .displayItems((flagSet, output, b) -> {
                    output.accept(ExtraItems.WARDEN_HEART.get());
                    output.accept(ExtraBlocks.OTHERSTONE.get());
                    output.accept(ExtraBlocks.SCULK_LUSTRE.get());
                    output.accept(ExtraBlocks.SCULK_SOIL.get());
                    output.accept(ExtraBlocks.SCULK_TENDRIL.get());
                    output.accept(ExtraBlocks.ABYSSAL_LOG.get());
                    output.accept(ExtraBlocks.STRIPPED_ABYSSAL_LOG.get());
                    output.accept(ExtraBlocks.ABYSSAL_WOOD.get());
                    output.accept(ExtraBlocks.STRIPPED_ABYSSAL_WOOD.get());
                    output.accept(ExtraBlocks.ABYSSAL_PLANKS.get());
                    output.accept(ExtraBlocks.ABYSSAL_LEAVES.get());
                    output.accept(ExtraBlocks.ABYSSAL_SAPLING.get());
                    output.accept(ExtraBlocks.ABYSSAL_STAIRS.get());
                    output.accept(ExtraBlocks.ABYSSAL_SLAB.get());
                    output.accept(ExtraBlocks.ABYSSAL_FENCE.get());
                    output.accept(ExtraBlocks.ABYSSAL_FENCE_GATE.get());
                    output.accept(ExtraBlocks.ABYSSAL_DOOR.get());
                    output.accept(ExtraBlocks.ABYSSAL_TRAPDOOR.get());
                    output.accept(ExtraBlocks.ABYSSAL_BUTTON.get());
                    output.accept(ExtraBlocks.ABYSSAL_PRESSURE_PLATE.get());
                    output.accept(ExtraItems.ABYSSAL_SIGN.get());
                    output.accept(ExtraItems.ABYSSAL_HANGING_SIGN.get());
                    output.accept(ExtraItems.ABYSSAL_BOAT.get());
                    output.accept(ExtraItems.ABYSSAL_CHEST_BOAT.get());
                }).build());
    }
}
