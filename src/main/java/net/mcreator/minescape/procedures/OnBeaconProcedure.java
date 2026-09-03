package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.minescape.network.MinescapeModVariables;

public class OnBeaconProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (!world.isClientSide()) {
			if (!(sourceentity instanceof Player _plr1 && _plr1.gameMode() == GameType.SPECTATOR)) {
				if (sourceentity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SPECTATOR);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:beaconenter")), SoundSource.PLAYERS, (float) 0.25, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:beaconenter")), SoundSource.PLAYERS, (float) 0.25, 1, false);
					}
				}
				MinescapeModVariables.WorldVariables.get(world).playersBeaconed = MinescapeModVariables.WorldVariables.get(world).playersBeaconed + 1;
				MinescapeModVariables.WorldVariables.get(world).playersAlive = MinescapeModVariables.WorldVariables.get(world).playersAlive - 1;
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			}
		}
	}
}