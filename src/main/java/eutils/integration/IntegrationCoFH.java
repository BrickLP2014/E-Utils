package eutils.integration;

import core.api.common.mod.IMod;
import core.api.integration.IModIntegrationHandler;
import core.helpers.RegistryHelper;
import eutils.EUtils;
import eutils.tileentity.TileEntityRF2EMC;

/**
 * Created by Master801 on 12/26/2014 at 1:16 PM.
 * @author Master801
 */
public final class IntegrationCoFH implements IModIntegrationHandler {

    @Override
    public void addModBlocksAndItems() {
        RegistryHelper.registerTileEntity(TileEntityRF2EMC.class, "rf2emc");
        //TODO Register the RF2EMC block too.
    }

    @Override
    public void addModRecipes() {
    }

    @Override
    public IMod getParentMod() {
        return EUtils.instance;
    }

    @Override
    public String getModIDToIntegrateWith() {
        return null;
    }

}
