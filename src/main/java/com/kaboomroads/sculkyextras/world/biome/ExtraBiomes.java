package com.kaboomroads.sculkyextras.world.biome;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraBiomes {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, SculkyExtras.MOD_ID);

    public static final ResourceKey<Biome> SCULK_DEPTHS = createKey("sculk_depths");
    public static final ResourceKey<Biome> DESOLATE_SLATELANDS = createKey("desolate_slatelands");
    public static final ResourceKey<Biome> ABYSSAL_FOREST = createKey("abyssal_forest");

    public static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(BIOMES.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
