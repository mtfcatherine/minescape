package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;

public class RotateVector90WVProcedure {
	public static Vec3 execute(double y) {
		Vec3 scaled = Vec3.ZERO;
		Vec3 v = Vec3.ZERO;
		scaled = v.scale((-1));
		return new Vec3((scaled.z()), y, (v.x()));
	}
}