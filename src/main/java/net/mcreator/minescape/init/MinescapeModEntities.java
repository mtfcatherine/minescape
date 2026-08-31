/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minescape.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.minescape.entity.NullGiftEntity;
import net.mcreator.minescape.entity.NodeGiftEntity;
import net.mcreator.minescape.entity.NodeFrontEntity;
import net.mcreator.minescape.entity.NodeBackEntity;
import net.mcreator.minescape.entity.GoldGiftEntity;
import net.mcreator.minescape.MinescapeMod;

@EventBusSubscriber
public class MinescapeModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, MinescapeMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<NullGiftEntity>> NULL_GIFT = register("null_gift",
			EntityType.Builder.<NullGiftEntity>of(NullGiftEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.notInPeaceful().sized(0.25f, 0.25f));
	public static final DeferredHolder<EntityType<?>, EntityType<NodeGiftEntity>> NODE_GIFT = register("node_gift",
			EntityType.Builder.<NodeGiftEntity>of(NodeGiftEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.notInPeaceful().sized(0.2f, 0.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<GoldGiftEntity>> GOLD_GIFT = register("gold_gift",
			EntityType.Builder.<GoldGiftEntity>of(GoldGiftEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.notInPeaceful().sized(0.25f, 0.25f));
	public static final DeferredHolder<EntityType<?>, EntityType<NodeFrontEntity>> NODE_FRONT = register("node_front",
			EntityType.Builder.<NodeFrontEntity>of(NodeFrontEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.notInPeaceful().sized(0.2f, 0.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<NodeBackEntity>> NODE_BACK = register("node_back",
			EntityType.Builder.<NodeBackEntity>of(NodeBackEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.notInPeaceful().sized(0.2f, 0.2f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MinescapeMod.MODID, registryname))));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		NullGiftEntity.init(event);
		NodeGiftEntity.init(event);
		GoldGiftEntity.init(event);
		NodeFrontEntity.init(event);
		NodeBackEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(NULL_GIFT.get(), NullGiftEntity.createAttributes().build());
		event.put(NODE_GIFT.get(), NodeGiftEntity.createAttributes().build());
		event.put(GOLD_GIFT.get(), GoldGiftEntity.createAttributes().build());
		event.put(NODE_FRONT.get(), NodeFrontEntity.createAttributes().build());
		event.put(NODE_BACK.get(), NodeBackEntity.createAttributes().build());
	}
}