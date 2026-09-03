/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.minescape.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class MinescapeModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelNodeStart.LAYER_LOCATION, ModelNodeStart::createBodyLayer);
		event.registerLayerDefinition(Modelgift.LAYER_LOCATION, Modelgift::createBodyLayer);
		event.registerLayerDefinition(Modelbeacon.LAYER_LOCATION, Modelbeacon::createBodyLayer);
		event.registerLayerDefinition(ModelNodeEnd.LAYER_LOCATION, ModelNodeEnd::createBodyLayer);
		event.registerLayerDefinition(ModelNodeLeft.LAYER_LOCATION, ModelNodeLeft::createBodyLayer);
		event.registerLayerDefinition(ModelNodeRight.LAYER_LOCATION, ModelNodeRight::createBodyLayer);
		event.registerLayerDefinition(ModelNodeGift.LAYER_LOCATION, ModelNodeGift::createBodyLayer);
	}
}