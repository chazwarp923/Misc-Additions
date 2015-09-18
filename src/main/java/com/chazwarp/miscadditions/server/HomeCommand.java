/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.DimensionManager;

public class HomeCommand implements ICommand {
	private List<String> aliases;
	private String PLAYER;
	private String HOME_NAME;
	private double[] lines = new double[4];
	private boolean correctSyntax = true;
	private EntityPlayer player;

	public HomeCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("home");
	}

	@Override
	public String getCommandName() {
		return "home";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/home [Home Name]";
	}

	@Override
	public List<String> getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender iCommandSender, String[] stringArray) {
		
        if (!(stringArray.length > 0)) {
			correctSyntax = false;
		}

		if (iCommandSender instanceof EntityPlayer) {
			player = (EntityPlayer) iCommandSender;

			if (correctSyntax == true) {
				PLAYER = iCommandSender.getCommandSenderName();
				HOME_NAME = stringArray[0];

				MinecraftServer minecraftserver = MinecraftServer.getServer();

				if (minecraftserver != null) {
					try {
						read();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					player.setPositionAndUpdate(lines[0], lines[1], lines[2]);
				}

				player.addChatMessage(new ChatComponentText("Welcome Home"));
			} 
			else if (correctSyntax == false) {
				player.addChatMessage(new ChatComponentText("Incorrect Syntax, Please Specify a Home"));
			}
		} 
		else if (!(iCommandSender instanceof EntityPlayer)) {
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("Player Only Command"));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender icommandsender, String[] stringArray) {
		PLAYER = icommandsender.getCommandSenderName();

		File[] fileArray = listFilesForFolder(new File(getPlayerDir()));
		List<String> stringArrayList = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			stringArrayList.add(fileArray[i].getName());
		}

		return stringArray.length == 1 ? stringArrayList : null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	public File[] listFilesForFolder(File folder) {
		return folder.listFiles();
	}

	public final String getPlayerDir() {
		File save = DimensionManager.getCurrentSaveRootDirectory();
		return save.getPath().substring(2) + "/MiscAdditions/Homes/" + PLAYER + "/";
	}

	public final String getFullPathToHome() {
		return this.getPlayerDir() + HOME_NAME;
	}

	private final void read() throws NumberFormatException, IOException {
		BufferedReader p = new BufferedReader(new InputStreamReader(new FileInputStream(this.getFullPathToHome())));
		for (int i = 0; i < 4; i++) {
			lines[i] = Double.parseDouble(p.readLine());
		}
		p.close();
	}
}
