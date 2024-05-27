package gregblockutils.recipes;

import binnie.extrabees.genetics.ExtraBeeDefinition;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.core.fluids.Fluids;
import gregblockutils.items.GBPebble;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemAndMetadata;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockGranite;
import gregtech.common.blocks.BlockMineral;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.common.items.MetaItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.*;
import java.util.stream.Collectors;

public class GBRecipeAddition {
    public static void init() {
        ModHandler.addShapedRecipe("pebbles_to_basalt", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("basalt"));
        ModHandler.addShapedRecipe("pebbles_to_black_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("black_granite"));
        ModHandler.addShapedRecipe("pebbles_to_marble", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("marble"));
        ModHandler.addShapedRecipe("pebbles_to_red_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("red_granite"));

        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(200).inputs(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED)).outputs(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.NORMAL)).buildAndRegister();
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(200).inputs(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED)).outputs(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.NORMAL)).buildAndRegister();
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(200).inputs(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED)).outputs(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.NORMAL)).buildAndRegister();
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(200).inputs(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED)).outputs(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.NORMAL)).buildAndRegister();
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
                        builder.chancedOutput(new ItemStack(entry.getKey().item, entry.getValue().left, entry.getKey().itemDamage), (int) (Math.pow(entry.getValue().right, 1.0 / entry.getValue().left) * (float) Recipe.getMaxChancedValue()), 1000);
                    }
                    builder.duration(100).EUt(8);
                    builder.buildAndRegister();
                }
            }
        }

        //Bees
        List<ItemStack> allFlowers = OreDictionary.getOres("flower").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        if (BeeManager.beeRoot != null) {
            for (ItemStack stack : allFlowers)
                GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.FOREST.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.FOREST.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MEADOWS.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MEADOWS.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                        .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                        .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.SNOW)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.WINTRY.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.WINTRY.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Items.CHORUS_FRUIT)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.ENDED.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.ENDED.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MODEST.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MODEST.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.VINE)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.TROPICAL.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.TROPICAL.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.WATERLILY)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.WATER.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.WATER.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(OreDictUnifier.get(OrePrefix.stone, null)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.ROCK.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.ROCK.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Items.NETHER_WART)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.BASALT.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.BASALT.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();

            GBRecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(MetaItems.COIN_GOLD_ANCIENT.getStackForm()).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MONASTIC.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MONASTIC.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.STEADFAST.getIndividual(), EnumBeeType.PRINCESS), 1000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.STEADFAST.getIndividual(), EnumBeeType.DRONE), 3000, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 1000)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 1000)
                    .EUt(26).duration(200).buildAndRegister();
        }
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {
                ModHandler.addShapedRecipe("chunk_to_crushed_" + mat, OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreChunk"), mat));
                ModHandler.addShapedRecipe("ender_chunk_to_crushed_" + mat, OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreEnderChunk"), mat));
                ModHandler.addShapedRecipe("nether_chunk_to_crushed_" + mat, OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreNetherChunk"), mat));
                ModHandler.addShapedRecipe("sandy_chunk_to_crushed_" + mat, OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreSandyChunk"), mat));
            }
        }
    }

    public static void register() {
        OrePrefix.valueOf("oreChunk").addProcessingHandler(DustMaterial.class, GBRecipeAddition::processOre);
        OrePrefix.valueOf("oreEnderChunk").addProcessingHandler(DustMaterial.class, GBRecipeAddition::processOre);
        OrePrefix.valueOf("oreNetherChunk").addProcessingHandler(DustMaterial.class, GBRecipeAddition::processOre);
        OrePrefix.valueOf("oreSandyChunk").addProcessingHandler(DustMaterial.class, GBRecipeAddition::processOre);
    }

    public static void processOre(OrePrefix orePrefix, DustMaterial material) {
        ItemStack ingotStack = null;
        DustMaterial smeltingMaterial = material;
        if (material.directSmelting != null) {
            smeltingMaterial = material.directSmelting;
        }
        if (smeltingMaterial instanceof IngotMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        else if (smeltingMaterial instanceof GemMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);

        if (ingotStack != null) {
            ingotStack.setCount(material.oreMultiplier);

            if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(material)) {
                ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
            }
        }
    }

    private static boolean doesMaterialUseNormalFurnace(Material material) {
        return !(material instanceof IngotMaterial) ||
                ((IngotMaterial) material).blastFurnaceTemperature <= 0;
    }
}
