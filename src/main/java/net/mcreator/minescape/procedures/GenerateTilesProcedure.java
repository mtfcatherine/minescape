package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.minescape.network.MinescapeModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class GenerateTilesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments) {
		double count = 0;
		double cursorX = 0;
		double cursorY = 0;
		double cursorZ = 0;
		double attempts = 0;
		double i = 0;
		double totalWeight = 0;
		double roll = 0;
		double idx = 0;
		double ox = 0;
		double oy = 0;
		double oz = 0;
		String tileId = "";
		Vec3 backOff = Vec3.ZERO;
		Vec3 frontOff = Vec3.ZERO;
		boolean placed = false;
		count = DoubleArgumentType.getDouble(arguments, "count");
		cursorX = Math.floor(x);
		cursorY = Math.floor(y);
		cursorZ = Math.floor(z);
		if (MinescapeModVariables.WorldVariables.get(world).TileIds.isEmpty()) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("no tiles saved - use /save_tile first").withColor(0xff3333), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("done - generated up to " + new java.text.DecimalFormat("0").format(count) + " tiles")).withColor(0x009900), false);
			}
		}
	}
}