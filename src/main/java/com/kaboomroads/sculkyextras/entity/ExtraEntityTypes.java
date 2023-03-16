package com.kaboomroads.sculkyextras.entity;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.entity.custom.ModBoat;
import com.kaboomroads.sculkyextras.entity.custom.ModChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtraEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SculkyExtras.MOD_ID);

    public static final RegistryObject<EntityType<ModBoat>> BOAT =
            ENTITY_TYPES.register("boat", () ->
                    EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC)
                            .sized(EntityType.BOAT.getWidth(), EntityType.BOAT.getHeight())
                            .build("boat"));
    public static final RegistryObject<EntityType<ModChestBoat>> CHEST_BOAT =
            ENTITY_TYPES.register("chest_boat", () ->
                    EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC)
                            .sized(EntityType.CHEST_BOAT.getWidth(), EntityType.CHEST_BOAT.getHeight())
                            .build("chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
