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
        //Discharge slots
        for (int inputSlotRow = 0; inputSlotRow < 4; inputSlotRow++) {
            for(int inputSlotColumn = 0; inputSlotColumn < 2; inputSlotColumn++) {
                addSlotToContainer(new Slot(inventory, inputSlotRow * inputSlotColumn, (inputSlotRow * 18) + 5, (inputSlotColumn * 18) + 30));
            }
        }
        //Charge slots
        for (int inputSlotRow = 0; inputSlotRow < 4; inputSlotRow++) {
            for(int inputSlotColumn = 0; inputSlotColumn < 2; inputSlotColumn++) {
                addSlotToContainer(new Slot(inventory, (inputSlotRow * inputSlotColumn) + 8, (inputSlotRow * 18) + 101, (inputSlotColumn * 18) + 30));
            }
        }
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

}
