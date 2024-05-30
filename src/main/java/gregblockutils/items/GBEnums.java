package gregblockutils.items;

import exnihilocreatio.items.ItemPebble;
import exnihilocreatio.items.tools.EnumCrook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.function.Predicate;

public class GBEnums {
    public static void preInit() {
        // Ex Nihilo
        List<String> pebbles = ObfuscationReflectionHelper.getPrivateValue(ItemPebble.class, null, "names");
        pebbles.add("basalt");
        pebbles.add("black_granite");
        pebbles.add("marble");
        pebbles.add("red_granite");

        EnumHelper.addEnum(EnumCrook.class, "BASALT",
                new Class[]{String.class, int.class, int.class},
                "basalt", 128, 1);
        EnumHelper.addEnum(EnumCrook.class, "BLACK_GRANITE",
                new Class[]{String.class, int.class, int.class},
                "black_granite", 128, 1);
        EnumHelper.addEnum(EnumCrook.class, "MARBLE",
                new Class[]{String.class, int.class, int.class},
                "marble", 128, 1);
        EnumHelper.addEnum(EnumCrook.class, "RED_GRANITE",
                new Class[]{String.class, int.class, int.class},
                "red_granite", 128, 1);

        // GTCE
        EnumHelper.addEnum(MaterialIconType.class, "oreChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreEnderChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreNetherChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreSandyChunk", new Class[0]);

        EnumHelper.addEnum(OrePrefix.class, "oreChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Ore Chunk", -1L, null, MaterialIconType.valueOf("oreChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreEnderChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Ender Ore Chunk", -1L, null, MaterialIconType.valueOf("oreEnderChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreNetherChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Nether Ore Chunk", -1L, null, MaterialIconType.valueOf("oreNetherChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreSandyChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Sandy Ore Chunk", -1L, null, MaterialIconType.valueOf("oreSandyChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));
    }

    private static Predicate<Material> pred(Predicate<Material> in) {
        return in;
    }
}
