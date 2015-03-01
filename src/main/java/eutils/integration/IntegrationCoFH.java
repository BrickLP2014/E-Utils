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
import eutils.tileentity.TileEntityRF2EMC;
import moze_intel.projecte.gameObjs.ObjHandler;
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
        RegistryHelper.registerBlock(IntegrationCoFH.BLOCK_RF2EMC, ItemBlockRF2EMC.class, "rf2emc");
    }

    @Override
    public void addModRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 0), "OIO", "IRI", "OIO", 'O', "obsidian", 'I', "ingotIron", 'R', EResources.getRedMatterBlock(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 1), "OIO", "IRI", "OIO", 'O', "obsidian", 'I', "ingotIron", 'R', new ItemStack(ObjHandler.matterBlock, 1, 1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 2), "OIO", "IRI", "OIO", 'O', "obsidian", 'I', "ingotIron", 'R', ObjHandler.relay));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 3), "OIO", "IRI", "OIO", 'O', "obsidian", 'I', "ingotIron", 'R', ObjHandler.relayMK2));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IntegrationCoFH.BLOCK_RF2EMC, 1, 4), "OIO", "IRI", "OIO", 'O', "obsidian", 'I', "ingotIron", 'R', ObjHandler.relayMK3));
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
