package eutils.tileentity;

import core.api.client.gui.IGuiHelper;
import core.api.tileentity.IActivate;
import core.helpers.GuiHelper;
import core.helpers.InventoryHelper;
import core.helpers.PlayerHelper;
import core.helpers.RotationHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.EUtils;
import eutils.client.gui.GuiContainerEMCItemRecharger;
import eutils.common.inventory.ContainerEMCItemRecharger;
import moze_intel.projecte.api.IItemCharge;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.items.ItemPE;
import moze_intel.projecte.gameObjs.tiles.TileEmc;
import moze_intel.projecte.utils.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Master801 on 12/26/2014 at 9:32 PM.
 * @author Master801
 */
public class TileEntityEMCItemRecharger extends TileEmc implements IActivate, IGuiHelper, ISidedInventory {//W.I.P.!

    public static final int[] ALLOWED_SLOTS_FOR_UNCHARGED_ITEMS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, };
    public static final int[] ALLOWED_SLOTS_FOR_CHARGED_ITEMS = new int[] { 8, 9, 10, 11, 12, 13, 14, 15, };

    private final ItemStack[] inventory = new ItemStack[19];
    private final byte markUpgrade;//The tier this recharger is.
    private final int emcTransfer;//How much emc this recharger should transfer to the item.

    public TileEntityEMCItemRecharger(int storage, int emcTransfer, byte markUpgrade) {
        super(storage);
        this.markUpgrade = markUpgrade;
        this.emcTransfer = emcTransfer;
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
//        return stack != null && stack.getItem() instanceof IItemCharge;//FIXME There's no flexible api that allows me to tell if the item has a max emc value or not.
        return stack != null && stack.getItem().equals(ObjHandler.kleinStars);
    }

    public byte getTier() {
        return markUpgrade;
    }

    @Override
    public void updateEntity() {
        checkAndSetEMCLevels();
        moveUnchargedItemsToChargeSlots();
        chargeItemsInChargeSlots();
        moveChargedItemsFromChargeSlots();
        checkAndSetEMCLevels();
    }

    protected void moveUnchargedItemsToChargeSlots() {
        for(int i = 0; i < 8; i++) {
            checkAndMoveUnchargedItemsToChargeSlots(i);
        }
    }

    private void checkAndMoveUnchargedItemsToChargeSlots(int slot) {
        ItemStack stack = inventory[slot];
//        if (stack != null && stack.getItem() instanceof IItemCharge) {//FIXME There's no flexible api that allows me to tell if the item has a max emc value or not.
        if (stack != null && stack.getItem().equals(ObjHandler.kleinStars)) {
            IItemCharge chargeItem = (IItemCharge)stack.getItem();
            if (chargeItem.getCharge(stack) < Utils.getKleinStarMaxEmc(stack)) {
                int availableChargeSlot = -1;
                for(int i = 0; i < (getTier() + 1); i++) {
                    i += 16;
                    if (inventory[i] == null) {
                        availableChargeSlot = i;
                        break;
                    }
                }
                if (availableChargeSlot < 16) {
                    return;
                }
                ItemStack bufferStack = stack.copy();
                inventory[slot] = null;
                inventory[availableChargeSlot] = bufferStack;
            }
        }
    }

    protected void moveChargedItemsFromChargeSlots() {
        for(int i = 0; i < 3; i++) {
            i += 16;
            checkAndMoveChargingItemToCharged(i);
        }
    }

    private void checkAndMoveChargingItemToCharged(int slot) {
        ItemStack stack = inventory[slot];
//        if (stack != null && stack.getItem() instanceof IItemCharge) {//FIXME There's no flexible api that allows me to tell if the item has a max emc value or not.
        if (stack != null && stack.getItem().equals(ObjHandler.kleinStars)) {
            IItemCharge chargeItem = (IItemCharge)stack.getItem();
            if (chargeItem.getCharge(stack) == Utils.getKleinStarMaxEmc(stack)) {
                int availableChargedSlot = -1;
                for(int i = 0; i < 8; i++) {
                    i += 8;
                    if (inventory[i] == null) {
                        availableChargedSlot = i;
                        break;
                    }
                }
                if (availableChargedSlot < 8) {
                    return;
                }
                ItemStack bufferStack = stack.copy();
                inventory[slot] = null;
                inventory[availableChargedSlot] = bufferStack;
            }
        }
    }

    protected void chargeItemsInChargeSlots() {
        switch(getTier()) {
            case 0:
                chargeAndMoveItems(16);
                break;
            case 1:
                chargeAndMoveItems(16);
                chargeAndMoveItems(17);
                break;
            case 2:
                chargeAndMoveItems(16);
                chargeAndMoveItems(17);
                chargeAndMoveItems(18);
                break;
        }
    }

    private void chargeAndMoveItems(int slot) {
        ItemStack stack = inventory[slot];
        if (stack == null) {
            return;
        }
//        if (stack.getItem() instanceof IItemCharge) {//FIXME No flexible api for max emc.
        if (stack.getItem().equals(ObjHandler.kleinStars)) {
            IItemCharge itemCharge = (IItemCharge)stack.getItem();
            int maxEMC = Utils.getKleinStarMaxEmc(stack);
            if (itemCharge.getCharge(stack) == maxEMC) {
                checkAndMoveChargingItemToCharged(slot);
                return;
            }
            ItemPE.addEmc(stack, emcTransfer);
            removeEmc(emcTransfer);
            if (itemCharge.getCharge(stack) == maxEMC) {
                checkAndMoveChargingItemToCharged(slot);
            }
        }
    }

    protected void checkAndSetEMCLevels() {
        if (getStoredEmc() > getMaxEmc()) {
            setEmc(getMaxEmc());
        } else if (getStoredEmc() < 0.0D) {
            setEmc(0.0D);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        InventoryHelper.readFromNBT(nbt, inventory);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        InventoryHelper.writeToNBT(nbt, inventory);
    }

    @Override
    public Container getServerGui(InventoryPlayer inventory) {
        return new ContainerEMCItemRecharger(inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiContainer getClientGui(InventoryPlayer inventory) {
        return new GuiContainerEMCItemRecharger(new ContainerEMCItemRecharger(inventory, this));
    }

    @Override
    public boolean isItem() {
        return false;
    }

    @Override
    public boolean doesHaveGui() {
        return true;
    }

    @Override
    public boolean onActivated(EntityPlayer player, ForgeDirection side) {
        return PlayerHelper.isPlayerNotSneaking(player) && GuiHelper.openGui(EUtils.instance, player, this);
    }

}
