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
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class HomeCommand implements ICommand, ICommandSender
{
  private List aliases;
  public String PLAYER;
  public HomeCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("home");
  }

  @Override
  public String getCommandName()
  {
    return "Home";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "/Home {Home Name}";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] astring)
  {
	EntityPlayer player;
      
    if(icommandsender instanceof EntityPlayer){
            player = (EntityPlayer)icommandsender;
    }
    else {
    	ChatMessageComponent chat = new ChatMessageComponent();
        chat.addText("Player Only Command");
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(chat);
        return;
    }
    //player.posY = 100;
    player.motionY = 10;
    PLAYER = player.getEntityName();
    read();
    ChatMessageComponent chat = new ChatMessageComponent();
    chat.addText("");
    
    MinecraftServer minecraftserver = MinecraftServer.getServer();

    /*if (minecraftserver != null)
    {
        ICommandManager icommandmanager = minecraftserver.getCommandManager();
        icommandmanager.executeCommand(icommandsender, "tp" + icommandsender.getCommandSenderName());
        //Needs to be finished, needs way to get the coordinates to teleport to
    }
   */
    
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
  
  EntityPlayer player;
  public final String saveFileName = PLAYER;
  public final String getSaveFileName() {
	  return saveFileName + "_Home" + ".txt";
  }
  
  public final String getFullSavePath() {
		return this.getSaveFilePath() + this.getSaveFileName();
  }
  
  private final void read() {
		String name = this.getSaveFileName();
		try {
			BufferedReader p = new BufferedReader(new InputStreamReader(new FileInputStream(this.getFullSavePath())));
			String line = "";
			while (line != null) {
				line = p.readLine();
				if (line != null) {
					line = line.substring(0, line.length()-1);
				}
			}
			p.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
  }
		
}
