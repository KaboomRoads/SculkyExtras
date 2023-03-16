package com.kaboomroads.sculkyextras.block.entity.custom;

import com.kaboomroads.sculkyextras.block.entity.ExtraBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {
    public ModSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @NotNull
    @Override
    public BlockEntityType<?> getType() {
        return ExtraBlockEntities.SIGN_BLOCK_ENTITIES.get();
    }
}
