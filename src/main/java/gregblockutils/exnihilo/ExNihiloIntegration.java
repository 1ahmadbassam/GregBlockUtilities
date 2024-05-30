package gregblockutils.exnihilo;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModItems;
import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.compatibility.jei.barrel.fluiditemtransform.FluidItemTransformRecipe;
import exnihilocreatio.compatibility.jei.hammer.HammerRecipe;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.items.ItemDoll;
import exnihilocreatio.items.ItemResource;
import exnihilocreatio.modules.Forestry;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import gregblockutils.recipes.GBRecipeMaps;
import gregicadditions.GAMaterials;
import gregicadditions.GAUtils;
import gregicadditions.item.GAMetaItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemAndMetadata;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import org.apache.commons.lang3.tuple.MutablePair;
import slimeknights.tconstruct.shared.TinkerFluids;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregblockutils.GBUtil.removeItem;
import static gregblockutils.jei.JEIGBPlugin.recipeCatalysts;

public class ExNihiloIntegration {
    public static void init() {
        removeItem(new ItemStack(ModItems.hammerWood));
        removeItem(new ItemStack(ModItems.hammerIron));
        removeItem(new ItemStack(ModItems.hammerGold));
        removeItem(new ItemStack(ModItems.hammerDiamond));
        removeItem(new ItemStack(ModBlocks.endCake));
        removeItem(new ItemStack(ModBlocks.crucibleWood));
        removeItem(new ItemStack(ModBlocks.grinder));
        removeItem(new ItemStack(Forestry.EXNIHILO_HIVE));
        removeItem(new ItemStack(Forestry.EXNIHILO_HIVE, 1, 1));

        ModHandler.addShapelessRecipe("gt_stone_plate", OreDictUnifier.get(OrePrefix.plate, Materials.Stone), 's', "smoothStone");
        ModHandler.removeRecipes(ItemResource.getResourceStack("rod_stone", 2));
        ModHandler.removeRecipes(ItemResource.getResourceStack("gear_stone"));
        ModHandler.addShapedRecipe("gt_stone_gear", OreDictUnifier.get(OrePrefix.gear, Materials.Stone), "RPR", "PfP", "RPR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Stone), 'P', new UnificationEntry(OrePrefix.plate, Materials.Stone));
        ModHandler.removeRecipes(ItemResource.getResourceStack("porcelain_clay"));

        //Forced to use GT Hammers or Tinker's ones
        ModHandler.removeRecipes(ModItems.hammerStone);
        ModHandler.addShapedRecipe("exnihilo_hammer_stone", new ItemStack(ModItems.hammerStone), "SS ", "SSI", "SS ", 'S', "cobblestone", 'I', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        recipeCatalysts.add(new Tuple<>(MetaItems.HARD_HAMMER.getStackForm(), "exnihilocreatio:hammer"));

        ModHandler.removeRecipes(new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.FLINT.getID()));
        ModHandler.addShapedRecipe("mesh_tin_alloy", new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.FLINT.getID()), "TST", "STS", "TST", 'T', new UnificationEntry(OrePrefix.stick, Materials.TinAlloy), 'S', "string");
        ModHandler.removeRecipes(new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.IRON.getID()));
        ModHandler.addShapedRecipe("mesh_steel", new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.IRON.getID()), "TST", "STS", "TST", 'T', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'S', "string");
        ModHandler.removeRecipes(new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.DIAMOND.getID()));
        ModHandler.addShapedRecipe("mesh_aluminium", new ItemStack(ModItems.mesh, 1, BlockSieve.MeshType.DIAMOND.getID()), "TST", "STS", "TST", 'T', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'S', "string");

        ModHandler.removeRecipes(ItemResource.getResourceStack("doll", 4));
        ModHandler.removeRecipes(ItemResource.getResourceStack("doll", 6));
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(64).duration(250).input(OrePrefix.plate, GAMaterials.Porcelain, 4).input(OrePrefix.gem, Materials.Diamond).outputs(ItemResource.getResourceStack("doll", 4)).buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().EUt(64).duration(325).input(OrePrefix.plate, GAMaterials.Porcelain, 6).input(OrePrefix.gem, Materials.Emerald).outputs(ItemResource.getResourceStack("doll", 6)).buildAndRegister();

        ModHandler.removeRecipes(ModItems.dolls);
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(64).duration(62).inputs(ItemResource.getResourceStack("doll"), new ItemStack(Items.NETHER_WART)).input(OrePrefix.dust, Materials.Blaze, 4).fluidInputs(Materials.Redstone.getFluid(144), Materials.Glowstone.getFluid(288)).outputs(new ItemStack(ModItems.dolls, 1, ItemDoll.DollType.BLAZE.meta)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(64).duration(62).inputs(ItemResource.getResourceStack("doll"), new ItemStack(Items.NETHER_WART)).input(OrePrefix.dye, MarkerMaterials.Color.Black, 4).fluidInputs(Materials.Redstone.getFluid(144), Materials.Glowstone.getFluid(288)).outputs(new ItemStack(ModItems.dolls, 1, ItemDoll.DollType.ENDERMAN.meta)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(64).duration(62).inputs(ItemResource.getResourceStack("doll")).input(OrePrefix.gem, Materials.EnderPearl).input(OrePrefix.dye, MarkerMaterials.Color.Purple, 4).fluidInputs(Materials.Deuterium.getFluid(1000), Materials.Glowstone.getFluid(576)).outputs(new ItemStack(ModItems.dolls, 1, ItemDoll.DollType.SHULKER.meta)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(64).duration(62).inputs(ItemResource.getResourceStack("doll")).input("fish", 1).input("gemPrismarine", 4).fluidInputs(Materials.Redstone.getFluid(144), Materials.Glowstone.getFluid(288)).outputs(new ItemStack(ModItems.dolls, 1, ItemDoll.DollType.GUARDIAN.meta)).buildAndRegister();
        for (MaterialStack lapis : GAUtils.lapisLike)
            RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(64).duration(62).inputs(ItemResource.getResourceStack("doll")).input(OrePrefix.gem, lapis.material, 3).fluidInputs(new FluidStack(TinkerFluids.blueslime, 1000), Materials.Water.getFluid(1000)).outputs(new ItemStack(ModItems.dolls, 1, ItemDoll.DollType.BLUESLIME.meta)).buildAndRegister();

        ModHandler.removeRecipes(new ItemStack(ModBlocks.autoSifter));
        ModHandler.addShapedRecipe("exnihilo_sifter", new ItemStack(ModBlocks.autoSifter), "GHG", "wPA", "GHG", 'G', new UnificationEntry(OrePrefix.gear, Materials.Stone), 'H', "hopper", 'P', Blocks.STICKY_PISTON, 'A', ModBlocks.axle_stone);

        ModHandler.removeRecipes(new ItemStack(ModBlocks.crucibleStone));
        ModHandler.addShapedRecipe("exnihilo_unfired_porcelain_crucible", new ItemStack(ModBlocks.crucibleStone), "P P", "PhP", "PCP", 'P', GAMetaItems.UNFIRED_PORCELAIN_PLATE, 'C', new UnificationEntry(OrePrefix.valueOf("clay"), GAMaterials.Porcelain));
    }

    public static void postInit() {
        //Ex Nihilo Sieve -> GTCE Sieve
        for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
            for (ItemStack stack : recipe.getSievables()) {
                SimpleRecipeBuilder builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
                builder.notConsumable(recipe.getMesh()).inputs(stack);
                Map<ItemAndMetadata, MutablePair<Integer, Double>> itemAndChance = new HashMap<>();
                for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                    if (siftable.getMeshLevel() == recipe.getMesh().getMetadata() && siftable.getDrop() != null) {
                        ItemAndMetadata item = new ItemAndMetadata(siftable.getDrop().getItemStack());
                        if (itemAndChance.containsKey(item)) {
                            itemAndChance.get(item).left += 1;
                            itemAndChance.get(item).right *= siftable.getChance();
                        } else
                            itemAndChance.put(new ItemAndMetadata(siftable.getDrop().getItemStack()), new MutablePair<>(1, (double) siftable.getChance()));
                    }
                }
                if (!itemAndChance.isEmpty() && GBRecipeMaps.SIEVE_RECIPES.findRecipe(Integer.MAX_VALUE, Arrays.asList(stack, recipe.getMesh()), Collections.emptyList(), Integer.MAX_VALUE) == null) {
                    if (itemAndChance.size() > GBRecipeMaps.SIEVE_RECIPES.getMaxOutputs())
                        if (stack.getItem() instanceof ItemBlock)
                            throw new IllegalStateException("An Ex Nihilo Sieve recipe contains too many outputs. Tier: " + recipe.getMesh().getMetadata() + ". Input: " + Block.getBlockFromItem(stack.getItem()) + ". Actual: " + itemAndChance.size() + ". Expected <=" + GBRecipeMaps.SIEVE_RECIPES.getMaxOutputs() + ".");
                        else
                            throw new IllegalStateException("An Ex Nihilo Sieve recipe contains too many outputs. Tier: " + recipe.getMesh().getMetadata() + ". Input: " + stack.getItem() + ". Actual: " + itemAndChance.size() + ". Expected <=" + GBRecipeMaps.SIEVE_RECIPES.getMaxOutputs() + ".");
                    for (Map.Entry<ItemAndMetadata, MutablePair<Integer, Double>> entry : itemAndChance.entrySet()) {
                        builder.chancedOutput(new ItemStack(entry.getKey().item, entry.getValue().left, entry.getKey().itemDamage), (int) (Math.pow(entry.getValue().right, 1.0 / entry.getValue().left) * (float) Recipe.getMaxChancedValue()), 500);
                    }
                    builder.duration(100).EUt(8);
                    builder.buildAndRegister();
                }
            }
        }
        //Ex Nihilo Fluid+Item -> Chemical Bath
        for (FluidItemTransformRecipe recipe : Stream.of(ExNihiloRegistryManager.FLUID_BLOCK_TRANSFORMER_REGISTRY.getRecipeList(), ExNihiloRegistryManager.FLUID_ITEM_FLUID_REGISTRY.getRecipeList()).flatMap(Collection::stream).collect(Collectors.toList())) {
            if (!recipe.getFluidInputs().isEmpty() && !recipe.getInputs().isEmpty() && !recipe.getInputs().get(1).isEmpty()) {
                for (ItemStack in : recipe.getInputs().get(1))
                    if (RecipeMaps.CHEMICAL_BATH_RECIPES.findRecipe(Integer.MAX_VALUE, Collections.singletonList(in), recipe.getFluidInputs(), Integer.MAX_VALUE) == null)
                        if (ItemStack.areItemStacksEqual(in, new ItemStack(Blocks.BROWN_MUSHROOM)) || ItemStack.areItemStacksEqual(in, new ItemStack(Blocks.RED_MUSHROOM)))
                            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(2).duration(20).inputs(in).fluidInputs(recipe.getFluidInputs()).outputs(recipe.getOutput()).chancedOutput(new ItemStack(Items.SLIME_BALL), (int) (0.2 * Recipe.getMaxChancedValue()), 500).buildAndRegister();
                        else if (FluidUtil.getFluidContained(recipe.getOutput()) != null)
                            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(2).duration(20).inputs(in).fluidInputs(recipe.getFluidInputs()).fluidOutputs(new FluidStack(Objects.requireNonNull(FluidUtil.getFluidContained(recipe.getOutput())), 1000)).buildAndRegister();
                        else
                            RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(2).duration(20).inputs(in).fluidInputs(recipe.getFluidInputs()).outputs(recipe.getOutput()).buildAndRegister();
            }
        }
        //Ex Nihilo Hammer -> Forge Hammer
        RecipeMaps.FORGE_HAMMER_RECIPES.removeRecipe(RecipeMaps.FORGE_HAMMER_RECIPES.findRecipe(Integer.MAX_VALUE, Collections.singletonList(new ItemStack(Blocks.COBBLESTONE)), Collections.emptyList(), Integer.MAX_VALUE));
        for (HammerRecipe recipe : ExNihiloRegistryManager.HAMMER_REGISTRY.getRecipeList()) {
            if (!recipe.getInputs().isEmpty() && !recipe.getOutputs().isEmpty()) {
                for (ItemStack in : recipe.getInputs())
                    if (RecipeMaps.FORGE_HAMMER_RECIPES.findRecipe(Integer.MAX_VALUE, Collections.singletonList(in), Collections.emptyList(), Integer.MAX_VALUE) == null)
                        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(16).duration(10).inputs(in).outputs(recipe.getOutputs().get(0)).buildAndRegister();
            }
        }
    }
}
