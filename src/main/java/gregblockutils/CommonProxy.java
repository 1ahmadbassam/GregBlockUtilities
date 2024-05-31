package gregblockutils;

import gregblockutils.exnihilo.ExNihiloItemRegistry;
import gregblockutils.recipes.GBRecipeAddition;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GregBlockUtils.MODID)
public class CommonProxy {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ExNihiloItemRegistry.register(event.getRegistry());
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ExNihiloItemRegistry.registerModels();
    }
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GBRecipeAddition.register();
    }
}
