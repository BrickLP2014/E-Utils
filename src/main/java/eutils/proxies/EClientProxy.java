package eutils.proxies;

import core.api.common.mod.IMod;
import core.api.network.proxy.IProxy;
import core.helpers.ProxyHelper;
import cpw.mods.fml.relauncher.Side;
import eutils.EUtils;

/**
 * Created by Master801 on 12/26/2014 at 12:39 PM.
 * @author Master801
 */
public final class EClientProxy implements IProxy {

    @Override
    public void registerBlockRenderers() {
    }

    @Override
    public void registerTileEntityRenderers() {
    }

    @Override
    public void registerItemRenderers() {
    }

    @Override
    public void addRenderID(String id, int renderID) {
        ProxyHelper.addRenderIDToMapping(ProxyHelper.getSidedProxyFromMod(getOwningMod(), getSide()), id, renderID);
    }

    @Override
    public int getRenderIDFromSpecialID(String id) {
        return ProxyHelper.getRenderIDFromSpecialID(ProxyHelper.getSidedProxyFromMod(getOwningMod(), getSide()), id);
    }

    @Override
    public Side getSide() {
        return Side.CLIENT;
    }

    @Override
    public IMod getOwningMod() {
        return EUtils.instance;
    }

}
