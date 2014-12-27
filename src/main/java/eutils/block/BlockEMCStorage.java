package eutils.block;

import core.block.BlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityEMCStorage;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Master801 on 12/26/2014 at 11:42 PM.
 * @author Master801
 */
public class BlockEMCStorage extends BlockCoreBase {

    public static final int[] MAX_EMC_STORAGES = new int[] { 50000000, 75000000, 100000000 };//TODO These numbers are made up. BrickLP2014 should make up better numbers...
    public static final int[] MAX_EMC_EXTRACT_RATES = new int[] { 500, 1250, 2000 };//TODO These numbers are made up. BrickLP2014 should make up better numbers...

    public BlockEMCStorage() {
        super(Material.iron, true);
        setCreativeTab(EResources.E_UTILS_CREATIVE_TAB);
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
        return new TileEntityEMCStorage(BlockEMCStorage.MAX_EMC_STORAGES[metadata], BlockEMCStorage.MAX_EMC_EXTRACT_RATES[metadata]);
    }

    @Override
    public int getAmountOfSubtypes() {
        return 3;
    }

}
