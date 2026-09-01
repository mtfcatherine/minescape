package net.mcreator.minescape.command;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import net.mcreator.minescape.procedures.SaveTileProcedure;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;

@EventBusSubscriber
public class TilesaveCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("save_tile")

				.then(Commands.argument("id", StringArgumentType.word()).then(Commands.argument("sizeX", DoubleArgumentType.doubleArg(1))
						.then(Commands.argument("sizeY", DoubleArgumentType.doubleArg(1)).then(Commands.argument("sizeZ", DoubleArgumentType.doubleArg(1)).then(Commands.argument("weight", DoubleArgumentType.doubleArg(0)).executes(arguments -> {
							Level world = arguments.getSource().getUnsidedLevel();
							double x = arguments.getSource().getPosition().x();
							double y = arguments.getSource().getPosition().y();
							double z = arguments.getSource().getPosition().z();
							Entity entity = arguments.getSource().getEntity();
							if (entity == null && world instanceof ServerLevel _servLevel)
								entity = FakePlayerFactory.getMinecraft(_servLevel);
							Direction direction = Direction.DOWN;
							if (entity != null)
								direction = entity.getDirection();

							SaveTileProcedure.execute(world, arguments);
							return 0;
						})))))));
	}

}