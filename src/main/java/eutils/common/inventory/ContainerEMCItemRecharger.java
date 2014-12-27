package eutils.common.inventory;

import core.common.inventory.container.ContainerCoreBase;
import eutils.tileentity.TileEntityEMCItemRecharger;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Master801 on 12/27/2014 at 12:09 PM.
 * @author Master801
 */
public class ContainerEMCItemRecharger extends ContainerCoreBase<TileEntityEMCItemRecharger> {

    public ContainerEMCItemRecharger(InventoryPlayer playerInventory, TileEntityEMCItemRecharger inventory) {
        super(playerInventory, inventory);
    }

    @Override
    protected void addSlots(IInventory inventory) {
        addDefaultSlotsToContainer(inventory, 0, 0);//Discharge slots
        addDefaultSlotsToContainer(inventory, 8, 96);//Charge slots
        switch(getIInventory().getTier()) {
            case 0:
                addSlotToContainer(new Slot(inventory, 16, 80, 39));//Charging Slot 1
                break;
            case 1:
                addSlotToContainer(new Slot(inventory, 16, 80, 39));//Charging Slot 1
                addSlotToContainer(new Slot(inventory, 17, 80, 21));//Charging Slot 2
                break;
            case 2:
                addSlotToContainer(new Slot(inventory, 16, 80, 39));//Charging Slot 1
                addSlotToContainer(new Slot(inventory, 17, 80, 21));//Charging Slot 2
                addSlotToContainer(new Slot(inventory, 18, 80, 57));//Charging Slot 3
                break;
        }
    }

    private void addDefaultSlotsToContainer(IInventory inventory, int startSlotNumber, int startingXPoint) {
        addSlotToContainer(new Slot(inventory, startSlotNumber, 5 + startingXPoint, 30));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 1, 22 + startingXPoint, 30));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 2, 41 + startingXPoint, 30));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 3, 59 + startingXPoint, 30));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 4, 5 + startingXPoint, 48));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 5, 22 + startingXPoint, 48));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 6, 41 + startingXPoint, 48));
        addSlotToContainer(new Slot(inventory, startSlotNumber + 7, 59 + startingXPoint, 48));
    }

}
