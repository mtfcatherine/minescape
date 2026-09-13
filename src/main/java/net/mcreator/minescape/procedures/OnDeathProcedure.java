package net.mcreator.minescape.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.minescape.network.MinescapeModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class OnDeathProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			MinescapeModVariables.MapVariables.get(world).playersDead = new Object() {
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
			}.change(MinescapeModVariables.MapVariables.get(world).playersDead);
			MinescapeModVariables.MapVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).playersAlive = new Object() {
				public double change(Object _obj) {
					if (_obj instanceof Integer _i)
						return _i + -1;
					if (_obj instanceof Long _l)
						return _l + -1;
					if (_obj instanceof Float _f)
						return _f + -1.0f;
					if (_obj instanceof Double _d)
						return _d + -1.0d;
					if (_obj instanceof Number _n)
						return _n.doubleValue() + -1;
					return 0;
				}
			}.change(MinescapeModVariables.WorldVariables.get(world).playersAlive);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
	}
}