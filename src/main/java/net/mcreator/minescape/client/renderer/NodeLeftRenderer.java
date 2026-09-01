package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.NodeLeftEntity;
import net.mcreator.minescape.client.model.ModelNodeLeft;

public class NodeLeftRenderer extends MobRenderer<NodeLeftEntity, LivingEntityRenderState, ModelNodeLeft> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/nodelefttext.png");

	public NodeLeftRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelNodeLeft(context.bakeLayer(ModelNodeLeft.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(NodeLeftEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}