package eutils.common.resources;

import core.common.resources.CoreResources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.PECore;
import moze_intel.projecte.gameObjs.ObjHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Master801 on 12/26/2014 at 12:32 PM.
 * @author Master801
 */
public final class EResources {

    public static final String E_UTILS_MOD_ID = "E-Utils";
    public static final String E_UTILS_NAME = "E-Utils";
    public static final String E_UTILS_VERSION = "@VERSION@";
    public static final String E_UTILS_DEPENDENCIES = "required-after:" + CoreResources.CORE_MODID + "; required-after:" + PECore.MODID + "; after:CoFHCore";
    public static final String E_UTILS_PROXY_SERVER = "eutils.proxies.EClientProxy";
    public static final String E_UTILS_PROXY_CLIENT = "eutils.proxies.EServerProxy";
    public static final CreativeTabs E_UTILS_CREATIVE_TAB = new CreativeTabs("e-utils") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return EResources.getDarkMatterItem((byte)1).getItem();
        }

    };

    public static ItemStack getDarkMatterBlock(byte stackSize) {
        return new ItemStack(ObjHandler.matterBlock, stackSize, 0);
    }

    public static ItemStack getRedMatterBlock(byte stackSize) {
        return new ItemStack(ObjHandler.matterBlock, stackSize, 1);
    }

    public static ItemStack getDarkMatterItem(byte stackSize) {
        return new ItemStack(ObjHandler.matter, stackSize, 0);
    }

    public static ItemStack getRedMatterItem(byte stackSize) {
        return new ItemStack(ObjHandler.matter, stackSize, 0);
    }

}
