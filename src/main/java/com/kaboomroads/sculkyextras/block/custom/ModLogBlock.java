package com.kaboomroads.sculkyextras.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModLogBlock extends RotatedPillarBlock {
    public final Block stripped;
    public final boolean flammable;
    public final int flammability;
    public final int fireSpreadSpeed;

    public ModLogBlock(Properties properties, boolean flammable, int flammability, int fireSpreadSpeed, @Nullable Block stripped) {
        super(properties);
        this.stripped = stripped;
        this.flammable = flammable;
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammable;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return fireSpreadSpeed;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem && stripped != null) {
            BlockState blockState = stripped.defaultBlockState();
            return blockState.hasProperty(AXIS) ? blockState.setValue(AXIS, state.getValue(AXIS)) : blockState;
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
