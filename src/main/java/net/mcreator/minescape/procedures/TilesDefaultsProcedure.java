package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.minescape.network.MinescapeModVariables;

public class TilesDefaultsProcedure {
	public static void execute(LevelAccessor world) {
		if (MinescapeModVariables.WorldVariables.get(world).TileIds.isEmpty()) {
			MinescapeModVariables.WorldVariables.get(world).TileIds.add("square");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(5);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add((new Vec3(5, 2, 5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add((new Vec3(2.5, 0, 4.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add((new Vec3(2.5, 0, 0.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileLefts.add((new Vec3(0.5, 0, 2.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileRights.add((new Vec3(4.5, 0, 2.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.add(2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.add(0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.add(1);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.add(3);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileIds.add("testtile1");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(3);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add((new Vec3(5, 1, 9)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add((new Vec3(2.5, 0, 8.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add((new Vec3(2.5, 0, 0.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileLefts.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileRights.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.add(2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.add(0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("seeded default tile registry (square + testtile1)").withColor(0x009900), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(
						Component.literal(("tile registry not empty (" + new java.text.DecimalFormat("0").format(MinescapeModVariables.WorldVariables.get(world).TileIds.size()) + " entries)"))
								.withColor(0xff8800), false);
			}
		}
	}
}