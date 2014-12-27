package eutils.block;

import core.block.BlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityEMCStorage;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master801 on 12/26/2014 at 11:42 PM.
 * @author Master801
 */
public class BlockEMCStorage extends BlockCoreBase {

    public static final int MAX_EMC_STORAGE_0 = 50000000, MAX_EMC_STORAGE_1 = 75000000, MAX_EMC_STORAGE_2 = 100000000;//TODO These numbers are made up. BrickLP2014 should make up better numbers...
    public static final int MAX_EMC_EXTRACT_RATE_0 = 500, MAX_EMC_EXTRACT_RATE_1 = 1250, MAX_EMC_EXTRACT_RATE_2 = 2000;//TODO These numbers are made up. BrickLP2014 should make up better numbers...

    public BlockEMCStorage() {
        super(Material.iron, true);
        setCreativeTab(EResources.getEUtilsCreativeTab());
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerIcon(TextureMap map) {
        blockIcon = map.registerIcon("projecte:dm");//TODO
    }

    @Override
    public String getAdjustedUnlocalizedName() {
        return null;
    }

    @Override
    public TileEntity createTileEntity(int metadata) {
        switch(metadata) {
            case 0:
                return new TileEntityEMCStorage(BlockEMCStorage.MAX_EMC_STORAGE_0, BlockEMCStorage.MAX_EMC_EXTRACT_RATE_0);
            case 1:
                return new TileEntityEMCStorage(BlockEMCStorage.MAX_EMC_STORAGE_1, BlockEMCStorage.MAX_EMC_EXTRACT_RATE_1);
            case 2:
                return new TileEntityEMCStorage(BlockEMCStorage.MAX_EMC_STORAGE_2, BlockEMCStorage.MAX_EMC_EXTRACT_RATE_2);
            default:
                return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected List<ItemStack> addBlocksToCreativeTab() {
        final List<ItemStack> list = new ArrayList<ItemStack>();
        for(int i = 0; i < 3; i++) {
            list.add(new ItemStack(this, 1, i));
        }
        return list;
    }

}
