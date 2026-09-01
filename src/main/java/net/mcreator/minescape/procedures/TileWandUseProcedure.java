package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.minescape.network.MinescapeModVariables;

public class TileWandUseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown()) {
			MinescapeModVariables.WorldVariables.get(world).TilePos2 = new Vec3(x, y, z);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("pos2 set to (" + x + ", " + y + ", " + z + ")")).withColor(0x339900), false);
			}
		} else {
			MinescapeModVariables.WorldVariables.get(world).TilePos1 = new Vec3(x, y, z);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("pos1 set to (" + x + ", " + y + ", " + z + ")")).withColor(0x339900), false);
			}
		}
	}
}