package com.kaboomroads.sculkyextras.world.feature.tree;

import com.kaboomroads.sculkyextras.world.feature.ExtraConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbyssalTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean largeHive) {
        return ExtraConfiguredFeatures.ABYSSAL_TREE;
    }
}
