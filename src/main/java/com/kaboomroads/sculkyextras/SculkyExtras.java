package com.kaboomroads.sculkyextras;

import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.block.ExtraWoodTypes;
import com.kaboomroads.sculkyextras.block.entity.ExtraBlockEntities;
import com.kaboomroads.sculkyextras.creativemodetab.ExtraCreativeModeTabs;
import com.kaboomroads.sculkyextras.entity.ExtraEntityTypes;
import com.kaboomroads.sculkyextras.item.ExtraItems;
import com.kaboomroads.sculkyextras.poi.ExtraPOIs;
import com.kaboomroads.sculkyextras.tag.ModTags;
import com.kaboomroads.sculkyextras.world.biome.ExtraBiomes;
import com.kaboomroads.sculkyextras.world.dimension.ExtraDimensionTypes;
import com.kaboomroads.sculkyextras.world.dimension.ExtraDimensions;
import com.kaboomroads.sculkyextras.world.feature.ExtraConfiguredFeatures;
import com.kaboomroads.sculkyextras.world.feature.ExtraFeatures;
import com.kaboomroads.sculkyextras.world.feature.ExtraPlacedFeatures;
import com.kaboomroads.sculkyextras.world.structure.ExtraStructures;
import com.kaboomroads.sculkyextras.world.structure.piece.ExtraStructurePieces;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SculkyExtras.MOD_ID)
public class SculkyExtras {
    public static final String MOD_ID = "sculkyextras";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SculkyExtras() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ExtraItems.register(eventBus);
        ExtraBlocks.register(eventBus);
        ExtraBlockEntities.register(eventBus);
        ExtraEntityTypes.register(eventBus);
        ExtraPOIs.register(eventBus);
        ExtraFeatures.register(eventBus);
        ExtraConfiguredFeatures.register(eventBus);
        ExtraPlacedFeatures.register(eventBus);
        ExtraStructurePieces.register(eventBus);
        ExtraStructures.register(eventBus);
        ExtraBiomes.register(eventBus);
        ExtraDimensions.register(eventBus);
        ExtraDimensionTypes.register(eventBus);
        ExtraWoodTypes.register();
        ExtraCreativeModeTabs.register(eventBus);
        ModTags.init();
        eventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ExtraWoodTypes.ABYSSAL);
        });
    }
}
