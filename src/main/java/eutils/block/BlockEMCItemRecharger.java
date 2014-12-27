package eutils.block;

import core.block.BlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityEMCItemRecharger;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Master801 on 12/27/2014 at 12:33 PM.
 * @author Master801
 */
public class BlockEMCItemRecharger extends BlockCoreBase {

    public static final int[] MAX_EMC_STORAGES = new int[] { 30000010, 60400000, 95000000 };//TODO Another batch of made-up numbers, hopefully BrickLP2014 can make up better numbers than these.
    public static final int[] EMC_ITEM_TRANSFER_RATES = new int[] { 500, 1000, 2000 };

    public BlockEMCItemRecharger() {
        super(Material.iron, true);
        setCreativeTab(EResources.E_UTILS_CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerIcon(TextureMap map) {
    }

    @Override
    public String getAdjustedUnlocalizedName() {
        return null;
    }

    @Override
    public TileEntity createTileEntity(int metadata) {
        return new TileEntityEMCItemRecharger(BlockEMCItemRecharger.MAX_EMC_STORAGES[metadata], BlockEMCItemRecharger.EMC_ITEM_TRANSFER_RATES[metadata], (byte)metadata);
    }

}
