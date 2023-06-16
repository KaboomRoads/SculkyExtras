package com.kaboomroads.sculkyextras.world.dimension.portal;

import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.block.custom.OthersidePortalBlock;
import com.kaboomroads.sculkyextras.poi.ExtraPOIs;
import com.kaboomroads.sculkyextras.world.dimension.ExtraDimensions;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public record OthersideTeleporter(ServerLevel level) implements ITeleporter {
    public Optional<BlockUtil.FoundRectangle> getExistingPortal(BlockPos pos) {
        PoiManager poiManager = this.level.getPoiManager();
        poiManager.ensureLoadedAndValid(this.level, pos, 64);
        Optional<PoiRecord> optional = poiManager.getInSquare(poiType ->
                poiType.get() == ExtraPOIs.OTHERSIDE_PORTAL.get(), pos, 64, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble(poi ->
                poi.getPos().distSqr(pos)).thenComparingInt(poi ->
                poi.getPos().getY())).filter(poi ->
                this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)).findFirst();
        return optional.map(poi -> {
            BlockPos blockpos = poi.getPos();
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.level.getBlockState(blockpos);
            return BlockUtil.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21,
                    posIn -> this.level.getBlockState(posIn) == blockstate);
        });
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockPos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int dimensionLogicalHeight = this.level.getHeight() - 1;
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        for (BlockPos.MutableBlockPos blockpos$mutable1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(dimensionLogicalHeight, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, blockpos$mutable1.getX(), blockpos$mutable1.getZ()));
            if (worldborder.isWithinBounds(blockpos$mutable1) && worldborder.isWithinBounds(blockpos$mutable1.move(direction, 1))) {
                blockpos$mutable1.move(direction.getOpposite(), 1);
                for (int l = j; l >= this.level.getMinBuildHeight(); --l) {
                    blockpos$mutable1.setY(l);
                    if (this.level.isEmptyBlock(blockpos$mutable1)) {
                        int i1;
                        i1 = l;
                        while (l > this.level.getMinBuildHeight() && this.level.isEmptyBlock(blockpos$mutable1.move(Direction.DOWN)))
                            --l;
                        if (l + 4 <= dimensionLogicalHeight) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                blockpos$mutable1.setY(l);
                                if (this.checkRegionForPlacement(blockpos$mutable1, mutablePos, direction, 0)) {
                                    double d2 = pos.distSqr(blockpos$mutable1);
                                    if (this.checkRegionForPlacement(blockpos$mutable1, mutablePos, direction, -1) && this.checkRegionForPlacement(blockpos$mutable1, mutablePos, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockPos = blockpos$mutable1.immutable();
                                    }
                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = blockpos$mutable1.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (d0 == -1.0D && d1 != -1.0D) {
            blockPos = blockpos1;
            d0 = d1;
        }
        if (d0 == -1.0D) {
            blockPos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), 70, this.level.getHeight() - 10), pos.getZ())).immutable();
            Direction direction1 = direction.getClockWise();
            if (!worldborder.isWithinBounds(blockPos)) return Optional.empty();
            for (int i = -2; i < 3; i++)
                for (int j = 0; j < 6; j++)
                    for (int k = -1; k < 3; k++) {
                        BlockState blockstate1 = k < 0 ? Blocks.REINFORCED_DEEPSLATE.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        mutablePos.setWithOffset(blockPos, j * direction.getStepX() + i * direction1.getStepX(), k, j * direction.getStepZ() + i * direction1.getStepZ());
                        this.level.setBlockAndUpdate(mutablePos, blockstate1);
                    }
        }
        for (int i = -1; i < 7; i++)
            for (int j = -1; j < 4; j++)
                if (i == -1 || i == 6 || j == -1 || j == 3) {
                    mutablePos.setWithOffset(blockPos, i * direction.getStepX(), j, i * direction.getStepZ());
                    this.level.setBlock(mutablePos, Blocks.REINFORCED_DEEPSLATE.defaultBlockState(), 3);
                }
        BlockState othersidePortal = ExtraBlocks.OTHERSIDE_PORTAL.get().defaultBlockState().setValue(OthersidePortalBlock.AXIS, axis);
        for (int j2 = 0; j2 < 6; ++j2)
            for (int l2 = 0; l2 < 3; ++l2) {
                mutablePos.setWithOffset(blockPos, j2 * direction.getStepX(), l2, j2 * direction.getStepZ());
                this.level.setBlock(mutablePos, othersidePortal, 18);
            }
        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 2, 3));
    }

    private boolean checkRegionForPlacement(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction directionIn, int offsetScale) {
        Direction direction = directionIn.getClockWise();
        for (int i = -1; i < 3; ++i)
            for (int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, directionIn.getStepX() * i + direction.getStepX() * offsetScale, j, directionIn.getStepZ() * i + direction.getStepZ() * offsetScale);
                if (j < 0 && !this.level.getBlockState(offsetPos).canOcclude()) return false;
                if (j >= 0 && !this.level.isEmptyBlock(offsetPos)) return false;
            }
        return true;
    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel level, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        boolean destinationIsUG = level.dimension() == ExtraDimensions.OTHERSIDE;
        if (entity.level().dimension() != ExtraDimensions.OTHERSIDE && !destinationIsUG) return null;
        else {
            WorldBorder border = level.getWorldBorder();
            double minX = Math.max(-2.9999872E7D, border.getMinX() + 16.0D);
            double minZ = Math.max(-2.9999872E7D, border.getMinZ() + 16.0D);
            double maxX = Math.min(2.9999872E7D, border.getMaxX() - 16.0D);
            double maxZ = Math.min(2.9999872E7D, border.getMaxZ() - 16.0D);
            double coordinateDifference = DimensionType.getTeleportationScale(entity.level().dimensionType(), level.dimensionType());
            BlockPos blockpos = new BlockPos((int) Mth.clamp(entity.getX() * coordinateDifference, minX, maxX), (int) entity.getY(), (int) Mth.clamp(entity.getZ() * coordinateDifference, minZ, maxZ));
            return this.getOrMakePortal(entity, blockpos).map(result -> {
                BlockState blockstate = entity.level().getBlockState(entity.portalEntrancePos);
                Direction.Axis axis;
                Vec3 vector3d;
                if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                    axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                    BlockUtil.FoundRectangle rectangle = BlockUtil.getLargestRectangleAround(entity.portalEntrancePos, axis, 21, Direction.Axis.Y, 21, pos -> entity.level().getBlockState(pos) == blockstate);
                    vector3d = PortalShape.getRelativePosition(rectangle, axis, entity.position(), entity.getDimensions(entity.getPose()));
                } else {
                    axis = Direction.Axis.X;
                    vector3d = new Vec3(0.5D, 0.0D, 0.0D);
                }
                return PortalShape.createPortalInfo(level, result, axis, vector3d, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
            }).orElse(null);
        }
    }

    private Optional<BlockUtil.FoundRectangle> getOrMakePortal(Entity entity, BlockPos pos) {
        Optional<BlockUtil.FoundRectangle> existingPortal = this.getExistingPortal(pos);
        if (existingPortal.isPresent()) return existingPortal;
        else {
            Direction.Axis portalAxis = this.level.getBlockState(entity.portalEntrancePos).getOptionalValue(OthersidePortalBlock.AXIS).orElse(Direction.Axis.X);
            return this.createPortal(pos, portalAxis);
        }
    }

    @Override
    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
        return false;
    }
}