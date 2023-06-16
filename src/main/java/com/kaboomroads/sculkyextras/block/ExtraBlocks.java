package com.kaboomroads.sculkyextras.block;

import com.kaboomroads.sculkyextras.SculkyExtras;
import com.kaboomroads.sculkyextras.block.custom.*;
import com.kaboomroads.sculkyextras.item.ExtraItems;
import com.kaboomroads.sculkyextras.world.feature.tree.AbyssalTreeGrower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ExtraBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SculkyExtras.MOD_ID);

    public static final RegistryObject<Block> OTHERSTONE = registerBlockAndItem("otherstone", () ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3f, 6f).sound(SoundType.DEEPSLATE_TILES)));
    public static final RegistryObject<Block> SCULK_LUSTRE = registerBlockAndItem("sculk_lustre", () ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3f, 3f).lightLevel(blockState -> 15).sound(SoundType.SCULK_SENSOR)));
    public static final RegistryObject<Block> SCULK_TENDRIL = registerBlockAndItem("sculk_tendril", () ->
            new SculkTendrilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).replaceable().noCollission().instabreak().sound(SoundType.ROOTS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(blockState -> 1)));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_LOG = registerBlockAndItem("stripped_abyssal_log", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, null));
    public static final RegistryObject<Block> ABYSSAL_LOG = registerBlockAndItem("abyssal_log", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, STRIPPED_ABYSSAL_LOG.get()));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_WOOD = registerBlockAndItem("stripped_abyssal_wood", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, null));
    public static final RegistryObject<Block> ABYSSAL_WOOD = registerBlockAndItem("abyssal_wood", () ->
            new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_CYAN).strength(3.0F).sound(SoundType.STEM), true, 5, 5, STRIPPED_ABYSSAL_WOOD.get()));
    public static final RegistryObject<Block> ABYSSAL_SAPLING = registerBlockAndItem("abyssal_sapling", () ->
            new AbyssalSaplingBlock(new AbyssalTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> ABYSSAL_PLANKS = registerBlockAndItem("abyssal_planks", () ->
            new ModWoodBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F), true, 5, 20));
    public static final RegistryObject<Block> ABYSSAL_STAIRS = registerBlockAndItem("abyssal_stairs", () ->
            new StairBlock(() -> ABYSSAL_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.NETHER_WOOD).mapColor(MapColor.COLOR_CYAN).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_SLAB = registerBlockAndItem("abyssal_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_FENCE = registerBlockAndItem("abyssal_fence", () ->
            new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ABYSSAL_FENCE_GATE = registerBlockAndItem("abyssal_fence_gate", () ->
            new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_CYAN).sound(SoundType.NETHER_WOOD).strength(3.0F, 3.0F), SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
    public static final RegistryObject<Block> ABYSSAL_DOOR = registerBlockAndItem("abyssal_door", () ->
            new DoorBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY).sound(SoundType.NETHER_WOOD), ExtraBlockSetTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_TRAPDOOR = registerBlockAndItem("abyssal_trapdoor", () ->
            new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(Blocks::never).ignitedByLava().sound(SoundType.NETHER_WOOD), ExtraBlockSetTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_BUTTON = registerBlockAndItem("abyssal_button", () ->
            woodenButton(ExtraBlockSetTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_PRESSURE_PLATE = registerBlockAndItem("abyssal_pressure_plate", () ->
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY).sound(SoundType.NETHER_WOOD), ExtraBlockSetTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_SIGN = registerBlock("abyssal_sign", () ->
            new ModStandingSignBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().sound(SoundType.NETHER_WOOD), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_WALL_SIGN = registerBlock("abyssal_wall_sign", () ->
            new ModWallSignBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).lootFrom(ABYSSAL_SIGN).ignitedByLava().sound(SoundType.NETHER_WOOD), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_HANGING_SIGN = registerBlock("abyssal_hanging_sign", () ->
            new ModCeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().sound(SoundType.NETHER_WOOD), ExtraWoodTypes.ABYSSAL));
    public static final RegistryObject<Block> ABYSSAL_WALL_HANGING_SIGN = registerBlock("abyssal_wall_hanging_sign", () ->
            new ModWallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(ABYSSAL_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().lootFrom(ABYSSAL_HANGING_SIGN).sound(SoundType.NETHER_WOOD), ExtraWoodTypes.ABYSSAL));

    public static final RegistryObject<Block> ABYSSAL_LEAVES = registerBlockAndItem("abyssal_leaves", () ->
            new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_CYAN), true, 30, 60));

    public static final RegistryObject<Block> SCULK_SOIL = registerBlockAndItem("sculk_soil", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.SCULK)));

    public static final RegistryObject<Block> OTHERSIDE_PORTAL = registerBlock("otherside_portal", OthersidePortalBlock::new);

    private static ButtonBlock woodenButton(BlockSetType blockSetType, FeatureFlag... featureFlags) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (featureFlags.length > 0) blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(featureFlags);
        return new ButtonBlock(blockbehaviour$properties, blockSetType, 30, true);
    }

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
