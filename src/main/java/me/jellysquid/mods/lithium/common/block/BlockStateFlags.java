package me.jellysquid.mods.lithium.common.block;

import me.jellysquid.mods.lithium.common.ai.pathing.BlockStatePathingCache;
import me.jellysquid.mods.lithium.common.ai.pathing.PathNodeCache;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.chunk.ChunkSection;

public class BlockStateFlags {
    public static final boolean ENABLED = BlockCountingSection.class.isAssignableFrom(ChunkSection.class);
    public static final int NUM_FLAGS;

    public static final IndexedBlockStatePredicate OVERSIZED_SHAPE;
    public static final IndexedBlockStatePredicate PATH_NOT_OPEN;
    public static final IndexedBlockStatePredicate WATER;
    public static final IndexedBlockStatePredicate LAVA;

    static {
        int numFlags = 0;

        numFlags++;
        OVERSIZED_SHAPE = new IndexedBlockStatePredicate() {
            @Override
            public boolean test(BlockState operand) {
                return operand.exceedsCube();
            }
        };

        numFlags++;
        WATER = new IndexedBlockStatePredicate() {
            @Override
            public boolean test(BlockState operand) {
                return operand.getFluidState().getFluid().isIn(FluidTags.WATER);
            }
        };

        numFlags++;
        LAVA = new IndexedBlockStatePredicate() {
            @Override
            public boolean test(BlockState operand) {
                return operand.getFluidState().getFluid().isIn(FluidTags.LAVA);
            }
        };

        if (BlockStatePathingCache.class.isAssignableFrom(AbstractBlock.AbstractBlockState.class)) {
            numFlags++;
            PATH_NOT_OPEN = new IndexedBlockStatePredicate() {
                @Override
                public boolean test(BlockState operand) {
                    return PathNodeCache.getNeighborPathNodeType(operand) != PathNodeType.OPEN;
                }
            };
        } else {
            PATH_NOT_OPEN = null;
        }

        NUM_FLAGS = numFlags;
    }
    //Don't forget to update NUM_FLAGS when adding more
}