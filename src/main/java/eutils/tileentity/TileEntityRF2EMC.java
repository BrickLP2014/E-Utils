package eutils.tileentity;

import moze_intel.projecte.gameObjs.tiles.TileEmcProducer;

/**
 * Created by Master801 on 12/26/2014 at 1:14 PM.
 * @author Master801
 */
public class TileEntityRF2EMC extends TileEmcProducer {

    @Override
    public boolean isRequestingEmc() {
        return false;
    }

}
