package net.mcreator.minescape.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.minescape.entity.BeaconEntity;
import net.mcreator.minescape.client.model.Modelbeacon;

import com.mojang.blaze3d.vertex.PoseStack;

public class BeaconRenderer extends MobRenderer<BeaconEntity, LivingEntityRenderState, Modelbeacon> {
	private final Identifier entityTexture = Identifier.parse("minescape:textures/entities/beacon_.png");

	public BeaconRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbeacon(context.bakeLayer(Modelbeacon.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BeaconEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(1.5f, 1.5f, 1.5f);
	}
}