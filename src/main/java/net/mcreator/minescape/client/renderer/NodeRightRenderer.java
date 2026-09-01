package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.NodeRightEntity;
import net.mcreator.minescape.client.model.ModelNodeRight;

public class NodeRightRenderer extends MobRenderer<NodeRightEntity, LivingEntityRenderState, ModelNodeRight> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/noderight.png");

	public NodeRightRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelNodeRight(context.bakeLayer(ModelNodeRight.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(NodeRightEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}