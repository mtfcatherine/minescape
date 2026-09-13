package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.minescape.network.MinescapeModVariables;

public class MainPlayerEntersDimensionProcedure {
	public static void execute(LevelAccessor world) {
		MinescapeModVariables.WorldVariables.get(world).playersAlive = new Object() {
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
		}.change(MinescapeModVariables.WorldVariables.get(world).playersAlive);
		MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
	}
}