package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.NodeFrontEntity;
import net.mcreator.minescape.client.model.ModelNodeStart;

public class NodeFrontRenderer extends MobRenderer<NodeFrontEntity, LivingEntityRenderState, ModelNodeStart> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/nodestart.png");

	public NodeFrontRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelNodeStart(context.bakeLayer(ModelNodeStart.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(NodeFrontEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}