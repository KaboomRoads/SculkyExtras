package com.kaboomroads.sculkyextras.block.custom;

import com.kaboomroads.sculkyextras.block.entity.custom.ModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModCeilingHangingSignBlock extends CeilingHangingSignBlock {
    public ModCeilingHangingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ModHangingSignBlockEntity(blockPos, blockState);
    }
}
