package gregblockutils;

import gregblockutils.events.StoneGenEvents;
import gregblockutils.exnihilo.SieveDrops;
import gregblockutils.items.GBEnums;
import gregblockutils.items.GBItems;
import gregblockutils.items.GBMetaItems;
import gregblockutils.machines.GBTextures;
import gregblockutils.machines.GBTileEntities;
import gregblockutils.recipes.GBMachineRecipes;
import gregblockutils.recipes.GBRecipeAddition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GregBlockUtils.MODID,
        name = GregBlockUtils.NAME,
        version = GregBlockUtils.VERSION,
        dependencies = "required-after:gtadditions;required-after:gtconstruct;required-after:exnihilocreatio"
)
public class GregBlockUtils {
    public static final String MODID = "gregblockutils";
    public static final String NAME = "GregBlock Utilities";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;

    public GregBlockUtils() {
        GBEnums.preInit();
        new GBTextures();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        GBMetaItems.preInit();
        new GBItems();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GBTileEntities.init();
        GBMachineRecipes.init();
        MinecraftForge.EVENT_BUS.register(new StoneGenEvents());
        SieveDrops.addSieveRecipe();
        SieveDrops.registerSieveRecipes();
        GBRecipeAddition.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        GBRecipeAddition.postInit();
    }
}
