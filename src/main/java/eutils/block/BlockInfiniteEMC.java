package eutils.block;

import core.block.BlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityInfiniteEMC;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Master801 on 12/26/2014 at 8:20 PM.
 * @author Master801
 */
public final class BlockInfiniteEMC extends BlockCoreBase {

    public BlockInfiniteEMC() {
        super(Material.piston, true);
        setCreativeTab(EResources.E_UTILS_CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerIcon(TextureMap map) {
        blockIcon = map.registerIcon("projecte:rm");
    }

    @Override
    public String getAdjustedUnlocalizedName() {
        return "infiniteEMC";
    }

    @Override
    public TileEntity createTileEntity(int metadata) {
        return new TileEntityInfiniteEMC();
    }

}
