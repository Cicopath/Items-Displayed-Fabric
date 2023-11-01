package net.freedinner.items_displayed.block.custom.stackable;

import net.freedinner.items_displayed.block.custom.AbstractItemBlock;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.freedinner.items_displayed.util.BlockPlacer;
import net.freedinner.items_displayed.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GemstoneBlock extends AbstractStackableItemBlock {
    public static final VoxelShape NORTH_SOUTH_SHAPE_1 =
            Block.createCuboidShape(6.0, 0.0, 5.0, 10.0, 2.0, 11.0);
    public static final VoxelShape EAST_WEST_SHAPE_1 =
            Block.createCuboidShape(5.0, 0.0, 6.0, 11.0, 2.0, 10.0);
    public static final VoxelShape NORTH_SOUTH_SHAPE_2 =
            Block.createCuboidShape(3.0, 0.0, 2.0, 13.0, 2.0, 14.0);
    public static final VoxelShape EAST_WEST_SHAPE_2 =
            Block.createCuboidShape(2.0, 0.0, 3.0, 14.0, 2.0, 13.0);
    public static final VoxelShape SHAPE_3 =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 2.0, 14.0);
    public static final VoxelShape SHAPE_4 =
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);

    public GemstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected IntProperty getItemProperty() {
        return ModProperties.GEMSTONES;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int currItemCount = state.get(getItemProperty());

        return switch (currItemCount) {
            case 1 -> switch (state.get(FACING)) {
                case NORTH, SOUTH -> NORTH_SOUTH_SHAPE_1;
                case EAST, WEST -> EAST_WEST_SHAPE_1;
                default -> throw new IllegalStateException("Unexpected value: " + state.get(FACING));
            };
            case 2 -> switch (state.get(FACING)) {
                case NORTH, SOUTH -> NORTH_SOUTH_SHAPE_2;
                case EAST, WEST -> EAST_WEST_SHAPE_2;
                default -> throw new IllegalStateException("Unexpected value: " + state.get(FACING));
            };
            case 3 -> SHAPE_3;
            case 4 -> SHAPE_4;
            default -> throw new IllegalStateException("Unexpected value: " + currItemCount);
        };
    }
}