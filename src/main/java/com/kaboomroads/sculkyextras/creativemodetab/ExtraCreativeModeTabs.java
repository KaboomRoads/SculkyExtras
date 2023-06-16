package com.kaboomroads.sculkyextras.creativemodetab;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.item.ExtraItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtraCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SculkyExtras.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SCULKY_EXTRAS = CREATIVE_MODE_TABS.register("sculky_extras", CreativeModeTab.builder()
            .icon(() -> new ItemStack(ExtraItems.WARDEN_HEART.get()))
            .title(Component.translatable("itemGroup.sculkyextras"))
            .displayItems((parameters, output) -> {
                output.accept(ExtraItems.WARDEN_HEART.get());
                output.accept(ExtraBlocks.OTHERSTONE.get());
                output.accept(ExtraBlocks.SCULK_LUSTRE.get());
                output.accept(ExtraBlocks.SCULK_SOIL.get());
                output.accept(ExtraBlocks.SCULK_TENDRIL.get());
                output.accept(ExtraBlocks.ABYSSAL_LOG.get());
                output.accept(ExtraBlocks.STRIPPED_ABYSSAL_LOG.get());
                output.accept(ExtraBlocks.ABYSSAL_WOOD.get());
                output.accept(ExtraBlocks.STRIPPED_ABYSSAL_WOOD.get());
                output.accept(ExtraBlocks.ABYSSAL_PLANKS.get());
                output.accept(ExtraBlocks.ABYSSAL_LEAVES.get());
                output.accept(ExtraBlocks.ABYSSAL_SAPLING.get());
                output.accept(ExtraBlocks.ABYSSAL_STAIRS.get());
                output.accept(ExtraBlocks.ABYSSAL_SLAB.get());
                output.accept(ExtraBlocks.ABYSSAL_FENCE.get());
                output.accept(ExtraBlocks.ABYSSAL_FENCE_GATE.get());
                output.accept(ExtraBlocks.ABYSSAL_DOOR.get());
                output.accept(ExtraBlocks.ABYSSAL_TRAPDOOR.get());
                output.accept(ExtraBlocks.ABYSSAL_BUTTON.get());
                output.accept(ExtraBlocks.ABYSSAL_PRESSURE_PLATE.get());
                output.accept(ExtraItems.ABYSSAL_SIGN.get());
                output.accept(ExtraItems.ABYSSAL_HANGING_SIGN.get());
                output.accept(ExtraItems.ABYSSAL_BOAT.get());
                output.accept(ExtraItems.ABYSSAL_CHEST_BOAT.get());
            })::build);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
