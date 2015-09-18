/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.DimensionManager;

public class SetHomeCommand implements ICommand {
	private List<String> aliases;
	private EntityPlayer player;
	private String PLAYER;
	private String HOME_NAME;
	private double PLAYER_X;
	private double PLAYER_Y;
	private double PLAYER_Z;
	private int PLAYER_DIM;
	private boolean correctSyntax = true;

	public SetHomeCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("sethome");
	}

	@Override
	public String getCommandName() {
		return "sethome";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/sethome [Home Name]";
	}

	@Override
	public List<String> getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] stringArray) {

		if(!(stringArray.length > 0)) {
			correctSyntax = false;
		}

		if(icommandsender instanceof EntityPlayer) {
			player = (EntityPlayer)icommandsender;

			if(correctSyntax == true) {
				PLAYER = icommandsender.getCommandSenderName();
				PLAYER_X = player.posX;
				PLAYER_Y = player.posY;
				PLAYER_Z = player.posZ;
				PLAYER_DIM = player.dimension;
				HOME_NAME = stringArray[0];

				player.addChatMessage(new ChatComponentText("Home Set"));

				try {
					save();
				} 
				catch (Throwable e) {
					e.printStackTrace();
				}
			} 
			else if (correctSyntax == false) {
				player.addChatMessage(new ChatComponentText("Incorrect Syntax, please specify a name"));
			}
		} 
		else if (!(icommandsender instanceof EntityPlayer)) {
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("Player Only Command"));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	public final String getPlayerDir() {
		File save = DimensionManager.getCurrentSaveRootDirectory();
		return save.getPath().substring(2) + "\\MiscAdditions\\Homes\\" + PLAYER + "\\";
	}

	public final String getFullPathToHome() {
		return this.getPlayerDir() + HOME_NAME;
	}

	private final void save() throws IOException {
		File dir = new File(this.getPlayerDir());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(this.getFullPathToHome());
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