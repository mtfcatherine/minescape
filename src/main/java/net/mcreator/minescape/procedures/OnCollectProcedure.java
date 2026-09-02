package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.minescape.network.MinescapeModVariables;
import net.mcreator.minescape.entity.NullGiftEntity;
import net.mcreator.minescape.entity.GoldGiftEntity;
import net.mcreator.minescape.MinescapeMod;

public class OnCollectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!entity.level().isClientSide())
			entity.discard();
		if (entity instanceof GoldGiftEntity) {
			MinescapeMod.LOGGER.info("Gold Gift collected");
			MinescapeModVariables.WorldVariables.get(world).GoldGifts = MinescapeModVariables.WorldVariables.get(world).GoldGifts + MinescapeModVariables.WorldVariables.get(world).GoldGiftMultiplier * 1;
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:goldcollect")), SoundSource.MASTER, (float) 0.2, (float) Mth.nextDouble(RandomSource.create(), 0.99, 1.07));
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:goldcollect")), SoundSource.MASTER, (float) 0.2, (float) Mth.nextDouble(RandomSource.create(), 0.99, 1.07), false);
				}
			}
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("GoldGifts: " + MinescapeModVariables.WorldVariables.get(world).GoldGifts)), false);
			}
		} else if (entity instanceof NullGiftEntity) {
			MinescapeMod.LOGGER.info("Gift collected");
			MinescapeModVariables.WorldVariables.get(world).GiftsCollected = MinescapeModVariables.WorldVariables.get(world).GiftsCollected + 1;
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:collect")), SoundSource.MASTER, (float) 0.2, (float) Mth.nextDouble(RandomSource.create(), 0.97, 1.05));
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("minescape:collect")), SoundSource.MASTER, (float) 0.2, (float) Mth.nextDouble(RandomSource.create(), 0.97, 1.05), false);
				}
			}
		} else {
			MinescapeMod.LOGGER.info("uh oh");
		}
	}
}