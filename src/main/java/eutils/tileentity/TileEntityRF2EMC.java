package eutils.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import core.api.tileentity.IActivate;
import core.common.resources.CoreEnums.LoggerEnum;
import core.helpers.LoggerHelper;
import core.helpers.PlayerHelper;
import core.helpers.RandomHelper;
import eutils.EUtils;
import moze_intel.projecte.gameObjs.tiles.TileEmcProducer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Master801 on 12/26/2014 at 1:14 PM.
 * @author Master801
 */
public class TileEntityRF2EMC extends TileEmcProducer implements IEnergyReceiver, IActivate {

    public final EnergyStorage storage;
    private final int extractRate;

    public TileEntityRF2EMC(int rfStorage, int rfReceive, int rfExtract, int maxAmount, int extractRate) {
        super(maxAmount);
        storage = new EnergyStorage(rfStorage, rfReceive, rfExtract);
        this.extractRate = extractRate;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagCompound storageNBT = nbt.getCompoundTag("rfStorage");
        if (storageNBT != null) {
            storage.readFromNBT(storageNBT);
        } else {
            LoggerHelper.addAdvancedMessageToLogger(EUtils.instance, LoggerEnum.WARN, "Failed to read tile-entity RF2EMC's nbt! [The NBT compound is null]");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagCompound storageNBT = new NBTTagCompound();
        storage.writeToNBT(storageNBT);
    }

    @Override
    public void updateEntity() {//Remember that this is getting updated every tick!
        convertPower();
        if (!checkStorage()) {
            sendEMCPower();
        }
    }

    protected void convertPower() {//Remember that this is getting updated every tick!
        if (storage.getEnergyStored() <= 0 || getStoredEmc() >= getMaxEmc()) {
            return;//Stop trying to convert power if stored rf is equal to or less than zero and/or if the currently stored emc is larger than the machine can store.
        }
        if (storage.getEnergyStored() > 0 && getStoredEmc() < getMaxEmc()) {//Checks if the rf stored is bigger than zero and amount of stored emc is less than the max capacity.
            int extractedEnergy = storage.extractEnergy(storage.getMaxExtract(), true);//The amount of RF this should take from this machine. (This is a simulation!)
            if (extractedEnergy <= 0) {
                return;
            }
            extractedEnergy = storage.extractEnergy(storage.getMaxExtract(), false);//The amount of RF this has taken from this machine (storage).
            int emc = TileEntityRF2EMC.convertRFToEMC(extractedEnergy);
            if (emc <= 0) {
                return;
            }
            addEmc(emc);
        }
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
            sendRelayBonus();//Adds a emc bonus if the consumers around is a relay.
            removeEmc(emcToSend);
        }
    }

    /**
     * @return False if no errors were found. True is errors were found.
     */
    protected boolean checkStorage() {//Remember that this is getting updated every tick!
        if (storage.getEnergyStored() < 0) {//Checks if we took too much rf out of the storage.
            storage.setEnergyStored(0);
            return true;
        } else if (storage.getEnergyStored() > storage.getMaxEnergyStored()) {//Checks if we put too much rf in the storage.
            storage.setEnergyStored(storage.getMaxEnergyStored());
            return true;
        }
        return false;
    }

    @Override
    public final boolean isRequestingEmc() {
        return false;//Returns false since we do not ever want to consume emc.
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    /**
     * Ex: 10 RF = 5 EMC
     * / 2
     */
    public static int convertRFToEMC(int rf) {
        return rf / 2;
    }

    @Override
    public boolean onActivated(EntityPlayer player, ForgeDirection side) {
        PlayerHelper.addAdvancedChatMessage(player, "RF: %d/%d", storage.getEnergyStored(), storage.getMaxEnergyStored());
        PlayerHelper.addAdvancedChatMessage(player, "EMC: %d/%d", RandomHelper.convertDoubleToInteger(getStoredEmc()).intValue(), getMaxEmc());
        return true;
    }

}
