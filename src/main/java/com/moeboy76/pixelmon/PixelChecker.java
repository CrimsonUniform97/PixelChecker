package com.moeboy76.pixelmon;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import com.moeboy76.pixelmon.commands.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid=PixelChecker.MODID, name=PixelChecker.NAME, version=PixelChecker.VERSION, dependencies="required-after:pixelmon", acceptableRemoteVersions="*", canBeDeactivated=true)
public class PixelChecker
{
	public static final String MODID = "pixelchecker";
	public static final String NAME = "PixelChecker";
	public static final String VERSION = "1.3.0";
	
	public HashMap<String, ArrayList<String>> abilityCommandQueue = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ArrayList<String>> natureCommandQueue = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ArrayList<String>> ivsCommandQueue = new HashMap<String, ArrayList<String>>();
	public HashMap<String, ArrayList<String>> moveCommandQueue = new HashMap<String, ArrayList<String>>();
	
	@Instance(MODID)
	public static PixelChecker instance;
		
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		AddMeta(event, VERSION);
		
		instance = this;
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new PixelmonInteractEvent());
	}
	
	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		if ((MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager)) 
		{
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new Abilities());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new IVs());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new Moves());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new Natures());
		}
	}
	
	private void AddMeta(FMLPreInitializationEvent event, String version)
	{
		ModMetadata m = event.getModMetadata();
		m.autogenerated = false;
		m.modId = MODID;
		m.version = version;
		m.name = NAME;
		m.url = "";
		m.updateUrl = "";
		m.description = "Nature, Moves and Ability checker, just command and click!";
		m.authorList.add("MoeBoy76");
		m.credits = "Rhonim for some ideas on how to get it all client-side =3";
	}
}
