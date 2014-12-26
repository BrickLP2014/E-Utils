package eutils.integration;

import core.api.common.mod.IMod;
import core.api.integration.IModIntegrationHandler;
import core.block.BlockCoreBase;
import core.helpers.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.EUtils;
import eutils.common.resources.EResources;
import eutils.tileentity.TileEntityRF2EMC;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Master801 on 12/26/2014 at 1:16 PM.
 * @author Master801
 */
public final class IntegrationCoFH implements IModIntegrationHandler {

    public static final BlockCoreBase BLOCK_RF2EMC = new BlockRF2EMC();

    @Override
    public void addModBlocksAndItems() {
        RegistryHelper.registerTileEntity(TileEntityRF2EMC.class, "rf2emc");
        //TODO Register the RF2EMC block too.
    }

    @Override
    public void addModRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 0), "OIO", "IRI", "OIO", 'O', Item.getItemFromBlock(Blocks.obsidian), 'I', Items.iron_ingot, 'R', EResources.getRedMatterBlock((byte)1)));
    }

    @Override
    public IMod getParentMod() {
        return EUtils.instance;
    }

    @Override
    public String getModIDToIntegrateWith() {
        return "CoFHCore";
    }

    public static final class BlockRF2EMC extends BlockCoreBase {

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

}
