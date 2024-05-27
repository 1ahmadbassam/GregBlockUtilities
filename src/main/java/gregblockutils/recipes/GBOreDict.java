package gregblockutils.recipes;

import gregicadditions.GAUtils;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.W;

public class GBOreDict {
    public static final List<Tuple<String, ItemStack>> oreDictionaryRemovals = new ArrayList<>();

    public static void init() {
        oreDictionaryRemovals.add(new Tuple<>("dirt", new ItemStack(Blocks.DIRT, 1, W)));
        GAUtils.oreDictRemoval(oreDictionaryRemovals);
        OreDictUnifier.registerOre(new ItemStack(Blocks.DIRT), "dirt");
    }
}
