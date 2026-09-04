package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.minescape.network.MinescapeModVariables;

public class TilesDefaultsProcedure {
	public static void execute(LevelAccessor world) {
		if (!MinescapeModVariables.WorldVariables.get(world).TileIds.isEmpty() == false) {
			MinescapeModVariables.WorldVariables.get(world).TileIds.add("square");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(5.0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add(new Vec3(-1.5, -1, 0.5));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add(new Vec3(-1.5, -1, -3.5));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add(new Vec3(3, 2, 3));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("seeded default tile registry: square").withColor(0x009900), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("tile registry not empty (" + new java.text.DecimalFormat("0").format(MinescapeModVariables.WorldVariables.get(world).TileIds.size()) + " entries)")).withColor(0xff8800), false);
			}
		}
	}
}
