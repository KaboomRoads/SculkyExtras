package com.kaboomroads.sculkyextras.world.structure.piece;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.NetherFossilPieces;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtraStructurePieces {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES = DeferredRegister.create(Registries.STRUCTURE_PIECE, SculkyExtras.MOD_ID);

    public static final RegistryObject<StructurePieceType> SCULK_FOSSIL = STRUCTURE_PIECES.register("sculk_fossil", () -> get(NetherFossilPieces.NetherFossilPiece::new));

    private static StructurePieceType get(StructurePieceType.StructureTemplateType structureTemplateType) {
        return structureTemplateType;
    }

    public static void register(IEventBus eventBus) {
        STRUCTURE_PIECES.register(eventBus);
    }
}