package net.mcreator.minescape.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.minescape.network.MinescapeModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SetDoubleJumpAmountProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		{
			MinescapeModVariables.PlayerVariables _vars = entity.getData(MinescapeModVariables.PLAYER_VARIABLES);
			_vars.DoubleJumpAmount = DoubleArgumentType.getDouble(arguments, "value");
			_vars.markSyncDirty();
		}
	}
}