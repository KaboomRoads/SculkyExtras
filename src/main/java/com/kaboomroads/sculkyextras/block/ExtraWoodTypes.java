package com.kaboomroads.sculkyextras.block;

import com.kaboomroads.sculkyextras.mixin.WoodTypeInvoker;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ExtraWoodTypes {
    public static final WoodType ABYSSAL = WoodTypeInvoker.invokeRegister(WoodTypeInvoker.invokeInit("abyssal", ExtraBlockSetTypes.ABYSSAL));

    public static void register() {
        WoodType.register(ABYSSAL);
    }
}
