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

public class Abilities extends CommandBase
{
	//public static HashMap<String, ArrayList<String>> abilityCommandQueue = new HashMap<String, ArrayList<String>>();
	public static ArrayList<String> users = new ArrayList<String>();
	
	public String getCommandName() 
	{
		return "ability";
	}

	public String getCommandUsage(ICommandSender sender) 
	{
		return "/ability - checks the ability";
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
				PixelChecker.instance.abilityCommandQueue.put(sender.getCommandSenderName(), users);
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("right-click a pixelmon");
				chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.AQUA);
				sender.addChatMessage(chatcomponenttranslation);
				//PixelChecker.PIPELINE.sendTo(new MouseOverPacketAbility(), player);
				/*MovingObjectPosition pos = Minecraft.getMinecraft().objectMouseOver;
				if (pos != null) 
				{
					if (pos.entityHit != null) 
					{
						if(pos.entityHit instanceof EntityPixelmon)
						{
							EntityPixelmon p = (EntityPixelmon) pos.entityHit;
							//NBTTagCompound nbt = p.getEntityData();
							String message = p.getName() + ": " + p.getAbility().getName();
							ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(message);
							chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
							sender.addChatMessage(chatcomponenttranslation);
							return;
						}
						//packet = new EntityRequestPacket(pos.entityHit.getEntityId());
					}
					else
					{
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(getCommandUsage(sender));
						chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.RED);
						sender.addChatMessage(chatcomponenttranslation);
						return;
					}
				}*/
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