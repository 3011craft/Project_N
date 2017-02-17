package com.project_n;

import com.project_n.ModInformation.ModInfo;
import com.project_n.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.Reference;

/**
 * Created by liam on 31/01/2017.
 */

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION)
public class Project_N_Main
{
    public static final String modid = ModInfo.MODID;
    public static final String version = ModInfo.VERSION;

    //Proxy:
    public static final Logger logger = LogManager.getLogger("Project N");
    @SidedProxy(clientSide = "com.project_n.client.ClientProxy", serverSide = "com.project_n.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
