package eutils.common.resources;

import core.api.metadata.IMetadata;
import cpw.mods.fml.common.ModContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master801 on 12/27/2014 at 12:56 AM.
 * @author Master801
 */
public final class EUtilsModMetadata implements IMetadata {

    public static final IMetadata INSTANCE = new EUtilsModMetadata();

    @Override
    public List<String> getAuthorList() {
        final List<String> list = new ArrayList<String>();
        list.add("BrickLP2014");
        list.add("Master801");
        return list;
    }

    @Override
    public boolean isAutoGenerated() {
        return false;
    }

    @Override
    public List<ModContainer> getChildMods() {
        return null;
    }

    @Override
    public List<String> getCredits() {
        final List<String> list = new ArrayList<String>();
        list.add("Textures by: BrickLP2014");
        list.add("Programmed by: Master801");
        return list;
    }

    @Override
    public String getDescription() {
        return "E-Utils; A (still in development) addon for Project:E";
    }

    @Override
    public String getLogo() {
        return "/E-Utils_Logo.png";
    }

    @Override
    public String getModID() {
        return EResources.E_UTILS_MOD_ID;
    }

    @Override
    public String getModName() {
        return EResources.E_UTILS_NAME;
    }

    @Override
    public String getParentMod() {
        return null;
    }

    @Override
    public String[] getScreenShots() {
        return new String[0];
    }

    @Override
    public String getUpdateURL() {
        return null;
    }

    @Override
    public String getURL() {
        return "https://github.com/BrickLP2014/E-Utils";
    }

    @Override
    public String getModVersion() {
        return EResources.E_UTILS_VERSION;
    }

}
