/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.minescape.MinescapeMod;

@EventBusSubscriber
public class MinescapeModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinescapeMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(MinescapeModItems.NULL_GIFT_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.NODE_GIFT_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.GOLD_GIFT_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.NODE_FRONT_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.NODE_BACK_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.NODE_LEFT_SPAWN_EGG.get());
			tabData.accept(MinescapeModItems.NODE_RIGHT_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
			if (tabData.hasPermissions()) {
				tabData.accept(MinescapeModItems.TILE_WAND.get());
			}
		}
	}
}