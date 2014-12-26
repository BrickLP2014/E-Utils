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
    public void updateEntity() {//Exploiting a bug in the code.
        addEmc(-getStoredEmc());//Sets the stored emc to zero.
        addEmc(-1);//Adds a negative one to the stored emc. AKA this is where the magic happens.
        //The "addEMC" code checks if the emc stored is either either bigger than the max capacity, and/or less than zero (bug! should be else if statement), then sets the stored amount to the max capacity.
    }

    @Override
    public boolean isRequestingEmc() {
        return true;
    }

}
