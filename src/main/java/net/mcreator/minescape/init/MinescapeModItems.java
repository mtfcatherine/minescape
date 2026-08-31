/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.minescape.MinescapeMod;

import java.util.function.Function;

public class MinescapeModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(MinescapeMod.MODID);
	public static final DeferredItem<Item> NULL_GIFT_SPAWN_EGG;
	public static final DeferredItem<Item> NODE_GIFT_SPAWN_EGG;
	public static final DeferredItem<Item> GOLD_GIFT_SPAWN_EGG;
	public static final DeferredItem<Item> PEDESTAL;
	public static final DeferredItem<Item> NODE_FRONT_SPAWN_EGG;
	public static final DeferredItem<Item> NODE_BACK_SPAWN_EGG;
	static {
		NULL_GIFT_SPAWN_EGG = register("null_gift_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(MinescapeModEntities.NULL_GIFT.get())));
		NODE_GIFT_SPAWN_EGG = register("node_gift_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(MinescapeModEntities.NODE_GIFT.get())));
		GOLD_GIFT_SPAWN_EGG = register("gold_gift_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(MinescapeModEntities.GOLD_GIFT.get())));
		PEDESTAL = block(MinescapeModBlocks.PEDESTAL);
		NODE_FRONT_SPAWN_EGG = register("node_front_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(MinescapeModEntities.NODE_FRONT.get())));
		NODE_BACK_SPAWN_EGG = register("node_back_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(MinescapeModEntities.NODE_BACK.get())));
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}
}