package us.ichun.mods.tabula.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.relauncher.Side;
import ichun.common.core.config.Config;
import ichun.common.core.config.IConfigUser;
import ichun.common.iChunUtil;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.ichun.mods.tabula.common.core.CommonProxy;

import java.util.EnumMap;

@Mod(modid = "Tabula", name = "Tabula",
        version = Tabula.version,
        dependencies = "required-after:iChunUtil@[" + iChunUtil.versionMC +".0.0,)",
        acceptableRemoteVersions = "[" + iChunUtil.versionMC +".0.0," + iChunUtil.versionMC + ".1.0)"
)
public class Tabula
        implements IConfigUser
{
    public static final String version = iChunUtil.versionMC + ".0.0";

    @Mod.Instance("Tabula")
    public static Tabula instance;

    @SidedProxy(clientSide = "us.ichun.mods.tabula.client.core.ClientProxy", serverSide = "us.ichun.mods.tabula.common.core.CommonProxy")
    public static CommonProxy proxy;

    public static EnumMap<Side, FMLEmbeddedChannel> channels;

    private static final Logger logger = LogManager.getLogger("Tabula");

    @Override
    public boolean onConfigChange(Config cfg, Property prop) { return true; }

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
    }

    public static void console(String s, boolean warning)
    {
        StringBuilder sb = new StringBuilder();
        logger.log(warning ? Level.WARN : Level.INFO, sb.append("[").append(version).append("] ").append(s).toString());
    }
}
