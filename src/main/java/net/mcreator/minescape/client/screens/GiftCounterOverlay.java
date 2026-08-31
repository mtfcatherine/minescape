package net.mcreator.minescape.client.screens;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

import net.mcreator.minescape.procedures.GetGoldCountProcedure;
import net.mcreator.minescape.procedures.GetGiftCountProcedure;

@EventBusSubscriber(Dist.CLIENT)
public class GiftCounterOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (true) {
			event.getGuiGraphics().text(Minecraft.getInstance().font,

					GetGiftCountProcedure.execute(world), w - 365, h - 16, -6750055, true);
			event.getGuiGraphics().text(Minecraft.getInstance().font,

					GetGoldCountProcedure.execute(world), w - 365, h - 32, -256, true);
		}
	}
}