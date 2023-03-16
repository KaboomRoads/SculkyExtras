package com.kaboomroads.sculkyextras.world.feature.custom;

import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SculkLustreFeature extends Feature<NoneFeatureConfiguration> {
    public SculkLustreFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
        WorldGenLevel worldgenlevel = placeContext.level();
        BlockPos blockpos = placeContext.origin();
        RandomSource randomsource = placeContext.random();
        if (!worldgenlevel.isEmptyBlock(blockpos)) return false;
        else {
            BlockState blockstate = worldgenlevel.getBlockState(blockpos.above());
            if (!blockstate.is(Blocks.SCULK) && !blockstate.is(ExtraBlocks.OTHERSTONE.get())) return false;
            else {
                worldgenlevel.setBlock(blockpos, ExtraBlocks.SCULK_LUSTRE.get().defaultBlockState(), 2);
                for (int i = 0; i < 1500; ++i) {
                    BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(8) - randomsource.nextInt(8), -randomsource.nextInt(12), randomsource.nextInt(8) - randomsource.nextInt(8));
                    if (worldgenlevel.getBlockState(blockpos1).isAir()) {
                        int j = 0;
                        for (Direction direction : Direction.values()) {
                            if (worldgenlevel.getBlockState(blockpos1.relative(direction)).is(ExtraBlocks.SCULK_LUSTRE.get()))
                                ++j;
                            if (j > 1) break;
                        }
                        if (j == 1)
                            worldgenlevel.setBlock(blockpos1, ExtraBlocks.SCULK_LUSTRE.get().defaultBlockState(), 2);
                    }
                }
                return true;
            }
        }
    }
}