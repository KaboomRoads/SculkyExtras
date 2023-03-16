package com.kaboomroads.sculkyextras.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ModWoodBlock extends Block {
    public final boolean flammable;
    public final int flammability;
    public final int fireSpreadSpeed;

    public ModWoodBlock(Properties properties, boolean flammable, int flammability, int fireSpreadSpeed) {
        super(properties);
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
}
