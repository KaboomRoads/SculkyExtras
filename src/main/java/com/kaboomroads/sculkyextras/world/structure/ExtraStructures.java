package com.kaboomroads.sculkyextras.world.structure;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.world.structure.custom.SculkFossilStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtraStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, SculkyExtras.MOD_ID);

    public static final RegistryObject<StructureType<SculkFossilStructure>> SCULK_FOSSIL = STRUCTURES.register("sculk_fossils", () -> explicitStructureTypeTyping(SculkFossilStructure.CODEC));

    public static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}