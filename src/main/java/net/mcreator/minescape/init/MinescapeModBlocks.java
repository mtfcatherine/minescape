/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.minescape.block.PedestalBlock;
import net.mcreator.minescape.MinescapeMod;

import java.util.function.Function;

public class MinescapeModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(MinescapeMod.MODID);
	public static final DeferredBlock<Block> PEDESTAL;
	static {
		PEDESTAL = register("pedestal", PedestalBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}