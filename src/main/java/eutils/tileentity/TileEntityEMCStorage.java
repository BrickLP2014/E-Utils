package eutils.tileentity;

import core.api.tileentity.IActivate;
import core.helpers.PlayerHelper;
import core.helpers.RandomHelper;
import moze_intel.projecte.gameObjs.tiles.TileEmcProducer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Master801 on 12/26/2014 at 11:32 PM.
 * @author Master801
 */
public class TileEntityEMCStorage extends TileEmcProducer implements IActivate {

    final int extractRate;

    public TileEntityEMCStorage(int storage, int extractRate) {
        super(storage);
        this.extractRate = extractRate;
    }

    @Override
    public boolean isRequestingEmc() {
        return getStoredEmc() < getMaxEmc();
    }

    @Override
    public void updateEntity() {
        if (getStoredEmc() > 0 && getStoredEmc() <= getMaxEmc()) {
            sendEMCPower();
        }
    }

    @Override
    public boolean onActivated(EntityPlayer player, ForgeDirection side) {
        PlayerHelper.addAdvancedChatMessage(worldObj, player, "EMC: %d/%d", RandomHelper.convertDoubleToInteger(getStoredEmc()), getMaxEmc());
        return true;
    }

    protected void sendEMCPower() {
        if (getStoredEmc() <= 0.0D || getStoredEmc() >= getMaxEmc()) {
            return;
        }
        checkSurroundingBlocks(false);
        int amountOfReceivers = super.getNumRequesting();
        if (amountOfReceivers > 0) {
            double emcToSend;/** !!DISCLAIMER!! Method was ripped out of {@link moze_intel.projecte.gameObjs.tiles.CollectorMK1Tile.updateEMC()} and modified to my preferences and needs. -Master801*/
            if (getStoredEmc() <= extractRate) {
                emcToSend = extractRate;//Send only the allowed rate at a time.
            } else {
                emcToSend = getStoredEmc();//Send all of the emc at a time.
            }
            sendEmcToRequesting(emcToSend / amountOfReceivers);//Sends emc to consumers around itself.
            removeEmc(emcToSend);
        }
    }

}
