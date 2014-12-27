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

    public BlockRF2EMC() {
        super(Material.iron, false);
        setCreativeTab(EResources.E_UTILS_CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerIcon(TextureMap map) {
        blockIcon = map.registerIcon(EResources.E_UTILS_MOD_ID + ":" + "RF2EMC");
    }

    @Override
    public String getAdjustedUnlocalizedName() {
        return "rf2emc";
    }

    @Override
    public TileEntity createTileEntity(int metadata) {
        return new TileEntityRF2EMC();
    }

}
