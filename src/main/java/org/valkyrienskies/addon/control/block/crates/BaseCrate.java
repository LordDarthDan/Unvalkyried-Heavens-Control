package org.valkyrienskies.addon.control.block.crates;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.valkyrienskies.addon.control.util.BaseBlock;

public class BaseCrate extends BaseBlock {
	protected static final PropertyBool CONSTRUCTED = PropertyBool.create("constructed");

	protected BaseCrate(String name, float hardness) {
		super(name, Material.WOOD, 0, false);
		this.setHardness(hardness);
		this.setDefaultState(blockState.getBaseState().withProperty(CONSTRUCTED, false));
	}

	protected BaseCrate(String name, Material mat, float hardness) {
		super(name, mat, 0, false);
		this.setHardness(hardness);
		this.setDefaultState(blockState.getBaseState().withProperty(CONSTRUCTED, false));
	}

    @Override
    public void addInformation(ItemStack stack, @Nullable World player,
        List<String> itemInformation, ITooltipFlag advanced) {
        itemInformation.add(TextFormatting.BLUE + I18n.format("tooltip.vs_control." + this.getRegistryName().getPath()));
    }

    // Lighting stuff
    @Override
    public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	// Blockstate stuff
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONSTRUCTED, false);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CONSTRUCTED});
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CONSTRUCTED, meta == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(CONSTRUCTED) ? 1 : 0;
	}
}