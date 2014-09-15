package com.moeboy76.pixelmon;

import com.moeboy76.pixelmon.commands.*;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.ComingSoon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Moveset;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class PixelmonInteractEvent 
{
	@SubscribeEvent
	public void onInteract(EntityInteractEvent event)
	{
		if(event.entityPlayer instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
			String username = player.getDisplayName();
			if(PixelChecker.instance.abilityCommandQueue.containsKey(username))
			{
				if(event.target instanceof EntityPixelmon)
				{
					EntityPixelmon p = (EntityPixelmon) event.target;
					String message = p.getName() + ": ";
					if(p.getAbility() instanceof ComingSoon)
					{
						ComingSoon abil = (ComingSoon) p.getAbility();
						message += "Coming Soon - " + abil.getTrueAbility();
					}
					else
					{
						message += p.getAbility().getName();
					}
					ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					PixelChecker.instance.abilityCommandQueue.remove(player.getDisplayName());
				}
			}
			if(PixelChecker.instance.natureCommandQueue.containsKey(username))
			{
				if(event.target instanceof EntityPixelmon)
				{
					EntityPixelmon p = (EntityPixelmon) event.target;
					String message = p.getName() + ": " + p.getNature();
					ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					PixelChecker.instance.natureCommandQueue.remove(player.getDisplayName());
				}
			}
			if(PixelChecker.instance.ivsCommandQueue.containsKey(username))
			{
				if(event.target instanceof EntityPixelmon)
				{
					EntityPixelmon p = (EntityPixelmon) event.target;
					String message = p.getName() + "'s IVs:";
					ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					NBTTagCompound nbtPoke = new NBTTagCompound();
					p.writeEntityToNBT(nbtPoke);
					message = "HP " + nbtPoke.getInteger("IVHP");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					message = "Attack " + nbtPoke.getInteger("IVAttack");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					message = "Defense " + nbtPoke.getInteger("IVDefence");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					message = "Special Attack " + nbtPoke.getInteger("IVSpAtt");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					message = "Special Defence " + nbtPoke.getInteger("IVSpDef");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					message = "Speed " + nbtPoke.getInteger("IVSpeed");
					chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					PixelChecker.instance.ivsCommandQueue.remove(player.getDisplayName());
				}
			}
			if(PixelChecker.instance.moveCommandQueue.containsKey(username))
			{
				if(event.target instanceof EntityPixelmon)
				{
					EntityPixelmon p = (EntityPixelmon) event.target;
					Moveset moves = p.getMoveset();
					String message = p.getName() + "'s Moves:";
					ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(message);
					chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GREEN);
					player.addChatMessage(chatcomponenttranslation);
					for(Attack a : moves.attacks)
					{
						if(a != null)
						{
							message = a.baseAttack.getLocalizedName();
							chatcomponenttranslation = new ChatComponentTranslation(message);
							chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.AQUA);
							player.addChatMessage(chatcomponenttranslation);
						}
						
					}
					PixelChecker.instance.moveCommandQueue.remove(player.getDisplayName());
				}
			}
		}
	}
}
