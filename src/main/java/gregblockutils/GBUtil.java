package gregblockutils;

import gregtech.api.recipes.ModHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static gregblockutils.jei.JEIGBPlugin.removedItems;

public class GBUtil {
    public static void removeItem(ItemStack item) {
        ModHandler.removeRecipes(item);
        removedItems.add(item);
    }

    @Nullable
    public static IRecipe getRecipeByResourceLocation(@Nonnull ResourceLocation location) {
        if (ForgeRegistries.RECIPES.containsKey(location)) {
            return ForgeRegistries.RECIPES.getValue(location);
        }
        return null;
    }
}
