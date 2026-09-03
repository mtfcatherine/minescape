package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.minescape.network.MinescapeModVariables;
import net.mcreator.minescape.init.MinescapeModEntities;
import net.mcreator.minescape.entity.NodeGiftEntity;

import java.util.Comparator;

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
		Vec3 sizeV = Vec3.ZERO;
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
			for (int _i1 = 0; _i1 < (int) count; _i1++) {
				placed = false;
				attempts = 0;
				while (attempts < 25 && !placed) {
					attempts = attempts + 1;
					totalWeight = 0;
					i = 0;
					while (i < MinescapeModVariables.WorldVariables.get(world).TileWeights.size()) {
						totalWeight = totalWeight + (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub4 ? _doub4 : 0.0D);
						i = i + 1;
					}
					roll = Mth.nextDouble(RandomSource.create(), 0.001, totalWeight);
					i = 0;
					idx = 0;
					while (i < MinescapeModVariables.WorldVariables.get(world).TileWeights.size()) {
						if (roll <= (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub7 ? _doub7 : 0.0D)) {
							idx = i;
							i = MinescapeModVariables.WorldVariables.get(world).TileWeights.size();
						} else {
							roll = roll - (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub9 ? _doub9 : 0.0D);
							i = i + 1;
						}
					}
					if (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3) {
						tileId = MinescapeModVariables.WorldVariables.get(world).TileIds.get((int) idx) instanceof String _str11 ? _str11 : "";
						backOff = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3 _vector12 ? _vector12 : Vec3.ZERO;
						ox = cursorX - backOff.x();
						oy = cursorY - backOff.y();
						oz = cursorZ - backOff.z();
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("place template minescape:" + tileId + " " + new java.text.DecimalFormat("0").format(ox) + " " + new java.text.DecimalFormat("0").format(oy) + " " + new java.text.DecimalFormat("0").format(oz)));
						placed = true;
						if (world instanceof ServerLevel _level) {
							_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("placed tile '" + tileId + "'")).withColor(0x44cc44), false);
						}
						sizeV = MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) idx) instanceof Vec3 _vector18 ? _vector18 : Vec3.ZERO;
						{
							final Vec3 _center = new Vec3((ox + sizeV.x() / 2), (oy + sizeV.y() / 2), (oz + sizeV.z() / 2));
							for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((sizeV.x() + sizeV.z()) / 2d), e -> true).stream()
									.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
								if (entityiterator instanceof NodeGiftEntity) {
									MinescapeModVariables.MapVariables.get(world).GeneratedGifts = new Object() {
										public double change(Object _obj) {
											if (_obj instanceof Integer _i)
												return _i + 1;
											if (_obj instanceof Long _l)
												return _l + 1;
											if (_obj instanceof Float _f)
												return _f + 1.0f;
											if (_obj instanceof Double _d)
												return _d + 1.0d;
											if (_obj instanceof Number _n)
												return _n.doubleValue() + 1;
											return 0;
										}
									}.change(count);
									MinescapeModVariables.MapVariables.get(world).markSyncDirty();
									if (world instanceof ServerLevel _level) {
										Entity entityToSpawn = MinescapeModEntities.NULL_GIFT.get().spawn(_level, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), EntitySpawnReason.MOB_SUMMONED);
										if (entityToSpawn != null) {
											entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
										}
									}
									MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions.add((new Vec3((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()))));
									MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
									if (!entityiterator.level().isClientSide())
										entityiterator.discard();
								}
							}
						}
						if (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3) {
							frontOff = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3 _vector38 ? _vector38 : Vec3.ZERO;
							cursorX = ox + frontOff.x();
							cursorY = oy + frontOff.y();
							cursorZ = oz + frontOff.z();
						} else {
							if (world instanceof ServerLevel _level) {
								_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("tile '" + tileId + "' has no front node - chain stalls here")).withColor(0xff8800), false);
							}
						}
					}
				}
				if (!placed) {
					if (world instanceof ServerLevel _level) {
						_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("gave up on a tile after 25 tries (missing back nodes?) - chain stops").withColor(0xff8800), false);
					}
					break;
				}
			}
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("done - generated up to " + new java.text.DecimalFormat("0").format(count) + " tiles")).withColor(0x009900), false);
			}
		}
	}
}