package eutils;

import core.api.common.mod.IMod;
import core.api.network.proxy.IProxy;
import core.helpers.ModHelper;
import core.helpers.ProxyHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import eutils.common.resources.EResources;

/**
 * Created by Master801 on 12/26/2014 at 12:27 PM.
 * @author Master801
 */
@Mod(modid = EResources.E_UTILS_MOD_ID, name = EResources.E_UTILS_NAME, version = EResources.E_UTILS_VERSION, dependencies = EResources.E_UTILS_DEPENDENCIES)
public final class EUtils implements IMod {

    @Instance(EResources.E_UTILS_MOD_ID)
    public static IMod instance;

    @SidedProxy(serverSide = EResources.E_UTILS_PROXY_SERVER, clientSide = EResources.E_UTILS_PROXY_CLIENT)
    public static IProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ModHelper.addChildMod(EUtils.class);//Should always be done first.

        ProxyHelper.addProxyToMapping(EUtils.proxy);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
    }

    @Override
    public String getModID() {
        return EResources.E_UTILS_MOD_ID;
    }

    @Override
    public IMod getInstance() {
        return EUtils.instance;
    }

}
