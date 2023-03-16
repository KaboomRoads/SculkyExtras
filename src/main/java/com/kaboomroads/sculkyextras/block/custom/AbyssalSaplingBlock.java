package com.kaboomroads.sculkyextras.block.custom;

import com.kaboomroads.sculkybits.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AbyssalSaplingBlock extends SaplingBlock {
    public AbyssalSaplingBlock(AbstractTreeGrower treeGrower, BlockBehaviour.Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return Block.isFaceFull(blockState.getCollisionShape(blockGetter, blockPos.below()), Direction.UP) && (blockState.is(BlockTags.SCULK_REPLACEABLE_WORLD_GEN) || blockState.is(Blocks.SCULK) || blockState.is(ModBlocks.SCULK_FLESH.get()) || blockState.is(ModBlocks.SCULK_GROWTH.get()));
    }
}