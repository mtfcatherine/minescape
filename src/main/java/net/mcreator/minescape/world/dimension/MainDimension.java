package net.mcreator.minescape.world.dimension;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.minescape.procedures.MainPlayerLeavesDimensionProcedure;
import net.mcreator.minescape.procedures.MainPlayerEntersDimensionProcedure;

@EventBusSubscriber
public class MainDimension {
	@SubscribeEvent
	public static void onPlayerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
		Entity entity = event.getEntity();
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (event.getFrom() == ResourceKey.create(Registries.DIMENSION, Identifier.parse("minescape:main"))) {
			MainPlayerLeavesDimensionProcedure.execute(world);
		}
		if (event.getTo() == ResourceKey.create(Registries.DIMENSION, Identifier.parse("minescape:main"))) {
			MainPlayerEntersDimensionProcedure.execute(world);
		}
	}
}