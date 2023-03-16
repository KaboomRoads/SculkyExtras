package com.kaboomroads.sculkyextras.world.feature;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, SculkyExtras.MOD_ID);

    public static final ResourceKey<PlacedFeature> ABYSSAL_TREE = createKey("abyssal_tree");
    public static final ResourceKey<PlacedFeature> ORE_DEEPSLATE = createKey("ore_deepslate");
    public static final ResourceKey<PlacedFeature> ORE_SCULK = createKey("ore_sculk");
    public static final ResourceKey<PlacedFeature> ORE_TUFF = createKey("ore_tuff");
    public static final ResourceKey<PlacedFeature> SCULK_LUSTRE = createKey("sculk_lustre");
    public static final ResourceKey<PlacedFeature> SCULK_LUSTRE_EXTRA = createKey("sculk_lustre_extras");
    public static final ResourceKey<PlacedFeature> SCULK_PATCH_ABYSSAL_FOREST = createKey("sculk_patch_abyssal_forest");
    public static final ResourceKey<PlacedFeature> SCULK_PATCH_OTHERSIDE = createKey("sculk_patch_otherside");
    public static final ResourceKey<PlacedFeature> SCULK_TENDRIL = createKey("sculk_tendril");
    public static final ResourceKey<PlacedFeature> SPRING_SCULK = createKey("spring_sculk");

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(PLACED_FEATURES.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
