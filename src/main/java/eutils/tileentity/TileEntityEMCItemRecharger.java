package eutils.tileentity;

import core.helpers.InventoryHelper;
import core.helpers.RotationHelper;
import moze_intel.projecte.api.IItemCharge;
import moze_intel.projecte.gameObjs.tiles.TileEmc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Master801 on 12/26/2014 at 9:32 PM.
 * @author Master801
 */
public class TileEntityEMCItemRecharger extends TileEmc implements ISidedInventory {//W.I.P.!

    public static final int MAX_EMC_STORAGE = 30000000;//TODO Another made-up number, hopefully BrickLP2014 can make up better numbers.

    public static final int[] ALLOWED_SLOTS_FOR_UNCHARGED_ITEMS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, };
    public static final int[] ALLOWED_SLOTS_FOR_CHARGED_ITEMS = new int[] { 8, 9, 10, 11, 12, 13, 14, 15, };

    private final ItemStack[] inventory = new ItemStack[16];
    private final byte markUpgrade;//The tier this recharger is.

    public TileEntityEMCItemRecharger(byte markUpgrade) {
        super(TileEntityEMCItemRecharger.MAX_EMC_STORAGE);
        this.markUpgrade = markUpgrade;
    }

    @Override
    public boolean isRequestingEmc() {
        return getStoredEmc() < getMaxEmc();
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == RotationHelper.convertForgeDirectionToInt(ForgeDirection.UP)) {
            return TileEntityEMCItemRecharger.ALLOWED_SLOTS_FOR_UNCHARGED_ITEMS;
        }
        return TileEntityEMCItemRecharger.ALLOWED_SLOTS_FOR_CHARGED_ITEMS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack slotStack, int side) {
        return isItemValidForSlot(slot, slotStack) && getStackInSlot(slot) == null;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack slotStack, int side) {
        return getStackInSlot(slot) != null;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return InventoryHelper.decrStackSize(p_70298_1_, p_70298_2_, inventory, this);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return InventoryHelper.getStackInSlotOnClosing(slot, inventory);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        InventoryHelper.setInventorySlotContents(slot, stack, inventory, this);
    }

    @Override
    public String getInventoryName() {
        return "emcItemRecharger";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return InventoryHelper.isUseableByPlayer(player, xCoord, yCoord, zCoord);
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return stack != null && stack.getItem() instanceof IItemCharge;
    }

    public byte getTier() {
        return markUpgrade;
    }

    @Override
    public void updateEntity() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

}
