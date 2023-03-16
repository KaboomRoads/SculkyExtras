package com.kaboomroads.sculkyextras.world.structure.piece.custom;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.world.structure.piece.ExtraStructurePieces;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;

public class SculkFossilPieces {
    public static final ResourceLocation[] FOSSILS = new ResourceLocation[]{new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_1"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_2"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_3"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_4"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_5"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_6"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_7"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_8"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_9"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_10"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_11"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_12"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_13"), new ResourceLocation(SculkyExtras.MOD_ID, "sculk_fossils/fossil_14")};

    public static void addPieces(StructureTemplateManager structureTemplateManager, StructurePieceAccessor pieceAccessor, RandomSource randomSource, BlockPos blockPos) {
        Rotation rotation = Rotation.getRandom(randomSource);
        pieceAccessor.addPiece(new SculkFossilPiece(structureTemplateManager, Util.getRandom(FOSSILS, randomSource), blockPos, rotation));
    }

    public static class SculkFossilPiece extends TemplateStructurePiece {
        public SculkFossilPiece(StructureTemplateManager templateManager, ResourceLocation resourceLocation, BlockPos blockPos, Rotation rotation) {
            super(ExtraStructurePieces.SCULK_FOSSIL.get(), 0, templateManager, resourceLocation, resourceLocation.toString(), makeSettings(rotation), blockPos);
        }

        public SculkFossilPiece(StructureTemplateManager templateManager, CompoundTag compoundTag) {
            super(ExtraStructurePieces.SCULK_FOSSIL.get(), compoundTag, templateManager, (resourceLocation) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot"))));
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation) {
            return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
        }

        protected void addAdditionalSaveData(@NotNull StructurePieceSerializationContext serializationContext, @NotNull CompoundTag compoundTag) {
            super.addAdditionalSaveData(serializationContext, compoundTag);
            compoundTag.putString("Rot", this.placeSettings.getRotation().name());
        }

        protected void handleDataMarker(@NotNull String p_228561_, @NotNull BlockPos p_228562_, @NotNull ServerLevelAccessor p_228563_, @NotNull RandomSource p_228564_, @NotNull BoundingBox p_228565_) {
        }

        public void postProcess(@NotNull WorldGenLevel worldGenLevel, @NotNull StructureManager structureManager, @NotNull ChunkGenerator chunkGenerator, @NotNull RandomSource randomSource, BoundingBox p_228552_, @NotNull ChunkPos chunkPos, @NotNull BlockPos blockPos) {
            p_228552_.encapsulate(this.template.getBoundingBox(this.placeSettings, this.templatePosition));
            super.postProcess(worldGenLevel, structureManager, chunkGenerator, randomSource, p_228552_, chunkPos, blockPos);
        }
    }
}