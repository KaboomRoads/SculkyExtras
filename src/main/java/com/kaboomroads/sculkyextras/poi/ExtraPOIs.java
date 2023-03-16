package com.kaboomroads.sculkyextras.poi;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtraPOIs {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, SculkyExtras.MOD_ID);

    public static final RegistryObject<PoiType> OTHERSIDE_PORTAL =
            POI_TYPES.register("otherside_portal", () ->
                    new PoiType(PoiTypes.getBlockStates(ExtraBlocks.OTHERSIDE_PORTAL.get()), 0, 1));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}
