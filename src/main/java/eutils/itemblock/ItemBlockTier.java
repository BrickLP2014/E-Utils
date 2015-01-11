package eutils.itemblock;

import core.itemblock.ItemBlockCoreBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Master801 on 1/2/2015 at 12:37 PM.
 * @author Master801
 */
public class ItemBlockTier extends ItemBlockCoreBase {

    final String name;

    public ItemBlockTier(Block block, String name) {
        super(block, true);
        this.name = name;
    }

    @Override
    protected String getUnlocalizedName(int metadata) {
        return name + metadata;
    }

    @Override
    protected List<String> addInformation(ItemStack stack, EntityPlayer player, boolean var3) {
        return null;
    }

}
