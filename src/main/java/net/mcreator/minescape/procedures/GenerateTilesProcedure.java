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
import net.mcreator.minescape.MinescapeMod;

import java.util.Comparator;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class GenerateTilesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		double count = 0;
		double attempts = 0;
		double generated = 0;
		double maxAttempts = 0;
		double Jx = 0;
		double Jy = 0;
		double Jz = 0;
		double dir = 0;
		double entrySide = 0;
		double idx = 0;
		double side = 0;
		double s = 0;
		double i = 0;
		double newIdx = 0;
		double tileIdx = 0;
		double defIdx = 0;
		double rot = 0;
		double rotIdx = 0;
		double seedIdx = 0;
		double fE = 0;
		double totalWeight = 0;
		double roll = 0;
		double ox = 0;
		double oy = 0;
		double oz = 0;
		double vx = 0;
		double vy = 0;
		double vz = 0;
		double rbx = 0;
		double rbz = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double bx1 = 0;
		double bz1 = 0;
		double bx2 = 0;
		double bz2 = 0;
		double ex = 0;
		double ey = 0;
		double ez = 0;
		double parentIdx = 0;
		double pickIdx = 0;
		boolean valid = false;
		boolean ov = false;
		boolean tc = false;
		boolean placedOk = false;
		boolean hasNode = false;
		String tileId = "";
		String rotStr = "";
		if (MinescapeModVariables.WorldVariables.get(world).TileIds.isEmpty()) {
			MinescapeModVariables.WorldVariables.get(world).TileIds.add("square");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(5);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add((new Vec3(5, 2, 5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add((new Vec3(2.5, 0, 4.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add((new Vec3(2.5, 0, 0.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileLefts.add((new Vec3(0.5, 0, 2.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileRights.add((new Vec3(4.5, 0, 2.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.add(2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.add(0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.add(1);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.add(3);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileIds.add("testtile1");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileWeights.add(3);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileSizes.add((new Vec3(5, 1, 9)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileBacks.add((new Vec3(2.5, 0, 8.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFronts.add((new Vec3(2.5, 0, 0.5)));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileLefts.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileRights.add("none");
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingF.add(2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingB.add(0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingL.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).TileFacingR.add((-1));
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("seeded default tile registry (square + testtile1)").withColor(0x009900), false);
			}
		}
		if (MinescapeModVariables.WorldVariables.get(world).TileIds.isEmpty()) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("no tiles saved and no defaults found").withColor(0xff3333), false);
			}
		} else {
			count = DoubleArgumentType.getDouble(arguments, "count");
			Jx = Math.floor(x) + 0.5;
			Jy = Math.floor(entity.getY()) - 1;
			Jz = Math.floor(z) + 0.5;
			maxAttempts = 20000;
			generated = 0;
			MinescapeModVariables.WorldVariables.get(world).PTDef.clear();
			MinescapeModVariables.WorldVariables.get(world).PTOx.clear();
			MinescapeModVariables.WorldVariables.get(world).PTOy.clear();
			MinescapeModVariables.WorldVariables.get(world).PTOz.clear();
			MinescapeModVariables.WorldVariables.get(world).PTRot.clear();
			MinescapeModVariables.WorldVariables.get(world).FTile.clear();
			MinescapeModVariables.WorldVariables.get(world).FSide.clear();
			MinescapeModVariables.WorldVariables.get(world).BoxX1.clear();
			MinescapeModVariables.WorldVariables.get(world).BoxZ1.clear();
			MinescapeModVariables.WorldVariables.get(world).BoxX2.clear();
			MinescapeModVariables.WorldVariables.get(world).BoxZ2.clear();
			seedIdx = MinescapeModVariables.WorldVariables.get(world).TileIds.indexOf("start");
			if (seedIdx < 0) {
				seedIdx = 0;
			}
			sx = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) seedIdx) instanceof Vec3 _vector48 ? _vector48 : Vec3.ZERO).x();
			sy = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) seedIdx) instanceof Vec3 _vector50 ? _vector50 : Vec3.ZERO).y();
			sz = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) seedIdx) instanceof Vec3 _vector52 ? _vector52 : Vec3.ZERO).z();
			ox = Jx - sx / 2;
			oy = Jy;
			oz = Jz - sz / 2;
			bx1 = ox;
			bz1 = oz;
			bx2 = ox + sx;
			bz2 = oz + sz;
			tileId = MinescapeModVariables.WorldVariables.get(world).TileIds.get((int) seedIdx) instanceof String _str54 ? _str54 : "";
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("place template minescape:" + tileId + " " + new java.text.DecimalFormat("0").format(ox) + " " + new java.text.DecimalFormat("0").format(oy) + " " + new java.text.DecimalFormat("0").format(oz)));
			generated = 1;
			MinescapeModVariables.WorldVariables.get(world).PTDef.add(seedIdx);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).PTOx.add(ox);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).PTOy.add(oy);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).PTOz.add(oz);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).PTRot.add(0);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			newIdx = MinescapeModVariables.WorldVariables.get(world).PTOx.size() - 1;
			MinescapeModVariables.WorldVariables.get(world).BoxX1.add(bx1);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).BoxZ1.add(bz1);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).BoxX2.add(bx2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			MinescapeModVariables.WorldVariables.get(world).BoxZ2.add(bz2);
			MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
			s = 0;
			while (s < 4) {
				if (s == 0) {
					hasNode = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) seedIdx) instanceof Vec3;
				} else if (s == 1) {
					hasNode = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) seedIdx) instanceof Vec3;
				} else if (s == 2) {
					hasNode = MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) seedIdx) instanceof Vec3;
				} else if (s == 3) {
					hasNode = MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) seedIdx) instanceof Vec3;
				}
				if (hasNode) {
					MinescapeModVariables.WorldVariables.get(world).FTile.add(newIdx);
					MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
					MinescapeModVariables.WorldVariables.get(world).FSide.add(s);
					MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
				}
				s = s + 1;
			}
			attempts = 0;
			while (generated < count && attempts < maxAttempts) {
				attempts = attempts + 1;
				if (MinescapeModVariables.WorldVariables.get(world).FTile.size() == 0) {
					break;
				}
				pickIdx = Mth.nextInt(RandomSource.create(), 0, (int) (MinescapeModVariables.WorldVariables.get(world).FTile.size() - 1));
				tileIdx = MinescapeModVariables.WorldVariables.get(world).FTile.get((int) pickIdx) instanceof Double _doub75 ? _doub75 : 0.0D;
				side = MinescapeModVariables.WorldVariables.get(world).FSide.get((int) pickIdx) instanceof Double _doub76 ? _doub76 : 0.0D;
				MinescapeModVariables.WorldVariables.get(world).FTile.remove((int) pickIdx);
				MinescapeModVariables.WorldVariables.get(world).FSide.remove((int) pickIdx);
				defIdx = MinescapeModVariables.WorldVariables.get(world).PTDef.get((int) tileIdx) instanceof Double _doub79 ? _doub79 : 0.0D;
				rot = MinescapeModVariables.WorldVariables.get(world).PTRot.get((int) tileIdx) instanceof Double _doub80 ? _doub80 : 0.0D;
				if (side == 0) {
					vx = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) defIdx) instanceof Vec3 _vector81 ? _vector81 : Vec3.ZERO).x();
					vy = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) defIdx) instanceof Vec3 _vector83 ? _vector83 : Vec3.ZERO).y();
					vz = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) defIdx) instanceof Vec3 _vector85 ? _vector85 : Vec3.ZERO).z();
				} else if (side == 1) {
					vx = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) defIdx) instanceof Vec3 _vector87 ? _vector87 : Vec3.ZERO).x();
					vy = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) defIdx) instanceof Vec3 _vector89 ? _vector89 : Vec3.ZERO).y();
					vz = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) defIdx) instanceof Vec3 _vector91 ? _vector91 : Vec3.ZERO).z();
				} else if (side == 2) {
					vx = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) defIdx) instanceof Vec3 _vector93 ? _vector93 : Vec3.ZERO).x();
					vy = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) defIdx) instanceof Vec3 _vector95 ? _vector95 : Vec3.ZERO).y();
					vz = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) defIdx) instanceof Vec3 _vector97 ? _vector97 : Vec3.ZERO).z();
				} else if (side == 3) {
					vx = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) defIdx) instanceof Vec3 _vector99 ? _vector99 : Vec3.ZERO).x();
					vy = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) defIdx) instanceof Vec3 _vector101 ? _vector101 : Vec3.ZERO).y();
					vz = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) defIdx) instanceof Vec3 _vector103 ? _vector103 : Vec3.ZERO).z();
				}
				if (rot == 0) {
					rbx = vx;
					rbz = vz;
				} else if (rot == 1) {
					rbx = 1 - vz;
					rbz = vx;
				} else if (rot == 2) {
					rbx = 1 - vx;
					rbz = 1 - vz;
				} else if (rot == 3) {
					rbx = vz;
					rbz = 1 - vx;
				}
				ex = (MinescapeModVariables.WorldVariables.get(world).PTOx.get((int) tileIdx) instanceof Double _doub105 ? _doub105 : 0.0D) + rbx;
				ey = (MinescapeModVariables.WorldVariables.get(world).PTOy.get((int) tileIdx) instanceof Double _doub106 ? _doub106 : 0.0D) + vy;
				ez = (MinescapeModVariables.WorldVariables.get(world).PTOz.get((int) tileIdx) instanceof Double _doub107 ? _doub107 : 0.0D) + rbz;
				if (side == 0) {
					fE = MinescapeModVariables.WorldVariables.get(world).TileFacingF.get((int) defIdx) instanceof Double _doub108 ? _doub108 : 0.0D;
				} else if (side == 1) {
					fE = MinescapeModVariables.WorldVariables.get(world).TileFacingB.get((int) defIdx) instanceof Double _doub109 ? _doub109 : 0.0D;
				} else if (side == 2) {
					fE = MinescapeModVariables.WorldVariables.get(world).TileFacingL.get((int) defIdx) instanceof Double _doub110 ? _doub110 : 0.0D;
				} else if (side == 3) {
					fE = MinescapeModVariables.WorldVariables.get(world).TileFacingR.get((int) defIdx) instanceof Double _doub111 ? _doub111 : 0.0D;
				}
				dir = ((fE + rot) % 4 + 4) % 4;
				if (side % 2 == 0) {
					entrySide = side + 1;
				} else {
					entrySide = side - 1;
				}
				parentIdx = tileIdx;
				totalWeight = 0;
				i = 0;
				while (i < MinescapeModVariables.WorldVariables.get(world).TileWeights.size()) {
					if (entrySide == 0) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) i) instanceof Vec3;
					} else if (entrySide == 1) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) i) instanceof Vec3;
					} else if (entrySide == 2) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) i) instanceof Vec3;
					} else if (entrySide == 3) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) i) instanceof Vec3;
					}
					if (hasNode) {
						totalWeight = totalWeight + (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub117 ? _doub117 : 0.0D);
					}
					i = i + 1;
				}
				roll = Mth.nextDouble(RandomSource.create(), 0.0001, totalWeight);
				i = 0;
				idx = -1;
				while (i < MinescapeModVariables.WorldVariables.get(world).TileWeights.size() && idx == -1) {
					if (entrySide == 0) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) i) instanceof Vec3;
					} else if (entrySide == 1) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) i) instanceof Vec3;
					} else if (entrySide == 2) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) i) instanceof Vec3;
					} else if (entrySide == 3) {
						hasNode = MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) i) instanceof Vec3;
					}
					if (hasNode) {
						if (roll <= (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub124 ? _doub124 : 0.0D)) {
							idx = i;
						} else {
							roll = roll - (MinescapeModVariables.WorldVariables.get(world).TileWeights.get((int) i) instanceof Double _doub125 ? _doub125 : 0.0D);
						}
					}
					i = i + 1;
				}
				if (totalWeight <= 0) {
					placedOk = false;
				} else {
					if (idx == -1) {
						i = MinescapeModVariables.WorldVariables.get(world).TileWeights.size();
						while (i > 0 && idx == -1) {
							i = i - 1;
							if (entrySide == 0) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) i) instanceof Vec3;
							} else if (entrySide == 1) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) i) instanceof Vec3;
							} else if (entrySide == 2) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) i) instanceof Vec3;
							} else if (entrySide == 3) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) i) instanceof Vec3;
							}
							if (hasNode) {
								idx = i;
							}
						}
					}
					if (entrySide == 0) {
						fE = MinescapeModVariables.WorldVariables.get(world).TileFacingF.get((int) idx) instanceof Double _doub131 ? _doub131 : 0.0D;
					} else if (entrySide == 1) {
						fE = MinescapeModVariables.WorldVariables.get(world).TileFacingB.get((int) idx) instanceof Double _doub132 ? _doub132 : 0.0D;
					} else if (entrySide == 2) {
						fE = MinescapeModVariables.WorldVariables.get(world).TileFacingL.get((int) idx) instanceof Double _doub133 ? _doub133 : 0.0D;
					} else if (entrySide == 3) {
						fE = MinescapeModVariables.WorldVariables.get(world).TileFacingR.get((int) idx) instanceof Double _doub134 ? _doub134 : 0.0D;
					}
					rotIdx = ((dir - fE + 2) % 4 + 4) % 4;
					if (entrySide == 0) {
						vx = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3 _vector135 ? _vector135 : Vec3.ZERO).x();
						vy = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3 _vector137 ? _vector137 : Vec3.ZERO).y();
						vz = (MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3 _vector139 ? _vector139 : Vec3.ZERO).z();
					} else if (entrySide == 1) {
						vx = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3 _vector141 ? _vector141 : Vec3.ZERO).x();
						vy = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3 _vector143 ? _vector143 : Vec3.ZERO).y();
						vz = (MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3 _vector145 ? _vector145 : Vec3.ZERO).z();
					} else if (entrySide == 2) {
						vx = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) idx) instanceof Vec3 _vector147 ? _vector147 : Vec3.ZERO).x();
						vy = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) idx) instanceof Vec3 _vector149 ? _vector149 : Vec3.ZERO).y();
						vz = (MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) idx) instanceof Vec3 _vector151 ? _vector151 : Vec3.ZERO).z();
					} else if (entrySide == 3) {
						vx = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) idx) instanceof Vec3 _vector153 ? _vector153 : Vec3.ZERO).x();
						vy = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) idx) instanceof Vec3 _vector155 ? _vector155 : Vec3.ZERO).y();
						vz = (MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) idx) instanceof Vec3 _vector157 ? _vector157 : Vec3.ZERO).z();
					}
					if (rotIdx == 0) {
						rbx = vx;
						rbz = vz;
					} else if (rotIdx == 1) {
						rbx = 1 - vz;
						rbz = vx;
					} else if (rotIdx == 2) {
						rbx = 1 - vx;
						rbz = 1 - vz;
					} else if (rotIdx == 3) {
						rbx = vz;
						rbz = 1 - vx;
					}
					ox = ex - rbx;
					oy = ey - vy;
					oz = ez - rbz;
					sx = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) idx) instanceof Vec3 _vector159 ? _vector159 : Vec3.ZERO).x();
					sy = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) idx) instanceof Vec3 _vector161 ? _vector161 : Vec3.ZERO).y();
					sz = (MinescapeModVariables.WorldVariables.get(world).TileSizes.get((int) idx) instanceof Vec3 _vector163 ? _vector163 : Vec3.ZERO).z();
					if (rotIdx == 0) {
						bx1 = ox;
						bz1 = oz;
						bx2 = ox + sx;
						bz2 = oz + sz;
					} else if (rotIdx == 1) {
						bx1 = ox - sz + 1;
						bz1 = oz;
						bx2 = ox + 1;
						bz2 = oz + sx;
					} else if (rotIdx == 2) {
						bx1 = ox - sx + 1;
						bz1 = oz - sz + 1;
						bx2 = ox + 1;
						bz2 = oz + 1;
					} else if (rotIdx == 3) {
						bx1 = ox;
						bz1 = oz - sx + 1;
						bx2 = ox + sz;
						bz2 = oz + 1;
					}
					valid = true;
					i = 0;
					while (i < MinescapeModVariables.WorldVariables.get(world).BoxX1.size() && valid) {
						if (!(i == parentIdx)) {
							ov = !(bx2 <= (MinescapeModVariables.WorldVariables.get(world).BoxX1.get((int) i) instanceof Double _doub166 ? _doub166 : 0.0D)
									|| bx1 >= (MinescapeModVariables.WorldVariables.get(world).BoxX2.get((int) i) instanceof Double _doub167 ? _doub167 : 0.0D)
									|| bz2 <= (MinescapeModVariables.WorldVariables.get(world).BoxZ1.get((int) i) instanceof Double _doub168 ? _doub168 : 0.0D)
									|| bz1 >= (MinescapeModVariables.WorldVariables.get(world).BoxZ2.get((int) i) instanceof Double _doub169 ? _doub169 : 0.0D));
							if (ov) {
								valid = false;
							} else {
								tc = (Math.abs(bx2 - (MinescapeModVariables.WorldVariables.get(world).BoxX1.get((int) i) instanceof Double _doub170 ? _doub170 : 0.0D)) < 0.01
										|| Math.abs((MinescapeModVariables.WorldVariables.get(world).BoxX2.get((int) i) instanceof Double _doub171 ? _doub171 : 0.0D) - bx1) < 0.01)
										&& bz1 < (MinescapeModVariables.WorldVariables.get(world).BoxZ2.get((int) i) instanceof Double _doub172 ? _doub172 : 0.0D)
										&& bz2 > (MinescapeModVariables.WorldVariables.get(world).BoxZ1.get((int) i) instanceof Double _doub173 ? _doub173 : 0.0D)
										|| (Math.abs(bz2 - (MinescapeModVariables.WorldVariables.get(world).BoxZ1.get((int) i) instanceof Double _doub174 ? _doub174 : 0.0D)) < 0.01
												|| Math.abs((MinescapeModVariables.WorldVariables.get(world).BoxZ2.get((int) i) instanceof Double _doub175 ? _doub175 : 0.0D) - bz1) < 0.01)
												&& bx1 < (MinescapeModVariables.WorldVariables.get(world).BoxX2.get((int) i) instanceof Double _doub176 ? _doub176 : 0.0D)
												&& bx2 > (MinescapeModVariables.WorldVariables.get(world).BoxX1.get((int) i) instanceof Double _doub177 ? _doub177 : 0.0D);
								if (tc) {
									valid = false;
								}
							}
						}
						i = i + 1;
					}
					if (valid) {
						tileId = MinescapeModVariables.WorldVariables.get(world).TileIds.get((int) idx) instanceof String _str178 ? _str178 : "";
						if (rotIdx == 1) {
							rotStr = " clockwise_90";
						} else if (rotIdx == 2) {
							rotStr = " clockwise_180";
						} else if (rotIdx == 3) {
							rotStr = " counterclockwise_90";
						} else {
							rotStr = "";
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, LevelBasedPermissionSet.OWNER, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("place template minescape:" + tileId + " " + new java.text.DecimalFormat("0").format(ox) + " " + new java.text.DecimalFormat("0").format(oy) + " " + new java.text.DecimalFormat("0").format(oz) + rotStr));
						placedOk = true;
						MinescapeModVariables.WorldVariables.get(world).PTDef.add(idx);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).PTOx.add(ox);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).PTOy.add(oy);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).PTOz.add(oz);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).PTRot.add(rotIdx);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						newIdx = MinescapeModVariables.WorldVariables.get(world).PTOx.size() - 1;
						MinescapeModVariables.WorldVariables.get(world).BoxX1.add(bx1);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).BoxZ1.add(bz1);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).BoxX2.add(bx2);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						MinescapeModVariables.WorldVariables.get(world).BoxZ2.add(bz2);
						MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
						s = 0;
						while (s < 4) {
							if (s == 0) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileFronts.get((int) idx) instanceof Vec3;
							} else if (s == 1) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileBacks.get((int) idx) instanceof Vec3;
							} else if (s == 2) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileLefts.get((int) idx) instanceof Vec3;
							} else if (s == 3) {
								hasNode = MinescapeModVariables.WorldVariables.get(world).TileRights.get((int) idx) instanceof Vec3;
							}
							if (!(s == entrySide) && hasNode) {
								MinescapeModVariables.WorldVariables.get(world).FTile.add(newIdx);
								MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
								MinescapeModVariables.WorldVariables.get(world).FSide.add(s);
								MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
							}
							s = s + 1;
						}
					} else {
						placedOk = false;
					}
				}
				if (placedOk) {
					generated = generated + 1;
				}
			}
			MinescapeMod.queueServerWork(10, () -> {
				{
					final Vec3 _center = new Vec3(x, y, z);
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2048 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof NodeGiftEntity) {
							MinescapeModVariables.MapVariables.get(world).GeneratedGifts = MinescapeModVariables.MapVariables.get(world).GeneratedGifts + 1;
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
			});
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList()
						.broadcastSystemMessage(Component
								.literal(("Generated " + new java.text.DecimalFormat("0").format(generated) + " / " + new java.text.DecimalFormat("0").format(count) + " tiles (" + new java.text.DecimalFormat("0").format(attempts) + " attempts)"))
								.withColor(0x44cc44), false);
			}
		}
	}
}