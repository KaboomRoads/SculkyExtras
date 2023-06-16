package com.kaboomroads.sculkyextras.tag;

import com.kaboomroads.sculkyextras.SculkyExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static void init() {
        Blocks.init();
    }

    public static class Blocks {
        public static final TagKey<Block> SCULK_TENDRIL_GROWABLE = tag("sculk_tendril_growable");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(SculkyExtras.MOD_ID, name));
        }

        public static void init() {
        }
    }
}
