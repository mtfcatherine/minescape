package net.mcreator.minescape.client.model;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelbeacon extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("minescape", "modelbeacon"), "main");
	public final ModelPart model;

	public Modelbeacon(ModelPart root) {
		super(root);
		this.model = root.getChild("model");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition model = partdefinition.addOrReplaceChild("model",
				CubeListBuilder.create().texOffs(0, 67).addBox(-13.0F, 0.0F, -13.0F, 26.0F, 8.0F, 26.0F, new CubeDeformation(0.0F)).texOffs(80, 0).addBox(-12.0F, 8.0F, -12.0F, 24.0F, 9.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(0, 101)
						.addBox(-11.0F, 17.0F, -11.0F, 22.0F, 15.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-10.0F, 32.0F, -10.0F, 20.0F, 47.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(80, 33)
						.addBox(-8.0F, 79.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}

}