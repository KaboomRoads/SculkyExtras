package com.kaboomroads.sculkyextras.item.custom;

import com.kaboomroads.sculkyextras.block.ExtraBlocks;
import com.kaboomroads.sculkyextras.block.custom.OthersidePortalBlock;
import com.kaboomroads.sculkyextras.world.dimension.ExtraDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WardenHeartItem extends Item {
    public WardenHeartItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
        );
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null)
            if (context.getPlayer().level().dimension() == ExtraDimensions.OTHERSIDE || context.getPlayer().level().dimension() == Level.OVERWORLD)
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    if (((OthersidePortalBlock) ExtraBlocks.OTHERSIDE_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        MinecraftServer minecraftServer = context.getLevel().getServer();
                        ResourceKey<Level> destination = context.getLevel().dimension() == ExtraDimensions.OTHERSIDE ? Level.OVERWORLD : ExtraDimensions.OTHERSIDE;
                        if (minecraftServer != null)
                            ((OthersidePortalBlock) ExtraBlocks.OTHERSIDE_PORTAL.get()).trySpawnPortal(minecraftServer.getLevel(destination), framePos.above(context.getLevel().dimension() == ExtraDimensions.OTHERSIDE ? -72 : 77));
                        return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
                    } else return InteractionResult.FAIL;
                }
        return InteractionResult.FAIL;
    }
}
