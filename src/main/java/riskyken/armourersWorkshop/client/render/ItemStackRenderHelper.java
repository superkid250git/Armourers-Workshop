
package riskyken.armourersWorkshop.client.render;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;

import riskyken.armourersWorkshop.api.common.equipment.skin.ISkinType;
import riskyken.armourersWorkshop.api.common.lib.LibCommonTags;
import riskyken.armourersWorkshop.client.equipment.ClientEquipmentModelCache;
import riskyken.armourersWorkshop.client.model.equipmet.IEquipmentModel;
import riskyken.armourersWorkshop.common.equipment.data.CustomEquipmentItemData;
import riskyken.armourersWorkshop.common.equipment.skin.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.handler.EquipmentDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Helps render item stacks.
 * 
 * @author RiskyKen
 *
 */

@SideOnly(Side.CLIENT)
public final class ItemStackRenderHelper {

    public static void renderItemAsArmourModel(ItemStack stack) {
        ISkinType skinType =  EquipmentDataHandler.INSTANCE.getSkinTypeFromStack(stack);
        renderItemAsArmourModel(stack, skinType);
    }
    
    public static void renderItemAsArmourModel(ItemStack stack, ISkinType skinType) {
        NBTTagCompound armourNBT = stack.getTagCompound().getCompoundTag(LibCommonTags.TAG_ARMOUR_DATA);
        int equipmentId = armourNBT.getInteger(LibCommonTags.TAG_EQUIPMENT_ID);
        
        renderItemModelFromId(equipmentId, skinType);
    }
    
    public static void renderItemModelFromId(int equipmentId, ISkinType skinType) {
        IEquipmentModel targetModel = EquipmentModelRenderer.INSTANCE.getModelForEquipmentType(skinType);
        if (targetModel == null) {
            return;
        }
        
        CustomEquipmentItemData data = ClientEquipmentModelCache.INSTANCE.getEquipmentItemData(equipmentId);
        if (data == null) {
            return;
        }
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        
        if (skinType == SkinTypeRegistry.skinHead) {
            GL11.glTranslatef(0F, 0.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinChest) {
            GL11.glTranslatef(0F, -0.35F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinLegs) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinSkirt) {
            GL11.glTranslatef(0F, -1.0F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinFeet) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinSword) {
            targetModel.render(null, null, data);
        } else if (skinType == SkinTypeRegistry.skinBow) {
            targetModel.render(null, null, data);
        }
        
        GL11.glDisable(GL11.GL_BLEND);
    }
}
