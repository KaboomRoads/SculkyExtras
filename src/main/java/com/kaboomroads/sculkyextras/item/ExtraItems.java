package com.kaboomroads.sculkyextras.item;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.entity.custom.ModBoat;
import com.kaboomroads.sculkyextras.item.custom.ModBoatItem;
import com.kaboomroads.sculkyextras.item.custom.WardenHeartItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ExtraItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SculkyExtras.MOD_ID);

    public static final RegistryObject<Item> WARDEN_HEART = registerItem("warden_heart", WardenHeartItem::new);

    public static final RegistryObject<Item> ABYSSAL_SIGN = registerItem("abyssal_sign", () ->
            new SignItem(new Item.Properties().stacksTo(16), ExtraBlocks.ABYSSAL_SIGN.get(), ExtraBlocks.ABYSSAL_WALL_SIGN.get()));
    public static final RegistryObject<Item> ABYSSAL_HANGING_SIGN = registerItem("abyssal_hanging_sign", () ->
            new HangingSignItem(ExtraBlocks.ABYSSAL_HANGING_SIGN.get(), ExtraBlocks.ABYSSAL_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> ABYSSAL_BOAT = registerItem("abyssal_boat", () ->
            new ModBoatItem(false, ModBoat.Type.ABYSSAL, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ABYSSAL_CHEST_BOAT = registerItem("abyssal_chest_boat", () ->
            new ModBoatItem(true, ModBoat.Type.ABYSSAL, new Item.Properties().stacksTo(1)));

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
