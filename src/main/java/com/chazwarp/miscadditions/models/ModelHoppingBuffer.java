/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHoppingBuffer extends ModelBase {
	// fields
	ModelRenderer Box;
	ModelRenderer Chute;
	ModelRenderer Ring1;
	ModelRenderer Ring2;
	ModelRenderer Ring3;
	ModelRenderer Ring4;

	public ModelHoppingBuffer() {
		textureWidth = 64;
		textureHeight = 64;

		Box = new ModelRenderer(this, 0, 0);
		Box.addBox(-8F, -5F, -8F, 16, 10, 16);
		Box.setRotationPoint(0F, 19F, 0F);
		Box.setTextureSize(64, 64);
		Box.mirror = true;
		setRotation(Box, 0F, 0F, 0F);
		Chute = new ModelRenderer(this, 0, 42);
		Chute.addBox(-4F, -2F, -4F, 8, 3, 8);
		Chute.setRotationPoint(0F, 13F, 0F);
		Chute.setTextureSize(64, 64);
		Chute.mirror = true;
		setRotation(Chute, 0F, 0F, 0F);
		Ring1 = new ModelRenderer(this, 0, 26);
		Ring1.addBox(-5F, -2F, -1F, 10, 3, 2);
		Ring1.setRotationPoint(-1F, 10F, 5F);
		Ring1.setTextureSize(64, 64);
		Ring1.mirror = true;
		setRotation(Ring1, 0F, 0F, 0F);
		Ring2 = new ModelRenderer(this, 24, 26);
		Ring2.addBox(-1F, -2F, -5F, 2, 3, 10);
		Ring2.setRotationPoint(-5F, 10F, -1F);
		Ring2.setTextureSize(64, 64);
		Ring2.mirror = true;
		setRotation(Ring2, 0F, 0F, 0F);
		Ring3 = new ModelRenderer(this, 24, 26);
		Ring3.addBox(-1F, -2F, -5F, 2, 3, 10);
		Ring3.setRotationPoint(5F, 10F, 1F);
		Ring3.setTextureSize(64, 64);
		Ring3.mirror = true;
		setRotation(Ring3, 0F, 0F, 0F);
		Ring4 = new ModelRenderer(this, 0, 26);
		Ring4.addBox(-5F, -2F, -1F, 10, 3, 2);
		Ring4.setRotationPoint(1F, 10F, -5F);
		Ring4.setTextureSize(64, 64);
		Ring4.mirror = true;
		setRotation(Ring4, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Box.render(f5);
		Chute.render(f5);
		Ring1.render(f5);
		Ring2.render(f5);
		Ring3.render(f5);
		Ring4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
