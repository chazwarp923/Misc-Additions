/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPresent extends ModelBase {
    ModelRenderer Box;
    ModelRenderer Lid;
    ModelRenderer Bow_Part_1;
    ModelRenderer Bow_Part_2;
    ModelRenderer Bow_Part_3;
  
  public ModelPresent() {
	  textureWidth = 64;
	  textureHeight = 64;
    
      Box = new ModelRenderer(this, 0, 17);
      Box.addBox(-6F, -5F, -6F, 12, 10, 12);
      Box.setRotationPoint(0F, 19F, 0F);
      Box.setTextureSize(64, 64);
      Box.mirror = true;
      setRotation(Box, 0F, 0F, 0F);
      Lid = new ModelRenderer(this, 0, 0);
      Lid.addBox(-7F, -1.5F, -7F, 14, 3, 14);
      Lid.setRotationPoint(0F, 13F, 0F);
      Lid.setTextureSize(64, 64);
      Lid.mirror = true;
      setRotation(Lid, 0F, 0F, 0F);
      Bow_Part_1 = new ModelRenderer(this, 0, 0);
      Bow_Part_1.addBox(-1F, -0.5F, -1F, 2, 1, 2);
      Bow_Part_1.setRotationPoint(0F, 11F, 0F);
      Bow_Part_1.setTextureSize(64, 64);
      Bow_Part_1.mirror = true;
      setRotation(Bow_Part_1, 0F, 0F, 0F);
      Bow_Part_2 = new ModelRenderer(this, 0, 0);
      Bow_Part_2.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
      Bow_Part_2.setRotationPoint(-1.5F, 10F, 0F);
      Bow_Part_2.setTextureSize(64, 64);
      Bow_Part_2.mirror = true;
      setRotation(Bow_Part_2, 0F, 0F, 0F);
      Bow_Part_3 = new ModelRenderer(this, 0, 0);
      Bow_Part_3.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
      Bow_Part_3.setRotationPoint(1.5F, 10F, 0F);
      Bow_Part_3.setTextureSize(64, 64);
      Bow_Part_3.mirror = true;
      setRotation(Bow_Part_3, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  Box.render(f5);
	  Lid.render(f5);
	  Bow_Part_1.render(f5);
	  Bow_Part_2.render(f5);
	  Bow_Part_3.render(f5);
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
