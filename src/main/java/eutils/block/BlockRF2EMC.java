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

    public static final int MAX_EMC_STORAGE_0 = 1000000, MAX_EMC_STORAGE_1 = 2000000, MAX_EMC_STORAGE_2 = 2000000, MAX_EMC_STORAGE_3 = 2000000, MAX_EMC_STORAGE_4 = 2000000;//We can always change this.//TODO 1-4 are made up numbers
    public static final int MAX_EMC_EXTRACT_RATE_0 = 175, MAX_EMC_EXTRACT_RATE_1 = 500, MAX_EMC_EXTRACT_RATE_2 = 1000, MAX_EMC_EXTRACT_RATE_3 = 2000, MAX_EMC_EXTRACT_RATE_4 = 3000;//TODO I made all these numbers up, hopefully BrickLP2014 can make up a better ones than these.
    public static final int MAX_RF_STORAGE_0 = 10000, MAX_RF_STORAGE_1 = 20000, MAX_RF_STORAGE_2 = 30000, MAX_RF_STORAGE_3 = 40000, MAX_RF_STORAGE_4 = 50000;//TODO 1-4 are made up numbers
    public static final int MAX_RF_EXTRACT_RATE_0 = 500, MAX_RF_EXTRACT_RATE_1 = 1000, MAX_RF_EXTRACT_RATE_2 = 1500, MAX_RF_EXTRACT_RATE_3 = 2000, MAX_RF_EXTRACT_RATE_4 = 2500, MAX_RF_EXTRACT_RATE_5 = 3000;//TODO 1-4 are made up numbers
    public static final int MAX_RF_RECEIVE_RATE_0 = 1000, MAX_RF_RECEIVE_RATE_1 = 2000, MAX_RF_RECEIVE_RATE_2 = 3000, MAX_RF_RECEIVE_RATE_3 = 4000, MAX_RF_RECEIVE_RATE_4 = 5000;//TODO 1-4 are made up numbers

    public BlockRF2EMC() {
        super(Material.iron, true);
        setCreativeTab(EResources.getEUtilsCreativeTab());
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
        int rfStorage = BlockRF2EMC.MAX_RF_STORAGE_0;
        int rfExtraction = BlockRF2EMC.MAX_RF_EXTRACT_RATE_0;
        int rfReceive = BlockRF2EMC.MAX_RF_RECEIVE_RATE_0;
        int storage = BlockRF2EMC.MAX_EMC_STORAGE_0;
        int extraction = BlockRF2EMC.MAX_EMC_EXTRACT_RATE_0;
        switch(metadata) {
            case 1:
                storage = BlockRF2EMC.MAX_EMC_STORAGE_1;
                rfExtraction = BlockRF2EMC.MAX_RF_EXTRACT_RATE_1;
                rfReceive = BlockRF2EMC.MAX_RF_RECEIVE_RATE_1;
                extraction = BlockRF2EMC.MAX_EMC_EXTRACT_RATE_1;
                rfStorage = BlockRF2EMC.MAX_RF_STORAGE_1;
                break;
            case 2:
                storage = BlockRF2EMC.MAX_EMC_STORAGE_2;
                rfExtraction = BlockRF2EMC.MAX_RF_EXTRACT_RATE_2;
                rfReceive = BlockRF2EMC.MAX_RF_RECEIVE_RATE_2;
                extraction = BlockRF2EMC.MAX_EMC_EXTRACT_RATE_2;
                rfStorage = BlockRF2EMC.MAX_RF_STORAGE_2;
                break;
            case 3:
                storage = BlockRF2EMC.MAX_EMC_STORAGE_3;
                rfExtraction = BlockRF2EMC.MAX_RF_EXTRACT_RATE_3;
                rfReceive = BlockRF2EMC.MAX_RF_RECEIVE_RATE_3;
                extraction = BlockRF2EMC.MAX_EMC_EXTRACT_RATE_3;
                rfStorage = BlockRF2EMC.MAX_RF_STORAGE_3;
                break;
            case 4:
                storage = BlockRF2EMC.MAX_EMC_STORAGE_4;
                rfExtraction = BlockRF2EMC.MAX_RF_EXTRACT_RATE_4;
                rfReceive = BlockRF2EMC.MAX_RF_RECEIVE_RATE_4;
                extraction = BlockRF2EMC.MAX_EMC_EXTRACT_RATE_4;
                rfStorage = BlockRF2EMC.MAX_RF_STORAGE_4;
                break;
        }
        return new TileEntityRF2EMC(rfStorage, rfExtraction, rfReceive, storage, extraction);
    }

    @Override
    public int getAmountOfSubtypes() {
        return 5;
    }

}
