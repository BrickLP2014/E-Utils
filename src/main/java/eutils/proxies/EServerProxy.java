package eutils.proxies;

import core.api.common.mod.IMod;
import core.api.network.proxy.IProxy;
import cpw.mods.fml.relauncher.Side;
import eutils.EUtils;

/**
 * Created by Master801 on 12/26/2014 at 12:42 PM.
 * @author Master801
 */
public final class EServerProxy implements IProxy {

    @Override
    public void registerBlockRenderers() {
        //Do nothing
    }

    @Override
    public void registerTileEntityRenderers() {
        //Do nothing
    }

    @Override
    public void registerItemRenderers() {
        //Do nothing
    }

    @Override
    public void addRenderID(String id, int renderID) {
        //Do nothing
    }

    @Override
    public int getRenderIDFromSpecialID(String id) {
        return 0;//Do nothing
    }

    @Override
    public Side getSide() {
        return Side.SERVER;
    }

    @Override
    public IMod getOwningMod() {
        return EUtils.instance;
    }

}
