package net.mcreator.minescape.procedures;

import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.ChatFormatting;

import net.mcreator.minescape.network.MinescapeModVariables;
import net.mcreator.minescape.entity.NodeFrontEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;

@EventBusSubscriber
public class SaveTileProcedure {
	@SubscribeEvent
	public static void onCommand(CommandEvent event) {
		Entity entity = event.getParseResults().getContext().getSource().getEntity();
		if (entity != null) {
			execute(event, entity.level(), event.getParseResults().getContext().build(event.getParseResults().getReader().getString()));
		}
	}

	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		execute(null, world, arguments);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		String id = "";
		String commandSTR = "";
		double sizeX = 0;
		double sizeY = 0;
		double sizeZ = 0;
		double weight = 0;
		Vec3 pos1 = Vec3.ZERO;
		Vec3 pos2 = Vec3.ZERO;
		Vec3 origin = Vec3.ZERO;
		Vec3 mid = Vec3.ZERO;
		Vec3 offset = Vec3.ZERO;
		Entity marker = null;
		weight = DoubleArgumentType.getDouble(arguments, "weight");
		sizeZ = DoubleArgumentType.getDouble(arguments, "sizeZ");
		sizeX = DoubleArgumentType.getDouble(arguments, "sizeX");
		sizeY = DoubleArgumentType.getDouble(arguments, "sizeY");
		pos1 = MinescapeModVariables.WorldVariables.get(world).TilePos1;
		pos2 = MinescapeModVariables.WorldVariables.get(world).TilePos2;
		id = StringArgumentType.getString(arguments, "id");
		commandSTR = "structure save minescape:" + id + " " + pos1.x() + " " + pos1.y() + " " + pos1.z() + " " + pos2.x() + " " + pos2.y() + " " + pos2.z();
		if (sizeX % 2 == 0 || sizeZ % 2 == 0) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("WARNING: even width/length - sockets wont align. not saved").withColor(0xff0000).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.UNDERLINE), false);
			}
		} else {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((pos1.x()), (pos1.y()), (pos1.z())), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						commandSTR);
			origin = pos1;
			mid = (pos1.add(pos2)).scale(0.5);
			if (!world.getEntitiesOfClass(NodeFrontEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeFrontEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				offset = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("FrontOffset = (" + offset.x() + ", " + offset.y() + ", " + offset.z() + ")")), false);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Front: none").withColor(0xff3333), false);
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}