package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.minescape.network.MinescapeModVariables;

public class ClearTilesProcedure {
	public static void execute(LevelAccessor world) {
		MinescapeModVariables.WorldVariables.get(world).TileIds.clear();
		MinescapeModVariables.WorldVariables.get(world).TileWeights.clear();
		MinescapeModVariables.WorldVariables.get(world).TileFronts.clear();
		MinescapeModVariables.WorldVariables.get(world).TileBacks.clear();
		MinescapeModVariables.WorldVariables.get(world).TileSizes.clear();
		MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions.clear();
		MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("tile registry cleared").withColor(0x009900), false);
		}
	}
}