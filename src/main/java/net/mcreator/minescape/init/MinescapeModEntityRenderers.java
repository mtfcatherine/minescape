/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.minescape.client.renderer.NullGiftRenderer;
import net.mcreator.minescape.client.renderer.NodeGiftRenderer;
import net.mcreator.minescape.client.renderer.NodeFrontRenderer;
import net.mcreator.minescape.client.renderer.NodeBackRenderer;
import net.mcreator.minescape.client.renderer.GoldGiftRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class MinescapeModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinescapeModEntities.NULL_GIFT.get(), NullGiftRenderer::new);
		event.registerEntityRenderer(MinescapeModEntities.NODE_GIFT.get(), NodeGiftRenderer::new);
		event.registerEntityRenderer(MinescapeModEntities.GOLD_GIFT.get(), GoldGiftRenderer::new);
		event.registerEntityRenderer(MinescapeModEntities.NODE_FRONT.get(), NodeFrontRenderer::new);
		event.registerEntityRenderer(MinescapeModEntities.NODE_BACK.get(), NodeBackRenderer::new);
	}
}