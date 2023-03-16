package com.kaboomroads.sculkyextras.block;

import net.minecraft.world.level.block.state.properties.WoodType;

public class ExtraWoodTypes {
    public static final WoodType ABYSSAL = WoodType.create("abyssal");

    public static void register() {
        WoodType.register(ABYSSAL);
    }
}
