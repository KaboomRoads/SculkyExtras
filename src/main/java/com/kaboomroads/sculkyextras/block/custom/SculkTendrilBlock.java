package com.kaboomroads.sculkyextras.block.custom;

import com.kaboomroads.sculkybits.block.entity.custom.SculkAttacker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SculkTendrilBlock extends BushBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<SculkSensorPhase> PHASE = BlockStateProperties.SCULK_SENSOR_PHASE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public SculkTendrilBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PHASE, SculkSensorPhase.INACTIVE).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PHASE, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @NotNull
    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @NotNull
    @Override
    public BlockState updateShape(BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState1, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos1) {
        if (blockState.getValue(WATERLOGGED))
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    public static void activate(@Nullable Entity entity, Level level, BlockPos blockPos, BlockState blockState) {
        level.setBlock(blockPos, blockState.setValue(PHASE, SculkSensorPhase.ACTIVE), 3);
        level.scheduleTick(blockPos, blockState.getBlock(), 40);
        updateNeighbours(level, blockPos);
        level.gameEvent(entity, GameEvent.SCULK_SENSOR_TENDRILS_CLICKING, blockPos);
        if (!blockState.getValue(WATERLOGGED))
            level.playSound(null, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, SoundEvents.SCULK_CLICKING, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.2F + 0.8F);
    }

    public static void deactivate(Level level, BlockPos blockPos, BlockState blockState) {
        level.setBlock(blockPos, blockState.setValue(PHASE, SculkSensorPhase.COOLDOWN), 3);
        level.scheduleTick(blockPos, blockState.getBlock(), 1);
        updateNeighbours(level, blockPos);
        if (!blockState.getValue(WATERLOGGED))
            level.playSound(null, blockPos, SoundEvents.SCULK_CLICKING_STOP, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.2F + 0.8F);
    }

    private static void updateNeighbours(Level level, BlockPos blockPos) {
        level.updateNeighborsAt(blockPos, Blocks.SCULK_SENSOR);
        level.updateNeighborsAt(blockPos.relative(Direction.UP.getOpposite()), Blocks.SCULK_SENSOR);
    }

    @Override
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        if (SculkAttacker.testAttackable(entity) && SculkSensorBlock.canActivate(blockState))
            activate(entity, level, blockPos, blockState);
    }

    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        if (SculkSensorBlock.getPhase(blockState) != SculkSensorPhase.ACTIVE) {
            if (SculkSensorBlock.getPhase(blockState) == SculkSensorPhase.COOLDOWN)
                level.setBlock(blockPos, blockState.setValue(PHASE, SculkSensorPhase.INACTIVE), 3);
        } else deactivate(level, blockPos, blockState);
    }

    @NotNull
    @Override
    public VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return Block.isFaceFull(blockState.getCollisionShape(blockGetter, blockPos.below()), Direction.UP) && (blockState.is(BlockTags.SCULK_REPLACEABLE_WORLD_GEN) || blockState.getMaterial() == Material.SCULK);
    }
}