package eutils.tileentity;

import moze_intel.projecte.gameObjs.tiles.TileEmcProducer;

/**
 * Created by Master801 on 12/26/2014 at 2:45 PM.
 * @author Master801
 */
public final class TileEntityInfiniteEMC extends TileEmcProducer {

    public TileEntityInfiniteEMC() {
        super(999999999);
    }

    @Override
    public void updateEntity() {
        checkSurroundingBlocks(false);
        int amountOfReceivers = super.getNumRequesting();
        if (amountOfReceivers > 0) {
            sendEmcToRequesting(getMaxEmc() / amountOfReceivers);
        }
    }

    @Override
    public boolean isRequestingEmc() {
        return false;//Returns false since we do not ever want to consume emc.
    }

}
