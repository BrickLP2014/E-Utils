package eutils.client.gui;

import core.client.gui.GuiContainerCoreBase;
import core.helpers.TextureHelper.TextureUsageInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eutils.common.inventory.ContainerEMCItemRecharger;
import eutils.common.resources.EResources;

/**
 * Created by Master801 on 12/27/2014 at 12:09 PM.
 * @author Master801
 */
@SideOnly(Side.CLIENT)
public class GuiContainerEMCItemRecharger extends GuiContainerCoreBase<ContainerEMCItemRecharger> {

    public GuiContainerEMCItemRecharger(ContainerEMCItemRecharger container) {
        super(container);
    }

    @Override
    protected TextureUsageInfo getTextureInfo() {
        return new TextureUsageInfo(true, EResources.E_UTILS_MOD_ID + ":" + "textures/gui/EMCItemRecharger.png");
    }

    @Override
    protected void drawBackgroundLayer(int adjustedX, int adjustedY) {
        switch(getContainer().getIInventory().getTier()) {
            case 0:
                drawTexturedModalRect(adjustedX + 79, adjustedY + 38, 176, 0, 18, 18);//Slot 1
                break;
            case 1:
                drawTexturedModalRect(adjustedX + 79, adjustedY + 38, 176, 0, 18, 18);//Slot 1
                drawTexturedModalRect(adjustedX + 79, adjustedY + 20, 176, 0, 18, 18);//Slot 2
                break;
            case 2:
                drawTexturedModalRect(adjustedX + 79, adjustedY + 38, 176, 0, 18, 18);//Slot 1
                drawTexturedModalRect(adjustedX + 79, adjustedY + 20, 176, 0, 18, 18);//Slot 2
                drawTexturedModalRect(adjustedX + 79, adjustedY + 56, 176, 0, 18, 18);//Slot 3
                break;
        }
    }

}
