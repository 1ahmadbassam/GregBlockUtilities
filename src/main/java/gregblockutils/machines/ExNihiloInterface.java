package gregblockutils.machines;

import exnihilocreatio.ModFluids;
import gregtech.api.util.GTUtility;
import gregtech.api.util.XSTR;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ExNihiloInterface {
    @Nullable
    static ItemStack getWitchWaterDrop(SteamRockBreaker instance) {
        if (instance.checkSides(Blocks.WATER) && instance.checkSides(ModFluids.blockWitchwater)) {
            return new ItemStack(Blocks.DIRT, 1, GTUtility.getRandomIntXSTR(3));
        }
        return null;
    }
}
