package eutils.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import core.common.resources.CoreEnums.LoggerEnum;
import core.helpers.LoggerHelper;
import core.helpers.RandomHelper;
import eutils.EUtils;
import moze_intel.projecte.gameObjs.tiles.TileEmcProducer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Master801 on 12/26/2014 at 1:14 PM.
 * @author Master801
 */
public class TileEntityRF2EMC extends TileEmcProducer implements IEnergyReceiver {

    public static final int MAX_RF_STORAGE = 10000;
    public static final int MAX_RF_EXTRACT_RATE = 500;
    public static final int MAX_RF_RECEIVE_RATE = 1000;

    public static final int MAX_EMC_STORAGE = 1000000;//We can always change this.

    public final EnergyStorage storage;

    public TileEntityRF2EMC() {
        super(TileEntityRF2EMC.MAX_EMC_STORAGE);
        storage = new EnergyStorage(TileEntityRF2EMC.MAX_RF_STORAGE, TileEntityRF2EMC.MAX_RF_RECEIVE_RATE, TileEntityRF2EMC.MAX_RF_EXTRACT_RATE);
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
        checkStorage();
    }

    protected void convertPower() {//Remember that this is getting updated every tick!
        if (storage.getEnergyStored() <= 0 || getStoredEmc() > RandomHelper.convertIntegerToDouble(getMaxEmc()).doubleValue()) {
            return;//Stop trying to convert power if stored rf is equal to or less than zero and/or if the currently stored emc is larger than the machine can store.
        }
        if (storage.getEnergyStored() > 0) {//Checks if the rf stored is bigger than zero.
            int extractedEnergy = storage.extractEnergy(TileEntityRF2EMC.MAX_RF_EXTRACT_RATE, true);//The amount of RF this should take from this machine. (This is a simulation!)
            if (extractedEnergy <= 0) {
                return;
            }
            extractedEnergy = storage.extractEnergy(TileEntityRF2EMC.MAX_RF_EXTRACT_RATE, false);//The amount of RF this has taken from this machine (storage).
            int emc = TileEntityRF2EMC.convertRFToEMC(extractedEnergy);
            if (emc <= 0) {
                return;
            }
            addEmc(emc);
        }
    }

    protected void checkStorage() {//Remember that this is getting updated every tick!
        if (storage.getEnergyStored() < 0) {//Checks if we took too much rf out of the storage.
            storage.setEnergyStored(0);
        } else if (storage.getEnergyStored() > storage.getMaxEnergyStored()) {//Checks if we put too much rf in the storage.
            storage.setEnergyStored(storage.getMaxEnergyStored());
        }
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
        Object convertedEMC = rf / 2;
        if (!(convertedEMC instanceof Integer)) {
            LoggerHelper.addAdvancedMessageToLogger(EUtils.instance, LoggerEnum.WARN, "The amount of converted EMC is not valid (not an instance of Integer)!");
            return 0;
        }
        return (Integer)convertedEMC;
    }

}
