/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.server;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.DimensionManager;

public class SetHomeCommand implements ICommand
{
  private List aliases;
  EntityPlayer player;
  private String PLAYER;
  private String HOME_NAME;
  private double PLAYER_X;
  private double PLAYER_Y;
  private double PLAYER_Z;
  private int PLAYER_DIM;
  private boolean correctSyntax = true;
  
  public SetHomeCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("sethome");
  }

  @Override
  public String getCommandName()
  {
    return "sethome";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "/sethome [Home Name]";
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
            PLAYER_X = player.posX;
            PLAYER_Y = player.posY;
            PLAYER_Z = player.posZ;
            PLAYER_DIM = player.dimension;	
            HOME_NAME = stringArray[0];
            
           
            ChatMessageComponent chat = new ChatMessageComponent();
            chat.addText("Home Set");
            player.sendChatToPlayer(chat);
            
            try {
        		save();
        	} 
            catch (Throwable e) {
        		e.printStackTrace();
        	}  
	    }
		else if(correctSyntax == false) {
			ChatMessageComponent chat = new ChatMessageComponent();
	    	chat.addText("Incorrect Syntax, You Need A Name For The Home");
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
  public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
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
  
  private final void save() throws Throwable {
	  File dir = new File(this.getSaveFilePath());
	  if (!dir.exists()) {
			dir.mkdirs();
		}
	  File file = new File(this.getFullSavePath());
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		PrintWriter printWriter = new PrintWriter(file);
			
			printWriter.append(PLAYER_X + "");
			printWriter.println();
			printWriter.append(PLAYER_Y + "");
			printWriter.println();
			printWriter.append(PLAYER_Z + "");
			printWriter.println();
			printWriter.append(PLAYER_DIM + "");
			printWriter.close();
		}
  }