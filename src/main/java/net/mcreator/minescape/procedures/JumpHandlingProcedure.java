package net.mcreator.minescape.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

import net.mcreator.minescape.network.MinescapeModVariables;

public class JumpHandlingProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround()) {
			{
				MinescapeModVariables.PlayerVariables _vars = entity.getData(MinescapeModVariables.PLAYER_VARIABLES);
				_vars.DoubleJumpAmount = entity.getData(MinescapeModVariables.PLAYER_VARIABLES).MaxDoubleJumps;
				_vars.markSyncDirty();
			}
		}
		if (entity.getData(MinescapeModVariables.PLAYER_VARIABLES).DoubleJumpAmount >= 1 && entity.onGround() != true) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.65, (entity.getDeltaMovement().z())));
			{
				MinescapeModVariables.PlayerVariables _vars = entity.getData(MinescapeModVariables.PLAYER_VARIABLES);
				_vars.DoubleJumpAmount = entity.getData(MinescapeModVariables.PLAYER_VARIABLES).DoubleJumpAmount - 1;
				_vars.markSyncDirty();
			}
		}
	}
}