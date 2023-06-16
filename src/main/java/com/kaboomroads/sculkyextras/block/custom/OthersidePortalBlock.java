package com.kaboomroads.sculkyextras.block.custom;

import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.world.dimension.ExtraDimensions;
import com.kaboomroads.sculkyextras.world.dimension.portal.OthersideTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OthersidePortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public OthersidePortalBlock() {
        super(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((p_50872_) -> 11).pushReaction(PushReaction.BLOCK));
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @NotNull
    @Override
    public VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return blockState.getValue(AXIS) == Direction.Axis.Z ? Z_AABB : X_AABB;
    }

    public boolean trySpawnPortal(LevelAccessor levelAccessor, BlockPos blockPos) {
        OthersidePortalBlock.Size othersidePortalBlock$size = this.isPortal(levelAccessor, blockPos);
        if (othersidePortalBlock$size != null && !onTrySpawnPortal(levelAccessor, blockPos, othersidePortalBlock$size)) {
            othersidePortalBlock$size.placePortalBlocks();
            return true;
        } else return false;
    }

    public static boolean onTrySpawnPortal(LevelAccessor levelAccessor, BlockPos blockPos, OthersidePortalBlock.Size size) {
        return MinecraftForge.EVENT_BUS.post(new PortalSpawnEvent(levelAccessor, blockPos, levelAccessor.getBlockState(blockPos), size));
    }

    @Cancelable
    public static class PortalSpawnEvent extends BlockEvent {
        private final OthersidePortalBlock.Size size;

        public PortalSpawnEvent(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, OthersidePortalBlock.Size size) {
            super(levelAccessor, blockPos, blockState);
            this.size = size;
        }

        public OthersidePortalBlock.Size getPortalSize() {
            return size;
        }
    }

    @Nullable
    public OthersidePortalBlock.Size isPortal(LevelAccessor levelAccessor, BlockPos blockPos) {
        OthersidePortalBlock.Size othersidePortalBlock$size = new Size(levelAccessor, blockPos, Direction.Axis.X);
        if (othersidePortalBlock$size.isValid() && othersidePortalBlock$size.portalBlockCount == 0)
            return othersidePortalBlock$size;
        else {
            OthersidePortalBlock.Size othersidePortalBlock$size1 = new Size(levelAccessor, blockPos, Direction.Axis.Z);
            return othersidePortalBlock$size1.isValid() && othersidePortalBlock$size1.portalBlockCount == 0 ? othersidePortalBlock$size1 : null;
        }
    }

    @NotNull
    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, @NotNull BlockState blockState1, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        Direction.Axis direction$axis = direction.getAxis();
        Direction.Axis direction$axis1 = blockState.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && blockState1.getBlock() != this && !(new Size(levelAccessor, currentPos, direction$axis1)).validatePortal() ?
                Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState1, levelAccessor, currentPos, facingPos);
    }

    @Override
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                Level entityLevel = entity.level();
                if (!entityLevel.isClientSide && !blockPos.equals(entity.portalEntrancePos))
                    entity.portalEntrancePos = blockPos.immutable();
                MinecraftServer minecraftserver = entityLevel.getServer();
                ResourceKey<Level> destination = entityLevel.dimension() == ExtraDimensions.OTHERSIDE
                        ? Level.OVERWORLD : ExtraDimensions.OTHERSIDE;
                entity.handleInsidePortal(blockPos);
                if (minecraftserver != null) {
                    int i = entity.getPortalWaitTime();
                    ServerLevel destinationWorld = minecraftserver.getLevel(destination);
                    if (destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger() && entity.portalTime >= i) {
                        entityLevel.getProfiler().push("otherside_portal");
                        entity.portalTime = i;
                        entity.setPortalCooldown();
                        entity.changeDimension(destinationWorld, new OthersideTeleporter(destinationWorld));
                        entity.isInsidePortal = false;
                        entityLevel.getProfiler().pop();
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(100) == 0)
            level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D,
                    (double) blockPos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT,
                    SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
        for (int i = 0; i < 4; ++i) {
            double x = (double) blockPos.getX() + randomSource.nextDouble();
            double y = (double) blockPos.getY() + randomSource.nextDouble();
            double z = (double) blockPos.getZ() + randomSource.nextDouble();
            double xSpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            double zSpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.5D;
            int j = randomSource.nextInt(2) * 2 - 1;
            if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                x = (double) blockPos.getX() + 0.5D + 0.25D * (double) j;
                xSpeed = randomSource.nextFloat() * 2.0F * (float) j;
            } else {
                z = (double) blockPos.getZ() + 0.5D + 0.25D * (double) j;
                zSpeed = randomSource.nextFloat() * 2.0F * (float) j;
            }
            level.addParticle(ParticleTypes.PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    @NotNull
    @Override
    public ItemStack getCloneItemStack(@NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return ItemStack.EMPTY;
    }

    @NotNull
    @Override
    public BlockState rotate(@NotNull BlockState blockState, @NotNull Rotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (blockState.getValue(AXIS)) {
                case Z -> blockState.setValue(AXIS, Direction.Axis.X);
                case X -> blockState.setValue(AXIS, Direction.Axis.Z);
                default -> blockState;
            };
            default -> blockState;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static class Size {
        private final LevelAccessor level;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(LevelAccessor level, BlockPos pos, Direction.Axis axis) {
            this.level = level;
            this.axis = axis;
            if (axis == Direction.Axis.X) {
                this.leftDir = Direction.EAST;
                this.rightDir = Direction.WEST;
            } else {
                this.leftDir = Direction.NORTH;
                this.rightDir = Direction.SOUTH;
            }
            BlockPos blockpos = pos;
            while (pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.canConnect(level.getBlockState(pos.below())))
                pos = pos.below();
            int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
            if (i >= 0) {
                this.bottomLeft = pos.relative(this.leftDir, i);
                this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
                if (this.width < 2 || this.width > 21) {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }
            if (this.bottomLeft != null) this.height = this.calculatePortalHeight();
        }

        protected int getDistanceUntilEdge(BlockPos blockPos, Direction direction) {
            int i;
            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = blockPos.relative(direction, i);
                if (!this.canConnect(this.level.getBlockState(blockpos)) || !(this.level.getBlockState(blockpos.below()).is(Blocks.REINFORCED_DEEPSLATE)))
                    break;
            }
            BlockPos framePos = blockPos.relative(direction, i);
            return this.level.getBlockState(framePos).is(Blocks.REINFORCED_DEEPSLATE) ? i : 0;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        protected int calculatePortalHeight() {
            label56:
            for (this.height = 0; this.height < 21; ++this.height) {
                for (int i = 0; i < this.width; ++i) {
                    BlockPos blockpos = this.bottomLeft.relative(this.rightDir, i).above(this.height);
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    if (!this.canConnect(blockstate)) break label56;
                    Block block = blockstate.getBlock();
                    if (block == ExtraBlocks.OTHERSIDE_PORTAL.get()) ++this.portalBlockCount;
                    if (i == 0) {
                        BlockPos framePos = blockpos.relative(this.leftDir);
                        if (!(this.level.getBlockState(framePos).is(Blocks.REINFORCED_DEEPSLATE))) break label56;
                    } else if (i == this.width - 1) {
                        BlockPos framePos = blockpos.relative(this.rightDir);
                        if (!(this.level.getBlockState(framePos).is(Blocks.REINFORCED_DEEPSLATE))) {
                            break label56;
                        }
                    }
                }
            }
            for (int j = 0; j < this.width; ++j) {
                BlockPos framePos = this.bottomLeft.relative(this.rightDir, j).above(this.height);
                if (!(this.level.getBlockState(framePos).is(Blocks.REINFORCED_DEEPSLATE))) {
                    this.height = 0;
                    break;
                }
            }
            if (this.height <= 21 && this.height >= 2)
                return this.height;
            else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        protected boolean canConnect(BlockState blockState) {
            Block block = blockState.getBlock();
            return blockState.isAir() || blockState.is(Blocks.SCULK_VEIN) || block == ExtraBlocks.OTHERSIDE_PORTAL.get();
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 2 && this.height <= 21;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = this.bottomLeft.relative(this.rightDir, i);
                for (int j = 0; j < this.height; ++j)
                    this.level.setBlock(blockpos.above(j), ExtraBlocks.OTHERSIDE_PORTAL.get().defaultBlockState().setValue(OthersidePortalBlock.AXIS, this.axis), 18);
            }
        }

        private boolean isPortalCountValidForSize() {
            return this.portalBlockCount >= this.width * this.height;
        }

        public boolean validatePortal() {
            return this.isValid() && this.isPortalCountValidForSize();
        }
    }
}
