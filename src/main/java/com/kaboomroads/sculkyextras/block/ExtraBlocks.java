package com.kaboomroads.sculkyextras.block;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.custom.*;
import com.kaboomroads.sculkyextras.item.ExtraItems;
import com.kaboomroads.sculkyextras.world.feature.tree.AbyssalTreeGrower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ExtraBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SculkyExtras.MOD_ID);

    public static final RegistryObject<Block> OTHERSTONE = registerBlockAndItem("otherstone", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3f, 6f).sound(SoundType.DEEPSLATE_TILES)));
    public static final RegistryObject<Block> SCULK_LUSTRE = registerBlockAndItem("sculk_lustre", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3f, 3f).lightLevel(blockState -> 15).sound(SoundType.SCULK_SENSOR)));
    public static final RegistryObject<Block> SCULK_TENDRIL = registerBlockAndItem("sculk_tendril", () ->
            new SculkTendrilBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.ROOTS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(blockState -> 1)));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_LOG = registerBlockAndItem("stripped_abyssal_log", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).color(MaterialColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, null));
    public static final RegistryObject<Block> ABYSSAL_LOG = registerBlockAndItem("abyssal_log", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).color(MaterialColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, STRIPPED_ABYSSAL_LOG.get()));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_WOOD = registerBlockAndItem("stripped_abyssal_wood", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).color(MaterialColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, null));
    public static final RegistryObject<Block> ABYSSAL_WOOD = registerBlockAndItem("abyssal_wood", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).color(MaterialColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, STRIPPED_ABYSSAL_WOOD.get()));
    public static final RegistryObject<Block> ABYSSAL_SAPLING = registerBlockAndItem("abyssal_sapling", () ->
            new AbyssalSaplingBlock(new AbyssalTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).color(MaterialColor.COLOR_CYAN)));

    public static final RegistryObject<Block> ABYSSAL_PLANKS = registerBlockAndItem("abyssal_planks", () ->
            new ModWoodBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).color(MaterialColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F), true, 5, 20));
    public static final RegistryObject<Block> ABYSSAL_STAIRS = registerBlockAndItem("abyssal_stairs", () ->
            new StairBlock(() -> ABYSSAL_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.NETHER_WOOD).color(MaterialColor.COLOR_CYAN).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_SLAB = registerBlockAndItem("abyssal_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).color(MaterialColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_FENCE = registerBlockAndItem("abyssal_fence", () ->
            new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).color(MaterialColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_FENCE_GATE = registerBlockAndItem("abyssal_fence_gate", () ->
            new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).color(MaterialColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F), SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
    public static final RegistryObject<Block> ABYSSAL_DOOR = registerBlockAndItem("abyssal_door", () ->
            new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, ABYSSAL_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.NETHER_WOOD).noOcclusion(), SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN));
    public static final RegistryObject<Block> ABYSSAL_TRAPDOOR = registerBlockAndItem("abyssal_trapdoor", () ->
            new TrapDoorBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, ABYSSAL_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.NETHER_WOOD).noOcclusion().isValidSpawn(Blocks::never), SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN));
    public static final RegistryObject<Block> ABYSSAL_BUTTON = registerBlockAndItem("abyssal_button", () ->
            new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.NETHER_WOOD), 30, true, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));
    public static final RegistryObject<Block> ABYSSAL_PRESSURE_PLATE = registerBlockAndItem("abyssal_pressure_plate", () ->
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.NETHER_WOOD, ABYSSAL_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.NETHER_WOOD), SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON));
    public static final RegistryObject<Block> ABYSSAL_SIGN = registerBlock("abyssal_sign", () ->
            new ModStandingSignBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, ABYSSAL_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.NETHER_WOOD), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_WALL_SIGN = registerBlock("abyssal_wall_sign", () ->
            new ModWallSignBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, ABYSSAL_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.NETHER_WOOD).lootFrom(ABYSSAL_SIGN), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_HANGING_SIGN = registerBlock("abyssal_hanging_sign", () ->
            new ModCeilingHangingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_CYAN).noCollission().strength(1.0F).sound(SoundType.NETHER_WOOD_HANGING_SIGN), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_WALL_HANGING_SIGN = registerBlock("abyssal_wall_hanging_sign", () ->
            new ModWallHangingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_CYAN).noCollission().strength(1.0F).sound(SoundType.NETHER_WOOD_HANGING_SIGN).lootFrom(ABYSSAL_HANGING_SIGN), ExtraWoodTypes.ABYSSAL));

    public static final RegistryObject<Block> ABYSSAL_LEAVES = registerBlockAndItem("abyssal_leaves", () ->
            new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).color(MaterialColor.COLOR_CYAN), true, 30, 60));

    public static final RegistryObject<Block> SCULK_SOIL = registerBlockAndItem("sculk_soil", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.SCULK)));

    public static final RegistryObject<Block> OTHERSIDE_PORTAL = registerBlock("otherside_portal", OthersidePortalBlock::new);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ExtraItems.registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, BlockItem item) {
        return ExtraItems.registerItem(name, () -> item);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
