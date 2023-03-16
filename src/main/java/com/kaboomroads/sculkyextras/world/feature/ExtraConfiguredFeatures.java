package com.kaboomroads.sculkyextras.world.feature;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, SculkyExtras.MOD_ID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> ABYSSAL_TREE = createKey("abyssal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE = createKey("ore_deepslate");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SCULK = createKey("ore_sculk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TUFF = createKey("ore_tuff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_LUSTRE_EXTRA = createKey("sculk_lustre_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_TENDRIL_PATCH = createKey("sculk_tendril_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_SCULK_OPEN = createKey("spring_sculk_open");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(CONFIGURED_FEATURES.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
