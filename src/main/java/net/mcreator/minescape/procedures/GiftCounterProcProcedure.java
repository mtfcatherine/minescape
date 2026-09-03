package net.mcreator.minescape.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.minescape.network.MinescapeModVariables;

public class GiftCounterProcProcedure {
	public static String execute(LevelAccessor world) {
		MinescapeModVariables.WorldVariables.get(world).GiftsSTR = new java.text.DecimalFormat("##.##").format(MinescapeModVariables.WorldVariables.get(world).GiftsCollected) + " | "
				+ new java.text.DecimalFormat("##").format(MinescapeModVariables.MapVariables.get(world).GeneratedGifts);
		MinescapeModVariables.WorldVariables.get(world).markSyncDirty();
		return MinescapeModVariables.WorldVariables.get(world).GiftsSTR;
	}
}