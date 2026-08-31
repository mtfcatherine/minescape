package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.NodeGiftEntity;
import net.mcreator.minescape.client.model.ModelNodeGift;

public class NodeGiftRenderer extends MobRenderer<NodeGiftEntity, LivingEntityRenderState, ModelNodeGift> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/nodegift.png");

	public NodeGiftRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelNodeGift(context.bakeLayer(ModelNodeGift.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(NodeGiftEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}