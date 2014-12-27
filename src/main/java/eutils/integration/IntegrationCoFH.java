package eutils.integration;

import core.api.common.mod.IMod;
import core.api.integration.IModIntegrationHandler;
import core.block.BlockCoreBase;
import core.helpers.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import eutils.EUtils;
import eutils.block.BlockRF2EMC;
import eutils.common.resources.EResources;
import eutils.itemblock.ItemBlockRF2EMC;
import eutils.tileentity.TileEntityInfiniteEMC;
import eutils.tileentity.TileEntityRF2EMC;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        RegistryHelper.registerTileEntity(TileEntityInfiniteEMC.class, "infiniteEMC");
        RegistryHelper.registerBlock(IntegrationCoFH.BLOCK_RF2EMC, ItemBlockRF2EMC.class, "rf2emc");
    }

    @Override
    public void addModRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 0), "OIO", "IRI", "OIO", 'O', Item.getItemFromBlock(Blocks.obsidian), 'I', Items.iron_ingot, 'R', EResources.getRedMatterBlock(1)));
    }

    @Override
    public IMod getParentMod() {
        return EUtils.instance;
    }

    @Override
    public String getModIDToIntegrateWith() {
        return "CoFHCore";
    }

}
