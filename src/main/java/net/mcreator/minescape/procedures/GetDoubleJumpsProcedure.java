package net.mcreator.minescape.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.minescape.network.MinescapeModVariables;

public class GetDoubleJumpsProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		{
			MinescapeModVariables.PlayerVariables _vars = entity.getData(MinescapeModVariables.PLAYER_VARIABLES);
			_vars.DoubleJumpAmountSTR = new java.text.DecimalFormat("Double Jumps: #").format(entity.getData(MinescapeModVariables.PLAYER_VARIABLES).DoubleJumpAmount);
			_vars.markSyncDirty();
		}
		return entity.getData(MinescapeModVariables.PLAYER_VARIABLES).DoubleJumpAmountSTR;
	}
}