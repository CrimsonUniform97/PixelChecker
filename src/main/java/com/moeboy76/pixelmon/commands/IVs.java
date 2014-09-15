package com.moeboy76.pixelmon.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.moeboy76.pixelmon.PixelChecker;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class IVs extends CommandBase
{
	//public static HashMap<String, ArrayList<String>> ivsCommandQueue = new HashMap<String, ArrayList<String>>();
	public static ArrayList<String> users = new ArrayList<String>();
	
	public String getCommandName() 
	{
		return "getivs";
	}

	public String getCommandUsage(ICommandSender sender) 
	{
		return "/getivs - checks the IVs";
	}

	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	public void processCommand(ICommandSender sender, String[] args) 
	{
		if (sender instanceof EntityPlayerMP) 
		{
			EntityPlayerMP player = (EntityPlayerMP) sender;
			if(args.length == 0)
			{
				PixelChecker.instance.ivsCommandQueue.put(sender.getCommandSenderName(), users);
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("right-click a pixelmon");
				chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.AQUA);
				sender.addChatMessage(chatcomponenttranslation);
			}
			else
			{
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(getCommandUsage(sender));
				chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.RED);
				sender.addChatMessage(chatcomponenttranslation);
			}
		}
		else
		{
			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Only players can issue this command");
			chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.RED);
			sender.addChatMessage(chatcomponenttranslation);
		}
	}

	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
	}
}
