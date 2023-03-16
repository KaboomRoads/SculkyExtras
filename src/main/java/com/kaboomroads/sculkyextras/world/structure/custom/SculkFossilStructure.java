package com.kaboomroads.sculkyextras.world.structure.custom;

import com.kaboomroads.sculkyextras.world.structure.ExtraStructures;
import com.kaboomroads.sculkyextras.world.structure.piece.custom.SculkFossilPieces;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SculkFossilStructure extends Structure {
    public static final Codec<SculkFossilStructure> CODEC = RecordCodecBuilder.create((structureInstance) -> structureInstance.group(settingsCodec(structureInstance), HeightProvider.CODEC.fieldOf("height").forGetter((fossilStructure) -> fossilStructure.height)).apply(structureInstance, SculkFossilStructure::new));
    public final HeightProvider height;

    public SculkFossilStructure(Structure.StructureSettings structureSettings, HeightProvider heightProvider) {
        super(structureSettings);
        this.height = heightProvider;
    }

    @NotNull
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext generationContext) {
        WorldgenRandom worldgenrandom = generationContext.random();
        int i = generationContext.chunkPos().getMinBlockX() + worldgenrandom.nextInt(16);
        int j = generationContext.chunkPos().getMinBlockZ() + worldgenrandom.nextInt(16);
        int k = generationContext.chunkGenerator().getSeaLevel();
        WorldGenerationContext worldgenerationcontext = new WorldGenerationContext(generationContext.chunkGenerator(), generationContext.heightAccessor());
        int l = this.height.sample(worldgenrandom, worldgenerationcontext);
        NoiseColumn noisecolumn = generationContext.chunkGenerator().getBaseColumn(i, j, generationContext.heightAccessor(), generationContext.randomState());
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(i, l, j);
        while (l > k) {
            BlockState blockstate = noisecolumn.getBlock(l);
            --l;
            BlockState blockstate1 = noisecolumn.getBlock(l);
            if (blockstate.isAir() && blockstate1.isFaceSturdy(EmptyBlockGetter.INSTANCE, blockpos$mutableblockpos.setY(l), Direction.UP))
                break;
        }
        if (l <= k) return Optional.empty();
        else {
            BlockPos blockpos = new BlockPos(i, l, j);
            return Optional.of(new Structure.GenerationStub(blockpos, (p_228581_) -> {
                SculkFossilPieces.addPieces(generationContext.structureTemplateManager(), p_228581_, worldgenrandom, blockpos);
            }));
        }
    }

    @NotNull
    public StructureType<?> type() {
        return ExtraStructures.SCULK_FOSSIL.get();
    }
}