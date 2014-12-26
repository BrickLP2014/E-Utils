package eutils.common.resources;

import core.common.resources.CoreResources;
import moze_intel.projecte.PECore;

/**
 * Created by Master801 on 12/26/2014 at 12:32 PM.
 * @author Master801
 */
public final class EResources {

    public static final String E_UTILS_MOD_ID = "E-Utils";
    public static final String E_UTILS_NAME = "E-Utils";
    public static final String E_UTILS_VERSION = "@VERSION@";
    public static final String E_UTILS_DEPENDENCIES = "required-after:" + CoreResources.CORE_MODID + " required-after: " + PECore.MODID;
    public static final String E_UTILS_PROXY_SERVER = "eutils.proxies.EClientProxy";
    public static final String E_UTILS_PROXY_CLIENT = "eutils.proxies.EServerProxy";

}
