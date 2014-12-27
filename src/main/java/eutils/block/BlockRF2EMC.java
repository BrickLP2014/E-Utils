package eutils.block;

import core.block.BlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityRF2EMC;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Master801 on 12/26/2014 at 8:25 PM.
 * @author Master801
 */
public final class BlockRF2EMC extends BlockCoreBase {

    public static final int[] MAX_EMC_STORAGES = new int[] { 1000000, 2000000, 3000000, 4000000, 5000000 };//We can always change this.//TODO 1-4 are made up numbers
    public static final int[] MAX_EMC_EXTRACT_RATES = new int[] { 175, 500, 1000, 2000, 3000 };//TODO I made all these numbers up, hopefully BrickLP2014 can make up a better ones than these.
    public static final int[] MAX_RF_STORAGES = new int[] { 10000, 20000, 30000, 40000, 50000 };//TODO 1-4 are made up numbers
    public static final int[] MAX_RF_EXTRACT_RATES = new int[] { 500, 1000, 1500, 2000, 2500 };//TODO 1-4 are made up numbers
    public static final int[] MAX_RF_RECEIVE_RATES = new int[] { 1000, 2000, 3000, 4000, 5000 };//TODO 1-4 are made up numbers

    public BlockRF2EMC() {
        super(Material.iron, true);
        setCreativeTab(EResources.E_UTILS_CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerIcon(TextureMap map) {
        blockIcon = map.registerIcon(EResources.E_UTILS_MOD_ID + ":" + "RF2EMC");//TODO
    }

    @Override
    public String getAdjustedUnlocalizedName() {
        return null;
    }

    @Override
    public TileEntity createTileEntity(int metadata) {
        return new TileEntityRF2EMC(BlockRF2EMC.MAX_RF_STORAGES[metadata], BlockRF2EMC.MAX_RF_EXTRACT_RATES[metadata], BlockRF2EMC.MAX_RF_RECEIVE_RATES[metadata], BlockRF2EMC.MAX_EMC_STORAGES[metadata], BlockRF2EMC.MAX_EMC_EXTRACT_RATES[metadata]);
    }

    @Override
    public int getAmountOfSubtypes() {
        return 5;
    }

}
