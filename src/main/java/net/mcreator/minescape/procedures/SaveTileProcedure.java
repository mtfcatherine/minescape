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
		double originX = 0;
		double originY = 0;
		double originZ = 0;
		double fF = 0;
		double fB = 0;
		double fL = 0;
		double fR = 0;
		double fdx = 0;
		double fdz = 0;
		double tidx = 0;
		Vec3 pos1 = Vec3.ZERO;
		Vec3 pos2 = Vec3.ZERO;
		Vec3 origin = Vec3.ZERO;
		Vec3 mid = Vec3.ZERO;
		Vec3 offF = Vec3.ZERO;
		Vec3 offB = Vec3.ZERO;
		Vec3 offL = Vec3.ZERO;
		Vec3 offR = Vec3.ZERO;
		Entity marker = null;
		boolean okF = false;
		boolean okB = false;
		boolean okL = false;
		boolean okR = false;
		id = StringArgumentType.getString(arguments, "id");
		sizeZ = DoubleArgumentType.getDouble(arguments, "sizeZ");
		sizeX = DoubleArgumentType.getDouble(arguments, "sizeX");
		sizeY = DoubleArgumentType.getDouble(arguments, "sizeY");
		weight = DoubleArgumentType.getDouble(arguments, "weight");
		pos1 = MinescapeModVariables.WorldVariables.get(world).TilePos1;
		pos2 = MinescapeModVariables.WorldVariables.get(world).TilePos2;
		if (sizeX % 2 == 0 || sizeZ % 2 == 0) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("WARNING: even width/length - sockets wont align").withColor(0xff0000).withStyle(ChatFormatting.BOLD), false);
			}
		}
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
		originX = pos1.x();
		if (pos2.x() < originX) {
			originX = pos2.x();
		}
		originY = pos1.y();
		if (pos2.y() < originY) {
			originY = pos2.y();
		}
		originZ = pos1.z();
		if (pos2.z() < originZ) {
			originZ = pos2.z();
		}
		origin = new Vec3(originX, originY, originZ);
		mid = (pos1.add(pos2)).scale(0.5);
		if (!world.getEntitiesOfClass(NodeFrontEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
			marker = findEntityInWorldRange(world, NodeFrontEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
			offF = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
			okF = true;
			fF = 2;
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("FOffset = (" + new java.text.DecimalFormat("##.##").format(offF.x()) + ", " + new java.text.DecimalFormat("##.##").format(offF.y()) + ", "
						+ new java.text.DecimalFormat("##.##").format(offF.z()) + ") facing " + new java.text.DecimalFormat("0").format(fF))), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("F: none").withColor(0xff3333), false);
			}
		}
		if (!world.getEntitiesOfClass(NodeBackEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
			marker = findEntityInWorldRange(world, NodeBackEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
			offB = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
			okB = true;
			fB = 0;
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("BOffset = (" + new java.text.DecimalFormat("##.##").format(offB.x()) + ", " + new java.text.DecimalFormat("##.##").format(offB.y()) + ", "
						+ new java.text.DecimalFormat("##.##").format(offB.z()) + ") facing " + new java.text.DecimalFormat("0").format(fB))), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("B: none").withColor(0xff3333), false);
			}
		}
		if (!world.getEntitiesOfClass(NodeLeftEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
			marker = findEntityInWorldRange(world, NodeLeftEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
			offL = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
			okL = true;
			fL = 1;
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("LOffset = (" + new java.text.DecimalFormat("##.##").format(offL.x()) + ", " + new java.text.DecimalFormat("##.##").format(offL.y()) + ", "
						+ new java.text.DecimalFormat("##.##").format(offL.z()) + ") facing " + new java.text.DecimalFormat("0").format(fL))), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("L: none").withColor(0xff3333), false);
			}
		}
		if (!world.getEntitiesOfClass(NodeRightEntity.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3((mid.x()), (mid.y()), (mid.z()))).inflate(64 / 2d), e -> true).isEmpty()) {
			marker = findEntityInWorldRange(world, NodeRightEntity.class, (mid.x()), (mid.y()), (mid.z()), 94);
			offR = new Vec3((marker.getX() - origin.x()), (marker.getY() - origin.y()), (marker.getZ() - origin.z()));
			okR = true;
			fR = 3;
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("ROffset = (" + new java.text.DecimalFormat("##.##").format(offR.x()) + ", " + new java.text.DecimalFormat("##.##").format(offR.y()) + ", "
						+ new java.text.DecimalFormat("##.##").format(offR.z()) + ") facing " + new java.text.DecimalFormat("0").format(fR))), false);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("R: none").withColor(0xff3333), false);
			}
		}
		if (!MinescapeModVariables.WorldVariables.get(world).TileIds.contains(id)) {
			MinescapeModVariables.WorldVariables.get(world).TileIds.add(id);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(weight);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add((new Vec3(sizeX, sizeY, sizeZ)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileLefts.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileRights.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			tidx = MinescapeModVariables.WorldVariables.get(world).TileIds.indexOf(id);
		} else {
			tidx = MinescapeModVariables.WorldVariables.get(world).TileIds.indexOf(id);
			MinescapeModVariables.WorldVariables.get(world).TileWeights.set((int) tidx, weight);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.set((int) tidx, (new Vec3(sizeX, sizeY, sizeZ)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (okF) {
			MinescapeModVariables.WorldVariables.get(world).TileFronts.set((int) tidx, offF);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.set((int) tidx, fF);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		} else {
			MinescapeModVariables.WorldVariables.get(world).TileFronts.set((int) tidx, "none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.set((int) tidx, (-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (okB) {
			MinescapeModVariables.WorldVariables.get(world).TileBacks.set((int) tidx, offB);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.set((int) tidx, fB);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		} else {
			MinescapeModVariables.WorldVariables.get(world).TileBacks.set((int) tidx, "none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.set((int) tidx, (-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (okL) {
			MinescapeModVariables.WorldVariables.get(world).TileLefts.set((int) tidx, offL);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.set((int) tidx, fL);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		} else {
			MinescapeModVariables.WorldVariables.get(world).TileLefts.set((int) tidx, "none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.set((int) tidx, (-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (okR) {
			MinescapeModVariables.WorldVariables.get(world).TileRights.set((int) tidx, offR);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.set((int) tidx, fR);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		} else {
			MinescapeModVariables.WorldVariables.get(world).TileRights.set((int) tidx, "none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.set((int) tidx, (-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList()
					.broadcastSystemMessage(Component.literal(("tile '" + id + "' registered (" + new java.text.DecimalFormat("0").format(MinescapeModVariables.WorldVariables.get(world).TileIds.size()) + " tiles total)")).withColor(0x009900), false);
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}