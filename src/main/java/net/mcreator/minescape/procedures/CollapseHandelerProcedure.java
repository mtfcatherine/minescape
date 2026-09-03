package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.minescape.network.MinescapeModVariables;
import net.mcreator.minescape.init.MinescapeModEntities;

public class CollapseHandelerProcedure {
	public static void execute(LevelAccessor world) {
		double i = 0;
		for (Object arraylistiterator : MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions) {
			i = new Object() {
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
			}.change(i);
			MinescapeModVariables.WorldVariables.get(world).GiftsCollected = 0;
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = MinescapeModEntities.BEACON.get().spawn(_level, new BlockPos(0, 0, 0), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = MinescapeModEntities.GOLD_GIFT.get().spawn(_level,
						BlockPos.containing((MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions.get((int) i) instanceof Vec3 _vector2 ? _vector2 : Vec3.ZERO).x(),
								(MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions.get((int) i) instanceof Vec3 _vector4 ? _vector4 : Vec3.ZERO).y(),
								(MinescapeModVariables.WorldVariables.get(world).NodeGiftPositions.get((int) i) instanceof Vec3 _vector6 ? _vector6 : Vec3.ZERO).z()),
						EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		}
	}
}