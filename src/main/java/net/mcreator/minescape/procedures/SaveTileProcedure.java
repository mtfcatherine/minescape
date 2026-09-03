package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.ChatFormatting;

import net.mcreator.minescape.network.MinescapeModVariables;
import net.mcreator.minescape.entity.NodeRightEntity;
import net.mcreator.minescape.entity.NodeLeftEntity;
import net.mcreator.minescape.entity.NodeFrontEntity;
import net.mcreator.minescape.entity.NodeBackEntity;

import java.util.Comparator;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SaveTileProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		String id = "";
		double sizeX = 0;
		double sizeY = 0;
		double sizeZ = 0;
		double weight = 0;
		Vec3 pos1 = Vec3.ZERO;
		Vec3 pos2 = Vec3.ZERO;
		Vec3 origin = Vec3.ZERO;
		Vec3 mid = Vec3.ZERO;
		Vec3 offset = Vec3.ZERO;
		Vec3 frontOff = Vec3.ZERO;
		Vec3 backOff = Vec3.ZERO;
		boolean frontOK = false;
		boolean backOK = false;
		double tidx = 0;
		Entity marker = null;
		id = StringArgumentType.getString(arguments, "id");
		sizeZ = DoubleArgumentType.getDouble(arguments, "sizeZ");
		sizeX = DoubleArgumentType.getDouble(arguments, "sizeX");
		sizeY = DoubleArgumentType.getDouble(arguments, "sizeY");
		weight = DoubleArgumentType.getDouble(arguments, "weight");
		pos1 = MinescapeModVariables.WorldVariables.get(world).TilePos1;
		pos2 = MinescapeModVariables.WorldVariables.get(world).TilePos2;
		if (sizeX % 2 == 0 || sizeZ % 2 == 0) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("WARNING: even width/length - sockets wont align. not saved").withColor(0xff0000).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.UNDERLINE), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				BlockPos p1 = BlockPos.containing(pos1.x(), pos1.y(), pos1.z());
				BlockPos p2 = BlockPos.containing(pos2.x(), pos2.y(), pos2.z());
				BlockPos structureOrigin = new BlockPos(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ()));
				BlockPos size = new BlockPos(Math.abs(p1.getX() - p2.getX()) + 1, Math.abs(p1.getY() - p2.getY()) + 1, Math.abs(p1.getZ() - p2.getZ()) + 1);
				net.minecraft.resources.Identifier structureId = net.minecraft.resources.Identifier.fromNamespaceAndPath("minescape", id);
				StructureTemplateManager structureManager = _level.getStructureManager();
				StructureTemplate template = structureManager.getOrCreate(structureId);
				template.fillFromWorld(_level, structureOrigin, size, true, java.util.List.of(Blocks.STRUCTURE_VOID));
				structureManager.save(structureId);
			}
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
			if (!world.getEntitiesOfClass(NodeBackEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeBackEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				offset = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("BackOffset = (" + offset.x() + ", " + offset.y() + ", " + offset.z() + ")")), false);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Back: none").withColor(0xff3333), false);
				}
			}
			if (!world.getEntitiesOfClass(NodeLeftEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeLeftEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				offset = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("LeftOffset = (" + offset.x() + ", " + offset.y() + ", " + offset.z() + ")")), false);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Left: none").withColor(0xff3333), false);
				}
			}
			if (!world.getEntitiesOfClass(NodeRightEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeRightEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				offset = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("RightOffset = (" + offset.x() + ", " + offset.y() + ", " + offset.z() + ")")), false);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Right: none").withColor(0xff3333), false);
				}
			}
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Saved tile '" + id + "' - " + sizeX + "x" + sizeY + "x" + sizeZ + " weight " + weight)).withColor(0x009900), false);
			}
			frontOK = false;
			backOK = false;
			if (!world.getEntitiesOfClass(NodeFrontEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeFrontEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				frontOff = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				frontOK = true;
			}
			if (!world.getEntitiesOfClass(NodeBackEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
				marker = findEntityInWorldRange(world, NodeBackEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
				backOff = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
				backOK = true;
			}
			if (!MinescapeModVariables.WorldVariables.get(world).TileIds.contains(id)) {
				MinescapeModVariables.WorldVariables.get(world).TileIds.add(id);
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				MinescapeModVariables.WorldVariables.get(world).TileWeights.add(weight);
				MinescapeModVariables.WorldVariables.get(world).TileSizes.add(new Vec3(sizeX, sizeY, sizeZ));
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				MinescapeModVariables.WorldVariables.get(world).TileFronts.add("none");
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				MinescapeModVariables.WorldVariables.get(world).TileBacks.add("none");
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				tidx = MinescapeModVariables.WorldVariables.get(world).TileIds.indexOf(id);
			} else {
				tidx = MinescapeModVariables.WorldVariables.get(world).TileIds.indexOf(id);
				MinescapeModVariables.WorldVariables.get(world).TileWeights.set((int) tidx, weight);
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				MinescapeModVariables.WorldVariables.get(world).TileSizes.set((int) tidx, new Vec3(sizeX, sizeY, sizeZ));
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			}
			if (frontOK) {
				MinescapeModVariables.WorldVariables.get(world).TileFronts.set((int) tidx, frontOff);
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			}
			if (backOK) {
				MinescapeModVariables.WorldVariables.get(world).TileBacks.set((int) tidx, backOff);
				MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			}
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("tile '" + id + "' registered (" + new java.text.DecimalFormat("0").format(MinescapeModVariables.WorldVariables.get(world).TileIds.size()) + " tiles total)")).withColor(0x009900), false);
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}