package eutils;

import core.api.common.mod.IMod;
import core.api.network.proxy.IProxy;
import core.helpers.ModHelper;
import core.helpers.ProxyHelper;
import core.helpers.RegistryHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import eutils.common.resources.EResources;
import eutils.common.resources.EUtilsModMetadata;
import eutils.integration.IntegrationCoFH;
import eutils.itemblock.ItemBlockTier;
import eutils.tileentity.TileEntityEMCItemRecharger;
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

    @Metadata(EResources.E_UTILS_MOD_ID)
    public static ModMetadata metadata;

    @SidedProxy(serverSide = EResources.E_UTILS_PROXY_SERVER, clientSide = EResources.E_UTILS_PROXY_CLIENT)
    public static IProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        try {
            ModHelper.addChildMod(EUtils.class);//Should always be done first.
        } catch(Exception e) {
            e.printStackTrace();
        }
        ModHelper.handshakeMetadata(EUtils.metadata, EUtilsModMetadata.INSTANCE);
        RegistryHelper.registerModIntegrationHandlers(new IntegrationCoFH());//Always register the mod integration handlers before we post events to it.
        GameRegistry.registerTileEntity(TileEntityInfiniteEMC.class, "infiniteEMC");
        GameRegistry.registerTileEntity(TileEntityEMCStorage.class, "emcStorage");
        GameRegistry.registerTileEntity(TileEntityEMCItemRecharger.class, "emcItemRecharger");
        GameRegistry.registerBlock(EResources.BLOCK_INFINITE_EMC, "infiniteEMC");
        GameRegistry.registerBlock(EResources.BLOCK_EMC_STORAGE, ItemBlockTier.class, "emcStorage", new Object[] { "emcStorage.mk" });
        GameRegistry.registerBlock(EResources.BLOCK_EMC_ITEM_RECHARGER, ItemBlockTier.class, "emcItemRecharger", new Object[] { "emcItemRecharger.mk" });
        ProxyHelper.addProxyToMapping(EUtils.proxy);
        RegistryHelper.postModEventToIntegrationHandlers(EUtils.instance, event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHelper.postModEventToIntegrationHandlers(EUtils.instance, event);
        RegistryHelper.registerGuiHandler(EUtils.instance);
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
