/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
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
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if(!(args.length > 0)) {
			correctSyntax = false;
		}

		if(sender instanceof EntityPlayer) {
			player = (EntityPlayer)sender;

			if(correctSyntax == true) {
				PLAYER = sender.getCommandSenderEntity().getName();
				PLAYER_X = player.posX;
				PLAYER_Y = player.posY;
				PLAYER_Z = player.posZ;
				PLAYER_DIM = player.dimension;
				HOME_NAME = args[0];

				player.addChatMessage(new TextComponentString("Home Set"));


				try {
					save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			else if (correctSyntax == false) {
				player.addChatMessage(new TextComponentString("Incorrect Syntax, please specify a name"));
			}
		} 
		else if (!(sender instanceof EntityPlayer)) {
			server.addChatMessage(new TextComponentString("Player Only Command"));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	@Override
	public int compareTo(ICommand arg0) {
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