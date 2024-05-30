package gregblockutils.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIGBPlugin implements IModPlugin {
    public static final List<ItemStack> removedItems = new ArrayList<>();
    public static final List<Tuple<ItemStack, String>> recipeCatalysts = new ArrayList<>();
    public static final List<String> disabledCategories = new ArrayList<>();
    @Override
    public void register(@Nonnull IModRegistry registry) {
        for (ItemStack item : removedItems) {
            registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(item);
        }
        for (Tuple<ItemStack, String> registryItem : recipeCatalysts) {
            registry.addRecipeCatalyst(registryItem.getFirst(), registryItem.getSecond());
        }

    }
}
