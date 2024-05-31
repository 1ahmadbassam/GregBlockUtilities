package gregblockutils.exnihilo;

import gregicadditions.GAMaterials;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.blay09.mods.excompressum.ExCompressum;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public final class UncompressedFuel extends Item {
    public enum FuelType {
        CHARCOAL("charcoal", Materials.Charcoal),
        COKE("coke", Materials.Coke),
        LIGNITE_COAL("lignite_coal", Materials.Lignite),
        LIGNITE_COKE("lignite_coke", GAMaterials.LigniteCoke);

        private final String name;
        private final Material material;

        FuelType(String name, Material material) {
            this.name = name;
            this.material = material;
        }
        public String getName() {return name;}
        Material getMaterial() {return material;}
        public ItemStack getRepresentativeItem() {
            if (material == Materials.Charcoal) return new ItemStack(Items.COAL, 1, 1);
            return OreDictUnifier.get(OrePrefix.gem, material);
        }
    }
    public final ResourceLocation registryName;
    public final FuelType fuelType;

    public UncompressedFuel(@Nonnull FuelType type) {
        registryName = new ResourceLocation("excompressum", "uncompressed_" + type.getName());
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName.toString());
        this.setCreativeTab(ExCompressum.creativeTab);
        this.fuelType = type;
    }

    @Override
    public int getItemBurnTime(@Nonnull ItemStack itemStack) {
        if (fuelType == FuelType.CHARCOAL) {return 200;}
        ItemStack item = fuelType.getRepresentativeItem();
        return item.getItem().getItemBurnTime(item) / 8;
    }
}
