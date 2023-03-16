package com.kaboomroads.sculkyextras.world.dimension;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraDimensionTypes {
    public static final DeferredRegister<DimensionType> DIMENSION_TYPES =
            DeferredRegister.create(Registries.DIMENSION_TYPE, SculkyExtras.MOD_ID);

    public static final ResourceKey<DimensionType> OTHERSIDE_TYPE = createKey(ExtraDimensions.OTHERSIDE);

    public static ResourceKey<DimensionType> createKey(String name) {
        return ResourceKey.create(DIMENSION_TYPES.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static ResourceKey<DimensionType> createKey(ResourceKey<Level> dimension) {
        return createKey(dimension.location().getPath());
    }

    public static void register(IEventBus eventBus) {
        DIMENSION_TYPES.register(eventBus);
    }
}
