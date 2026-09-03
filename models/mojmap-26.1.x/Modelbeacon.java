// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelbeacon<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "beacon"), "main");
	private final ModelPart model;

	public Modelbeacon(ModelPart root) {
		this.model = root.getChild("model");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Y-flipped for Minecraft model space: y' = -(bb_y + height) (Blockbench y
		// grows up, MC y grows down)
		PartDefinition model = partdefinition.addOrReplaceChild("model",
				CubeListBuilder.create().texOffs(0, 67)
						.addBox(-13.0F, -8.0F, -13.0F, 26.0F, 8.0F, 26.0F, new CubeDeformation(0.0F)).texOffs(80, 0)
						.addBox(-12.0F, -17.0F, -12.0F, 24.0F, 9.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(0, 101)
						.addBox(-11.0F, -32.0F, -11.0F, 22.0F, 15.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-10.0F, -79.0F, -10.0F, 20.0F, 47.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(80, 33)
						.addBox(-8.0F, -81.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		model.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}