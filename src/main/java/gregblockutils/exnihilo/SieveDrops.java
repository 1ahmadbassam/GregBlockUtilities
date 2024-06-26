package gregblockutils.exnihilo;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.util.ItemInfo;
import gregsconstruct.common.GCMaterials;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class SieveDrops {
    public final String input;
    public final Material material;
    public final float chance;
    public final int level;

    public SieveDrops(String input, Material material, float chance, int level) {
        this.input = input;
        this.material = material;
        this.chance = chance;
        this.level = level;
    }

    protected static final ArrayList<SieveDrops> drops = new ArrayList<>();


    public static void addSieveRecipe() {
        //Constants
        String sand = "sand";
        String gravel = "gravel";
        String rack = "nether";
        String end = "end";
        String granite = "crushedGranite";
        String diorite = "crushedDiorite";
        String andesite = "crushedAndesite";

        //Overworld
        addRecipe(andesite, Materials.Redstone, 0.1938, 2);
        addRecipe(andesite, Materials.Ruby, 0.0646, 2);
        addRecipe(andesite, Materials.Cinnabar, 0.0646, 2);
        addRecipe(andesite, Materials.Chromite, 0.01615, 2);
        addRecipe(andesite, Materials.Almandine, 0.0816, 2);
        addRecipe(andesite, Materials.Pyrope, 0.0816, 2);
        addRecipe(andesite, Materials.Sapphire, 0.0816, 2);
        addRecipe(andesite, Materials.GreenSapphire, 0.0272, 2);
        addRecipe(andesite, Materials.Topaz, 0.06, 2);
        addRecipe(andesite, Materials.BlueTopaz, 0.04, 2);
        addRecipe(andesite, Materials.Chalcopyrite, 0.0969, 3);
        addRecipe(andesite, Materials.Iron, 0.0969, 3);
        addRecipe(andesite, Materials.Pyrite, 0.0969, 3);
        addRecipe(andesite, Materials.Copper, 0.0323, 3);
        addRecipe(andesite, Materials.BrownLimonite, 0.0507, 3);
        addRecipe(andesite, Materials.YellowLimonite, 0.0507, 3);
        addRecipe(andesite, Materials.BandedIron, 0.0507, 3);
        addRecipe(andesite, Materials.Malachite, 0.0169, 3);
        addRecipe(andesite, Materials.Bastnasite, 0.1014, 3);
        addRecipe(andesite, Materials.Monazite, 0.0338, 3);
        addRecipe(andesite, Materials.Neodymium, 0.0338, 3);
        addRecipe(andesite, Materials.Garnierite, 0.0546, 3);
        addRecipe(andesite, Materials.Nickel, 0.0546, 3);
        addRecipe(andesite, Materials.Cobaltite, 0.0546, 3);
        addRecipe(andesite, Materials.Pentlandite, 0.0182, 3);
        addRecipe(andesite, Materials.Bentonite, 0.0507, 3);
        addRecipe(andesite, Materials.Magnesite, 0.0507, 3);
        addRecipe(andesite, Materials.Olivine, 0.0507, 3);
        addRecipe(andesite, Materials.Glauconite, 0.0169, 3);
        addRecipe(andesite, Materials.Pitchblende, 0.0845, 3);
        addRecipe(andesite, Materials.Uraninite, 0.0676, 3);
        addRecipe(andesite, Materials.Uranium, 0.0169, 3);
        addRecipe(andesite, Materials.Beryllium, 0.0845, 4);
        addRecipe(andesite, Materials.Emerald, 0.0507, 4);
        addRecipe(andesite, Materials.Thorium, 0.0338, 4);
        addRecipe(andesite, Materials.Graphite, 0.1014, 4);
        addRecipe(andesite, Materials.Diamond, 0.0338, 4);
        addRecipe(andesite, Materials.Coal, 0.0338, 4);
        addRecipe(andesite, Materials.Soapstone, 0.056, 4);
        addRecipe(andesite, Materials.Talc, 0.056, 4);
        addRecipe(andesite, Materials.Glauconite, 0.056, 4);
        addRecipe(andesite, Materials.Pentlandite, 0.056, 4);
        addRecipe(andesite, Materials.Scheelite, 0.1014, 4);
        addRecipe(andesite, Materials.Tungstate, 0.0338, 4);
        addRecipe(andesite, Materials.Lithium, 0.0338, 4);

        addRecipe(diorite, Materials.Bauxite, 0.153, 1);
        addRecipe(diorite, Materials.Aluminium, 0.0765, 1);
        addRecipe(diorite, Materials.Ilmenite, 0.0255, 1);
        addRecipe(diorite, Materials.Chalcopyrite, 0.0585, 1);
        addRecipe(diorite, Materials.Vermiculite, 0.0585, 1);
        addRecipe(diorite, Materials.Cassiterite, 0.0585, 1);
        addRecipe(diorite, Materials.Alunite, 0.0195, 1);
        addRecipe(diorite, Materials.Quartzite, 0.09945, 1);
        addRecipe(diorite, Materials.Barite, 0.05525, 1);
        addRecipe(diorite, Materials.CertusQuartz, 0.0663, 1);
        addRecipe(diorite, Materials.Tennantite, 0.0663, 1);
        addRecipe(diorite, Materials.Tin, 0.24225, 1);
        addRecipe(diorite, Materials.Cassiterite, 0.08075, 1);
        addRecipe(diorite, Materials.Bornite, 0.0969, 2);
        addRecipe(diorite, Materials.Chalcocite, 0.0969, 2);
        addRecipe(diorite, Materials.Enargite, 0.0969, 2);
        addRecipe(diorite, Materials.Copper, 0.0323, 2);
        addRecipe(diorite, Materials.Galena, 0.104, 2);
        addRecipe(diorite, Materials.Lead, 0.052, 2);
        addRecipe(diorite, Materials.Silver, 0.052, 2);
        addRecipe(diorite, Materials.Lazurite, 0.0585, 2);
        addRecipe(diorite, Materials.Sodalite, 0.039, 2);
        addRecipe(diorite, Materials.Lapis, 0.06825, 2);
        addRecipe(diorite, Materials.Calcite, 0.02925, 2);
        addRecipe(diorite, Materials.BrownLimonite, 0.0507, 3);
        addRecipe(diorite, Materials.YellowLimonite, 0.0507, 3);
        addRecipe(diorite, Materials.BandedIron, 0.0507, 3);
        addRecipe(diorite, Materials.Malachite, 0.0169, 3);
        addRecipe(diorite, Materials.Pitchblende, 0.0845, 3);
        addRecipe(diorite, Materials.Uraninite, 0.0676, 3);
        addRecipe(diorite, Materials.Uranium, 0.0169, 3);
        addRecipe(diorite, Materials.Kyanite, 0.0546, 3);
        addRecipe(diorite, Materials.Mica, 0.0546, 3);
        addRecipe(diorite, Materials.Cassiterite, 0.0364, 3);
        addRecipe(diorite, Materials.Pollucite, 0.0364, 3);
        addRecipe(diorite, Materials.Graphite, 0.1014, 4);
        addRecipe(diorite, Materials.Diamond, 0.0338, 4);
        addRecipe(diorite, Materials.Coal, 0.0338, 4);
        addRecipe(diorite, Materials.Soapstone, 0.056, 4);
        addRecipe(diorite, Materials.Talc, 0.056, 4);
        addRecipe(diorite, Materials.Glauconite, 0.056, 4);
        addRecipe(diorite, Materials.Pentlandite, 0.056, 4);

        addRecipe(granite, Materials.Apatite, 0.1014, 1);
        addRecipe(granite, Materials.Phosphor, 0.0676, 1);
        addRecipe(granite, Materials.Bauxite, 0.153, 1);
        addRecipe(granite, Materials.Aluminium, 0.0765, 1);
        addRecipe(granite, Materials.Ilmenite, 0.0255, 1);
        addRecipe(granite, Materials.Magnetite, 0.2505, 1);
        addRecipe(granite, Materials.Iron, 0.0627, 1);
        addRecipe(granite, Materials.VanadiumMagnetite, 0.0627, 1);
        addRecipe(granite, Materials.Gold, 0.0418, 1);
        addRecipe(granite, Materials.Quartzite, 0.09945, 1);
        addRecipe(granite, Materials.Barite, 0.05525, 1);
        addRecipe(granite, Materials.CertusQuartz, 0.0663, 1);
        addRecipe(granite, Materials.Tennantite, 0.0663, 1);
        addRecipe(granite, Materials.Tetrahedrite, 0.1836, 1);
        addRecipe(granite, Materials.Copper, 0.0612, 1);
        addRecipe(granite, Materials.Stibnite, 0.0612, 1);
        addRecipe(granite, Materials.Bornite, 0.0969, 2);
        addRecipe(granite, Materials.Chalcocite, 0.0969, 2);
        addRecipe(granite, Materials.Enargite, 0.0969, 2);
        addRecipe(granite, Materials.Copper, 0.0323, 2);
        addRecipe(granite, Materials.Lazurite, 0.0585, 2);
        addRecipe(granite, Materials.Sodalite, 0.039, 2);
        addRecipe(granite, Materials.Lapis, 0.06825, 2);
        addRecipe(granite, Materials.Calcite, 0.02925, 2);
        addRecipe(granite, Materials.Redstone, 0.1938, 2);
        addRecipe(granite, Materials.Ruby, 0.0646, 2);
        addRecipe(granite, Materials.Cinnabar, 0.0646, 2);
        addRecipe(granite, Materials.Chromite, 0.01615, 2);
        addRecipe(granite, Materials.Topaz, 0.06, 2);
        addRecipe(granite, Materials.BlueTopaz, 0.04, 2);
        addRecipe(granite, Materials.Chalcopyrite, 0.0969, 3);
        addRecipe(granite, Materials.Iron, 0.0969, 3);
        addRecipe(granite, Materials.Pyrite, 0.0969, 3);
        addRecipe(granite, Materials.Copper, 0.0323, 3);
        addRecipe(granite, Materials.BrownLimonite, 0.0507, 3);
        addRecipe(granite, Materials.YellowLimonite, 0.0507, 3);
        addRecipe(granite, Materials.BandedIron, 0.0507, 3);
        addRecipe(granite, Materials.Malachite, 0.0169, 3);
        addRecipe(granite, Materials.Bastnasite, 0.1014, 3);
        addRecipe(granite, Materials.Monazite, 0.0338, 3);
        addRecipe(granite, Materials.Neodymium, 0.0338, 3);
        addRecipe(granite, Materials.Garnierite, 0.0546, 3);
        addRecipe(granite, Materials.Nickel, 0.0546, 3);
        addRecipe(granite, Materials.Cobaltite, 0.0546, 3);
        addRecipe(granite, Materials.Pentlandite, 0.0182, 3);
        addRecipe(granite, Materials.Pitchblende, 0.0845, 3);
        addRecipe(granite, Materials.Uraninite, 0.0676, 3);
        addRecipe(granite, Materials.Uranium, 0.0169, 3);
        addRecipe(granite, Materials.Kyanite, 0.0546, 3);
        addRecipe(granite, Materials.Mica, 0.0546, 3);
        addRecipe(granite, Materials.Cassiterite, 0.0364, 3);
        addRecipe(granite, Materials.Pollucite, 0.0364, 3);
        addRecipe(granite, Materials.Graphite, 0.1014, 4);
        addRecipe(granite, Materials.Diamond, 0.0338, 4);
        addRecipe(granite, Materials.Coal, 0.0338, 4);
        addRecipe(granite, Materials.Wulfenite, 0.0676, 4);
        addRecipe(granite, Materials.Molybdenite, 0.0676, 4);
        addRecipe(granite, Materials.Powellite, 0.0338, 4);
        addRecipe(granite, Materials.Soapstone, 0.056, 4);
        addRecipe(granite, Materials.Talc, 0.056, 4);
        addRecipe(granite, Materials.Glauconite, 0.056, 4);
        addRecipe(granite, Materials.Pentlandite, 0.056, 4);
        addRecipe(granite, Materials.Scheelite, 0.1014, 4);
        addRecipe(granite, Materials.Tungstate, 0.0338, 4);
        addRecipe(granite, Materials.Lithium, 0.0338, 4);

        addRecipe(gravel, Materials.Lignite, 0.064, 1);
        addRecipe(gravel, Materials.Coal, 0.192, 1);
        addRecipe(gravel, Materials.Dolomite, 0.0507, 1);
        addRecipe(gravel, Materials.Wollastonite, 0.0507, 1);
        addRecipe(gravel, Materials.Trona, 0.0507, 1);
        addRecipe(gravel, Materials.Andradite, 0.0169, 1);
        addRecipe(gravel, Materials.Lignite, 0.285, 1);
        addRecipe(gravel, Materials.Coal, 0.095, 1);
        addRecipe(gravel, Materials.Salt, 0.0952, 1);
        addRecipe(gravel, Materials.RockSalt, 0.0833, 1);
        addRecipe(gravel, Materials.Lepidolite, 0.0357, 1);
        addRecipe(gravel, Materials.Spodumene, 0.0238, 1);
        addRecipe(gravel, Materials.Tin, 0.24225, 1);
        addRecipe(gravel, Materials.Cassiterite, 0.08075, 1);
        addRecipe(gravel, Materials.BasalticMineralSand, 0.0546, 1);
        addRecipe(gravel, Materials.GraniticMineralSand, 0.0546, 1);
        addRecipe(gravel, Materials.FullersEarth, 0.0364, 1);
        addRecipe(gravel, Materials.Gypsum, 0.0364, 1);
        addRecipe(gravel, Materials.Galena, 0.104, 2);
        addRecipe(gravel, Materials.Lead, 0.052, 2);
        addRecipe(gravel, Materials.Silver, 0.052, 2);
        addRecipe(gravel, Materials.Kaolinite, 0.0507, 2);
        addRecipe(gravel, Materials.Zeolite, 0.0507, 2);
        addRecipe(gravel, Materials.FullersEarth, 0.0338, 2);
        addRecipe(gravel, Materials.GlauconiteSand, 0.0338, 2);
        addRecipe(gravel, Materials.Oilsands, 0.225, 2);
        addRecipe(gravel, Materials.CassiteriteSand, 0.0432, 2);
        addRecipe(gravel, Materials.GarnetSand, 0.0432, 2);
        addRecipe(gravel, Materials.Asbestos, 0.0432, 2);
        addRecipe(gravel, Materials.Diatomite, 0.0144, 2);
        addRecipe(gravel, Materials.Platinum, 0.07605, 3);
        addRecipe(gravel, Materials.Palladium, 0.05915, 3);
        addRecipe(gravel, Materials.Iridium, 0.0338, 3);

        addRecipe(sand, Materials.Tenorite, 0.1836, 2);
        addRecipe(sand, Materials.Copper, 0.0612, 2);
        addRecipe(sand, Materials.Cuprite, 0.0612, 2);
        addRecipe(sand, Materials.Redstone, 0.1938, 2);
        addRecipe(sand, Materials.Ruby, 0.0646, 2);
        addRecipe(sand, Materials.Cinnabar, 0.0646, 2);
        addRecipe(sand, Materials.Chromite, 0.01615, 2);
        addRecipe(sand, Materials.Almandine, 0.0816, 2);
        addRecipe(sand, Materials.Pyrope, 0.0816, 2);
        addRecipe(sand, Materials.Sapphire, 0.0816, 2);
        addRecipe(sand, Materials.GreenSapphire, 0.0272, 2);
        addRecipe(sand, Materials.Chalcopyrite, 0.0969, 3);
        addRecipe(sand, Materials.Iron, 0.0969, 3);
        addRecipe(sand, Materials.Pyrite, 0.0969, 3);
        addRecipe(sand, Materials.Copper, 0.0323, 3);
        addRecipe(sand, Materials.BrownLimonite, 0.0507, 3);
        addRecipe(sand, Materials.YellowLimonite, 0.0507, 3);
        addRecipe(sand, Materials.BandedIron, 0.0507, 3);
        addRecipe(sand, Materials.Malachite, 0.0169, 3);
        addRecipe(sand, Materials.Garnierite, 0.0546, 3);
        addRecipe(sand, Materials.Nickel, 0.0546, 3);
        addRecipe(sand, Materials.Cobaltite, 0.0546, 3);
        addRecipe(sand, Materials.Pentlandite, 0.0182, 3);
        addRecipe(sand, Materials.Bentonite, 0.0507, 3);
        addRecipe(sand, Materials.Magnesite, 0.0507, 3);
        addRecipe(sand, Materials.Olivine, 0.0507, 3);
        addRecipe(sand, Materials.Glauconite, 0.0169, 3);
        addRecipe(sand, Materials.Beryllium, 0.0845, 4);
        addRecipe(sand, Materials.Emerald, 0.0507, 4);
        addRecipe(sand, Materials.Thorium, 0.0338, 4);
        addRecipe(sand, Materials.Grossular, 0.0507, 4);
        addRecipe(sand, Materials.Spessartine, 0.0507, 4);
        addRecipe(sand, Materials.Pyrolusite, 0.0507, 4);
        addRecipe(sand, Materials.Tantalite, 0.0169, 4);

        //Nether
        addRecipe(rack, Materials.Chalcopyrite, 0.0432, 2);
        addRecipe(rack, Materials.Iron, 0.0432, 2);
        addRecipe(rack, Materials.Pyrite, 0.0432, 2);
        addRecipe(rack, Materials.Copper, 0.0144, 2);
        addRecipe(rack, Materials.BrownLimonite, 0.0324, 2);
        addRecipe(rack, Materials.YellowLimonite, 0.0324, 2);
        addRecipe(rack, Materials.BandedIron, 0.0324, 2);
        addRecipe(rack, Materials.Malachite, 0.0108, 2);
        addRecipe(rack, Materials.Magnetite, 0.1428, 2);
        addRecipe(rack, Materials.Iron, 0.0357, 2);
        addRecipe(rack, Materials.VanadiumMagnetite, 0.0357, 2);
        addRecipe(rack, Materials.Gold, 0.0238, 2);
        addRecipe(rack, Materials.NetherQuartz, 0.324, 2);
        addRecipe(rack, GCMaterials.Ardite, 0.0546, 2);
        addRecipe(rack, Materials.Sulfur, 0.1632, 2);
        addRecipe(rack, Materials.Pyrite, 0.0544, 2);
        addRecipe(rack, Materials.Sphalerite, 0.0544, 2);
        addRecipe(rack, Materials.Tennantite, 0.0816, 2);
        addRecipe(rack, Materials.Tetrahedrite, 0.0936, 2);
        addRecipe(rack, Materials.Copper, 0.0312, 2);
        addRecipe(rack, Materials.Stibnite, 0.0312, 2);
        addRecipe(rack, Materials.Garnierite, 0.0189, 4);
        addRecipe(rack, Materials.Nickel, 0.0189, 4);
        addRecipe(rack, Materials.Cobaltite, 0.0189, 4);
        addRecipe(rack, Materials.Pentlandite, 0.0063, 4);
        addRecipe(rack, Materials.Redstone, 0.0864, 4);
        addRecipe(rack, Materials.Ruby, 0.0288, 4);
        addRecipe(rack, Materials.Cinnabar, 0.0288, 4);
        addRecipe(rack, Materials.Chromite, 0.0072, 4);
        addRecipe(rack, Materials.Topaz, 0.0294, 4);
        addRecipe(rack, Materials.BlueTopaz, 0.0196, 4);

        //End
        addRecipe(end, Materials.Beryllium, 0.0405, 2);
        addRecipe(end, Materials.Emerald, 0.0243, 2);
        addRecipe(end, Materials.Thorium, 0.0162, 2);
        addRecipe(end, Materials.Nickel, 0.0189, 3);
        addRecipe(end, Materials.Cobaltite, 0.0189, 3);
        addRecipe(end, Materials.Grossular, 0.0192, 3);
        addRecipe(end, Materials.Spessartine, 0.0192, 3);
        addRecipe(end, Materials.Pyrolusite, 0.0192, 3);
        addRecipe(end, Materials.Tantalite, 0.0064, 3);
        addRecipe(end, Materials.Wulfenite, 0.0256, 3);
        addRecipe(end, Materials.Molybdenite, 0.0256, 3);
        addRecipe(end, Materials.Powellite, 0.0128, 3);
        addRecipe(end, Materials.Garnierite, 0.0189, 3);
        addRecipe(end, Materials.Pentlandite, 0.0063, 3);
        addRecipe(end, Materials.Bentonite, 0.0192, 3);
        addRecipe(end, Materials.Magnesite, 0.0192, 3);
        addRecipe(end, Materials.Olivine, 0.0192, 3);
        addRecipe(end, Materials.Glauconite, 0.064, 3);
        addRecipe(end, Materials.Chromite, 0.064, 3);
        addRecipe(end, Materials.Cooperite, 0.0288, 3);
        addRecipe(end, Materials.Palladium, 0.0224, 3);
        addRecipe(end, Materials.Iridium, 0.0128, 3);
        addRecipe(end, Materials.Scheelite, 0.0384, 3);
        addRecipe(end, Materials.Tungstate, 0.0128, 3);
        addRecipe(end, Materials.Lithium, 0.0128, 3);
        addRecipe(end, Materials.Lazurite, 0.0216, 4);
        addRecipe(end, Materials.Sodalite, 0.0144, 4);
        addRecipe(end, Materials.Lapis, 0.0252, 4);
        addRecipe(end, Materials.Calcite, 0.0108, 4);
        addRecipe(end, Materials.Naquadah, 0.36, 4);
        addRecipe(end, Materials.NaquadahEnriched, 0.04, 4);
    }

    private static void addRecipe(String input, Material material, double chance, int level) {
        drops.add(new SieveDrops(input, material, (float) chance, level));
    }

    public static void registerSieveRecipes() {
        for (SieveDrops recipe : SieveDrops.drops) {
            String type = recipe.input.equals("sand") ? "oreSandyChunk" : recipe.input.equals("nether") ? "oreNetherChunk" : recipe.input.equals("end") ? "oreEnderChunk" : "oreChunk";
            ItemStack stack = OreDictUnifier.get(OrePrefix.valueOf(type), recipe.material);
            if (!recipe.input.equals("nether") && !recipe.input.equals("end"))
                ExNihiloRegistryManager.SIEVE_REGISTRY.register(recipe.input, new ItemInfo(stack.getItem(), stack.getMetadata()), recipe.chance, recipe.level);
            else
                ExNihiloRegistryManager.SIEVE_REGISTRY.register(new ItemStack(recipe.input.equals("nether") ? ModBlocks.netherrackCrushed : ModBlocks.endstoneCrushed), new ItemInfo(stack.getItem(), stack.getMetadata()), recipe.chance, recipe.level);
        }
    }
}
