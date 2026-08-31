package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.NodeBackEntity;
import net.mcreator.minescape.client.model.ModelNodeEnd;

public class NodeBackRenderer extends MobRenderer<NodeBackEntity, LivingEntityRenderState, ModelNodeEnd> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/nodeend.png");

	public NodeBackRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelNodeEnd(context.bakeLayer(ModelNodeEnd.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(NodeBackEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}