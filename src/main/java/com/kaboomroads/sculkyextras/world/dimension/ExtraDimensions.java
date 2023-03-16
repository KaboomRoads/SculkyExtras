package com.kaboomroads.sculkyextras.world.dimension;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraDimensions {
    public static final DeferredRegister<Level> DIMENSIONS =
            DeferredRegister.create(Registries.DIMENSION, SculkyExtras.MOD_ID);

    public static final ResourceKey<Level> OTHERSIDE = createKey("otherside");

    public static ResourceKey<Level> createKey(String name) {
        return ResourceKey.create(DIMENSIONS.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        DIMENSIONS.register(eventBus);
    }
}
