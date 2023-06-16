package com.kaboomroads.sculkyextras.block;

import com.kaboomroads.sculkyextras.mixin.BlockSetTypeInvoker;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ExtraBlockSetTypes {
    public static final BlockSetType ABYSSAL = BlockSetTypeInvoker.register(new BlockSetType("abyssal"));
}
