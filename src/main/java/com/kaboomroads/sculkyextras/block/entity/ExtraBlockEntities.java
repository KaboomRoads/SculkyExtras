package com.kaboomroads.sculkyextras.block.entity;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.block.entity.custom.ModHangingSignBlockEntity;
import com.kaboomroads.sculkyextras.block.entity.custom.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtraBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SculkyExtras.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ExtraBlocks.ABYSSAL_WALL_SIGN.get(),
                            ExtraBlocks.ABYSSAL_SIGN.get()
                    ).build(null));
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("hanging_sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ExtraBlocks.ABYSSAL_WALL_HANGING_SIGN.get(),
                            ExtraBlocks.ABYSSAL_HANGING_SIGN.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
