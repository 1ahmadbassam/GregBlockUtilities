package gregblockutils.Recipes;

import binnie.extrabees.genetics.ExtraBeeDefinition;
import exnihilocreatio.ModItems;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.core.fluids.Fluids;
import gregblockutils.GregBlockUtils;
import gregblockutils.Items.GBItems;
import gregblockutils.Items.GBPebble;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockGranite;
import gregtech.common.blocks.BlockMineral;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class GBRecipeAddition {
    public static void postInit() {
        List<ItemStack> dirtItems = OreDictionary.getOres("dirt", true);
        dirtItems.removeIf(cur -> cur.getMetadata() == OreDictionary.WILDCARD_VALUE);

        Set<String> dirtUnlocNames = new HashSet<>();
        for (ItemStack dirt : dirtItems) {
            dirtUnlocNames.add(dirt.getUnlocalizedName());
        }
        Map<String, ArrayList<Integer>> registeredRecipes = new HashMap<>();
        for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
            ItemStack stack = recipe.getSievables().get(0);
            if (dirtUnlocNames.contains(stack.getUnlocalizedName())) continue;
            if (registeredRecipes.containsKey(stack.getUnlocalizedName()) && registeredRecipes.get(stack.getUnlocalizedName()).contains(recipe.getMesh().getMetadata())) continue;
            SimpleRecipeBuilder builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
            builder.notConsumable(recipe.getMesh()).inputs(stack);
            for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                if (siftable.getMeshLevel() == recipe.getMesh().getMetadata() && siftable.getDrop() != null)
                    builder.chancedOutput(siftable.getDrop().getItemStack(), (int) (siftable.getChance() * (float) Recipe.getMaxChancedValue()), 1000);
            }
            builder.duration(100).EUt(4);
            builder.buildAndRegister();
            if (!registeredRecipes.containsKey(stack.getUnlocalizedName())) registeredRecipes.put(stack.getUnlocalizedName(), new ArrayList<>());
            registeredRecipes.get(stack.getUnlocalizedName()).add(recipe.getMesh().getMetadata());
        }
        List<Siftable> dirtSiftables = ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(new ItemStack(Blocks.DIRT, 1, 0));
        dirtSiftables.removeIf(cur -> cur.getDrop() != null && cur.getDrop().getItemStack().getUnlocalizedName().contains("pebble"));
        for (ItemStack dirtItem : dirtItems) {
            SimpleRecipeBuilder builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
            builder.notConsumable(new ItemStack(ModItems.mesh, 1, 1)).inputs(dirtItem);
            for (Siftable siftable : dirtSiftables)
                if (siftable.getDrop() != null)
                    builder.chancedOutput(siftable.getDrop().getItemStack(), (int) (siftable.getChance() * (float) Recipe.getMaxChancedValue()), 1000);
            builder.duration(100).EUt(4);
            builder.buildAndRegister();
        }
        {
            for(ItemStack compressedDirt : OreDictionary.getOres("compressed1xDirt")) {
                SimpleRecipeBuilder builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
                builder.notConsumable(new ItemStack(ModItems.mesh, 1, 1)).inputs(compressedDirt);
                for (int i = 0; i < 4; i++) {
                    builder.chancedOutput(new ItemStack(ModItems.pebbles, 6, i), (int) (0.35 * (float) Recipe.getMaxChancedValue()), 1000);
                }
                builder.duration(100).EUt(4);
                builder.buildAndRegister();
                builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
                builder.notConsumable(new ItemStack(ModItems.mesh, 1, 2)).inputs(compressedDirt);
                for (int i = 0; i < 4; i++) {
                    builder.chancedOutput(new ItemStack(GBItems.pebble, 2, i), (int) (0.3 * (float) Recipe.getMaxChancedValue()), 1000);
                }
                builder.duration(100).EUt(4);
                builder.buildAndRegister();
            }
        }
        {
            SimpleRecipeBuilder builder = GBRecipeMaps.SIEVE_RECIPES.recipeBuilder();
            builder.notConsumable(new ItemStack(ModItems.mesh, 1, 1)).inputs(new ItemStack(Blocks.DIRT, 1, 1));
            builder.chancedOutput(new ItemStack(Blocks.DIRT, 2, 0), (int) (0.6 * (float) Recipe.getMaxChancedValue()), 1000);
            builder.chancedOutput(new ItemStack(Blocks.GRAVEL, 1, 0), (int) (0.4 * (float) Recipe.getMaxChancedValue()), 1000);
            builder.duration(100).EUt(4);
            builder.buildAndRegister();
        }

        ModHandler.addShapedRecipe("pebbles_to_basalt", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("basalt"));
        ModHandler.addShapedRecipe("pebbles_to_black_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("black_granite"));
        ModHandler.addShapedRecipe("pebbles_to_marble", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("marble"));
        ModHandler.addShapedRecipe("pebbles_to_red_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', GBPebble.getPebbleStack("red_granite"));

        ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.NORMAL));

        //Bees
        List<ItemStack> allFlowers = OreDictionary.getOres("flower").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

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

        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {
                ModHandler.addShapedRecipe("chunk_to_crushed_" + mat.toString(), OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreChunk"), mat));
                ModHandler.addShapedRecipe("ender_chunk_to_crushed_" + mat.toString(), OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreEnderChunk"), mat));
                ModHandler.addShapedRecipe("nether_chunk_to_crushed_" + mat.toString(), OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreNetherChunk"), mat));
                ModHandler.addShapedRecipe("sandy_chunk_to_crushed_" + mat.toString(), OreDictUnifier.get(OrePrefix.crushed, mat, ((DustMaterial) mat).oreMultiplier), "h", "O", 'O', new UnificationEntry(OrePrefix.valueOf("oreSandyChunk"), mat));
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
