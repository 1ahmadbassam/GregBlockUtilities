package gregblockutils.exnihilo;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.IForgeRegistry;

public class ExNihiloItemRegistry {
    public static final UncompressedFuel uncompressedCharcoal = new UncompressedFuel(UncompressedFuel.FuelType.CHARCOAL);
    public static final UncompressedFuel uncompressedCoke = new UncompressedFuel(UncompressedFuel.FuelType.COKE);
    public static final UncompressedFuel uncompressedLigniteCoal = new UncompressedFuel(UncompressedFuel.FuelType.LIGNITE_COAL);
    public static final UncompressedFuel uncompressedLigniteCoke = new UncompressedFuel(UncompressedFuel.FuelType.LIGNITE_COKE);


    private ExNihiloItemRegistry() {}
    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(uncompressedCharcoal, uncompressedCoke, uncompressedLigniteCoal, uncompressedLigniteCoke);
    }
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(uncompressedCharcoal, 0, new ModelResourceLocation(uncompressedCharcoal.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(uncompressedCoke, 0, new ModelResourceLocation(uncompressedCoke.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(uncompressedLigniteCoal, 0, new ModelResourceLocation(uncompressedLigniteCoal.registryName, "inventory"));
        ModelLoader.setCustomModelResourceLocation(uncompressedLigniteCoke, 0, new ModelResourceLocation(uncompressedLigniteCoke.registryName, "inventory"));
    }
}
