package eutils.itemblock;

import core.itemblock.ItemBlockCoreBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Master801 on 12/26/2014 at 11:47 PM.
 * @author Master801
 */
public class ItemBlockEMCStorage extends ItemBlockCoreBase {

    public ItemBlockEMCStorage(Block block) {
        super(block, true);
    }

    @Override
    protected String getUnlocalizedName(int metadata) {
        return "emcStorage.mk" + metadata;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected List<String> addInformation(ItemStack stack, EntityPlayer player, boolean var3) {
        return null;
    }

}
