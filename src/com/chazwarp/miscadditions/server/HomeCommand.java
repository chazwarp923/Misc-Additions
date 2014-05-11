/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Loader;

public class HomeCommand implements ICommand, ICommandSender
{
  private List aliases;
  private String PLAYER;
  private String HOME_NAME;
  private double[] lines = new double[4];
  private boolean correctSyntax = true;
  private EntityPlayer player;
  
  public HomeCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("home");
  }

  @Override
  public String getCommandName()
  {
    return "home";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "/home [Home Name]";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] stringArray)
  {

		//if(stringArray.length == 0) {
		//	correctSyntax = false;
		//}
		
		if(icommandsender instanceof EntityPlayer) {
	        player = (EntityPlayer)icommandsender;
	        
		    if(correctSyntax == true){
	            PLAYER = icommandsender.getCommandSenderName();	            
	            HOME_NAME = stringArray[0];
	           
	            MinecraftServer minecraftserver = MinecraftServer.getServer();

	            if (minecraftserver != null)
	            {
	                //ICommandManager icommandmanager = minecraftserver.getCommandManager();
	                read();
	                //icommandmanager.executeCommand(icommandsender, "tp" + PLAYER + lines[0] + lines[1] + lines[2]);
	                player.setPositionAndUpdate(lines[0], lines[1], lines[2]);              
	            } 
	            
	            ChatMessageComponent chat = new ChatMessageComponent();
	            chat.addText("You Went Home");
	            player.sendChatToPlayer(chat);	             
		    }
		    else if(correctSyntax == false) {
				ChatMessageComponent chat = new ChatMessageComponent();
		    	chat.addText("Incorrect Syntax, You to Specify a Home");
		    	player.sendChatToPlayer(chat);
			}
		}
		else if(!(icommandsender instanceof EntityPlayer)) {
		     ChatMessageComponent chat = new ChatMessageComponent();
		     chat.addText("Player Only Command");
		     MinecraftServer.getServer().getConfigurationManager().sendChatMsg(chat);
		}      
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex(String[] astring, int i)
  {
    return false;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
  
  //Begin ICommand Sender Methods
  @Override
  public String getCommandSenderName() {
  	return "Home Command";
  }

  @Override
  public void sendChatToPlayer(ChatMessageComponent chatmessagecomponent) {
  	
  }

  @Override
  public boolean canCommandSenderUseCommand(int i, String s) {
  	return true;
  }

  @Override
  public ChunkCoordinates getPlayerCoordinates() {
	  if(player != null) {
		  return player.playerLocation;
	  }
	  else {
		  return null; 
	  }
  }

  @Override
  public World getEntityWorld() {
	  if(player != null) {
		  return player.getEntityWorld(); 
	  }
	  else {
		  return null;
	  }
  }
  
 //From here down is all code originally written by Reika and borrowed from DragonAPI
 // https://github.com/ReikaKalseki/DragonAPI
  
  public final String getSaveFilePath() {
		File save = DimensionManager.getCurrentSaveRootDirectory();
		return save.getPath().substring(2)+"\\MiscAdditions\\Homes\\" + PLAYER + "\\";
  }
  
  public final String getSaveFileName() {
	  return HOME_NAME + ".txt";
  }
  
  public final String getFullSavePath() {
		return this.getSaveFilePath() + this.getSaveFileName();
  }
  
  private final void read() {
		try {
			BufferedReader p = new BufferedReader(new InputStreamReader(new FileInputStream(this.getFullSavePath())));
			for(int i=0; i<4; i++){
				lines[i] = Double.parseDouble(p.readLine());
			}
			p.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
  }
		
}
