package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.minescape.network.MinescapeModVariables;

public class GetGiftCountProcedure {
	public static String execute(LevelAccessor world) {
		MinescapeModVariables.WorldVariables.get(world).GiftsCollectedSTR = new java.text.DecimalFormat("( ##.## )").format(MinescapeModVariables.WorldVariables.get(world).GiftsCollected);
		MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		return MinescapeModVariables.WorldVariables.get(world).GiftsCollectedSTR;
	}
}