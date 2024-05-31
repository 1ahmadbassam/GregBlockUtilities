package gregblockutils.exnihilo;

import gregblockutils.GBUtil;
import gregicadditions.recipes.CokeOvenRecipeBuilder;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;
import net.blay09.mods.excompressum.block.BlockBait;
import net.blay09.mods.excompressum.block.ModBlocks;
import net.blay09.mods.excompressum.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.List;

import static gregblockutils.GBUtil.removeItem;
import static gregblockutils.jei.JEIGBPlugin.disabledCategories;
import static gregblockutils.jei.JEIGBPlugin.recipeCatalysts;

public class ExCompressumIntegration {
    protected static final List<ResourceLocation> removedRecipes = new ArrayList<>();
    public static void init() {
        for (int i = 0; i <= 5; i++) {
            removeItem(new ItemStack(ModBlocks.heavySieve, 1, i));
            if (i > 0)
                recipeCatalysts.add(new Tuple<>(new ItemStack(ModBlocks.woodenCrucible, 1, i), "excompressum:woodenCrucible"));
        }
        removeItem(new ItemStack(ModItems.ironMesh));
        removeItem(new ItemStack(ModBlocks.autoCompressedHammer));
        removeItem(new ItemStack(ModBlocks.autoHammer));
        removeItem(new ItemStack(ModBlocks.autoSieve));
        removeItem(new ItemStack(ModBlocks.autoCompressorRationing));
        removeItem(new ItemStack(ModBlocks.autoCompressor));
        removeItem(new ItemStack(ModBlocks.autoHeavySieve));
        removeItem(new ItemStack(ModItems.uglySteelPlating));
        removeItem(new ItemStack(ModItems.chickenStick));
        removeItem(new ItemStack(ModItems.chickenStick));
        removeItem(new ItemStack(ModItems.compressedHammerWood));
        removeItem(new ItemStack(ModItems.compressedHammerStone));
        removeItem(new ItemStack(ModItems.compressedHammerIron));
        removeItem(new ItemStack(ModItems.compressedHammerGold));
        removeItem(new ItemStack(ModItems.compressedHammerDiamond));
        removeItem(new ItemStack(ModItems.doubleCompressedDiamondHammer));
        removeItem(new ItemStack(ModItems.batZapper));
        removeItem(new ItemStack(ModItems.oreSmasher));
        disabledCategories.add("excompressum:chickenStick");

        // Bait -> Packager
        for (BlockBait.Type bait : BlockBait.Type.values()) {
            ResourceLocation recipeLocation;
            if (bait == BlockBait.Type.SHEEP)
                recipeLocation = new ResourceLocation("excompressum:bait_sheep_without_grass_seeds");
            else
                recipeLocation = new ResourceLocation("excompressum:bait_" + bait.getName());
            IRecipe recipe = GBUtil.getRecipeByResourceLocation(recipeLocation);
            if (recipe != null) {
                removedRecipes.add(recipeLocation);
                SimpleRecipeBuilder builder = RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(16).duration(80).fluidInputs(Materials.Glowstone.getFluid(144), Materials.Redstone.getFluid(144));
                for (Ingredient i : recipe.getIngredients())
                    builder.inputs(new CountableIngredient(i, 1));
                builder.outputs(recipe.getRecipeOutput()).notConsumable(new IntCircuitIngredient(12)).buildAndRegister();
            }
        }
        for (ResourceLocation l : removedRecipes)
            ModHandler.removeRecipeByName(l);
        removedRecipes.clear();

        CokeOvenRecipeBuilder.start().duration(240).input(new ItemStack(ModItems.uncompressedCoal)).output(new ItemStack(ExNihiloItemRegistry.uncompressedCoke)).fluidOutput(Materials.Creosote.getFluid(62)).buildAndRegister();
        CokeOvenRecipeBuilder.start().duration(240).input(new ItemStack(ExNihiloItemRegistry.uncompressedLigniteCoal)).output(new ItemStack(ExNihiloItemRegistry.uncompressedLigniteCoke)).fluidOutput(Materials.Creosote.getFluid(62)).buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().EUt(32).duration(30).input(ModItems.uncompressedCoal, 16).circuitMeta(0).output(ExNihiloItemRegistry.uncompressedCoke, 20).fluidOutputs(Materials.Creosote.getFluid(1250)).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().EUt(32).duration(30).input(ExNihiloItemRegistry.uncompressedLigniteCoal, 16).circuitMeta(0).output(ExNihiloItemRegistry.uncompressedLigniteCoke, 20).fluidOutputs(Materials.Creosote.getFluid(1250)).buildAndRegister();
    }
}
