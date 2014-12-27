package eutils;

import core.api.common.mod.IMod;
import core.api.network.proxy.IProxy;
import core.helpers.ModHelper;
import core.helpers.ProxyHelper;
import core.helpers.RegistryHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import eutils.common.resources.EResources;
import eutils.integration.IntegrationCoFH;
import eutils.itemblock.ItemBlockEMCStorage;
import eutils.tileentity.TileEntityEMCStorage;
import eutils.tileentity.TileEntityInfiniteEMC;

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
        RegistryHelper.registerModIntegrationHandlers(new IntegrationCoFH());//Always register the mod integration handlers before we post events to it.
        RegistryHelper.registerTileEntity(TileEntityInfiniteEMC.class, "infiniteEMC");
        RegistryHelper.registerTileEntity(TileEntityEMCStorage.class, "emcStorage");
        RegistryHelper.registerBlock(EResources.BLOCK_INFINITE_EMC, "infiniteEMC");
        RegistryHelper.registerBlock(EResources.BLOCK_EMC_STORAGE, ItemBlockEMCStorage.class, "emcStorage");
        ProxyHelper.addProxyToMapping(EUtils.proxy);
        RegistryHelper.postModEventToIntegrationHandlers(EUtils.instance, event);//Posts the mod integration handlers after the proxy is added.
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHelper.postModEventToIntegrationHandlers(EUtils.instance, event);//Always register the mod integration handlers before we post events to it.
        EUtils.proxy.registerBlockRenderers();
        EUtils.proxy.registerItemRenderers();
        EUtils.proxy.registerTileEntityRenderers();
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
