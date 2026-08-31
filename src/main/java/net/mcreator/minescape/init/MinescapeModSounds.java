/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.minescape.MinescapeMod;

public class MinescapeModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, MinescapeMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> COLLECT = REGISTRY.register("collect", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("minescape", "collect")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDCOLLECT = REGISTRY.register("goldcollect", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("minescape", "goldcollect")));
}