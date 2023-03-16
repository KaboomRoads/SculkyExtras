package com.kaboomroads.sculkyextras.world.structure.piece;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ExtraStructureSets {
    public static final DeferredRegister<StructureSet> STRUCTURE_SETS =
            DeferredRegister.create(Registries.STRUCTURE_SET, SculkyExtras.MOD_ID);

    public static final ResourceKey<StructureSet> SCULK_FOSSIL = createKey("sculk_fossil");

    public static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(STRUCTURE_SETS.getRegistryKey(), new ResourceLocation(SculkyExtras.MOD_ID, name));
    }

    public static void register(IEventBus eventBus) {
        STRUCTURE_SETS.register(eventBus);
    }
}