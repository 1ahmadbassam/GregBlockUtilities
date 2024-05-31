package gregblockutils.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FilteredFluidHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.recipes.ModHandler;
import gregtech.api.render.SimpleSidedCubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;

public class SteamRockBreaker extends MetaTileEntity {

    private static final int STEAM_DRAIN_PER_CYCLE = 50;
    private FluidTank steamFluidTank;

    public SteamRockBreaker(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamRockBreaker(metaTileEntityId);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            ItemStack output = getWitchWaterDrop();
            int largestSignal = 0;
            for (EnumFacing face : EnumFacing.VALUES)
                if (GTUtility.getRedstonePower(getWorld(), getPos(), face) > largestSignal)
                    largestSignal = GTUtility.getRedstonePower(getWorld(), getPos(), face);
            if (output == null && checkSides(Blocks.LAVA) && checkSides(Blocks.WATER)) {
                switch (largestSignal) {
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        output = new ItemStack(Blocks.STONE, 1, 3);
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        output = new ItemStack(Blocks.STONE, 1, 1);
                        break;
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        output = new ItemStack(Blocks.STONE, 1, 5);
                        break;
                    default:
                        output = new ItemStack(Blocks.COBBLESTONE);
                }
            }
            if (output != null && getOffsetTimer() % 32 == 0 && steamFluidTank.getFluidAmount() >= STEAM_DRAIN_PER_CYCLE * 4) {
                exportItems.insertItem(0, output, false);
                steamFluidTank.drain(STEAM_DRAIN_PER_CYCLE, true);
            }
            if (getOffsetTimer() % 5 == 0) {
                pushItemsIntoNearbyHandlers(frontFacing);
            }
        }
    }

    @Nullable
    private ItemStack getWitchWaterDrop() {
        if (Loader.isModLoaded("exnihilocreatio"))
            return ExNihiloInterface.getWitchWaterDrop(this);
        return null;
    }

    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(Textures.STEAM_CASING_BRONZE.getSpriteOnSide(SimpleSidedCubeRenderer.RenderSide.TOP), 16777215);
    }

    public int getSteamCapacity() {
        return 16000;
    }

    public FluidTankList createImportFluidHandler() {
        this.steamFluidTank = (new FilteredFluidHandler(this.getSteamCapacity())).setFillPredicate(ModHandler::isSteam);
        return new FluidTankList(false, this.steamFluidTank);
    }

    boolean checkSides(Block liquid) {
        EnumFacing frontFacing = getFrontFacing();
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side == frontFacing || side == EnumFacing.DOWN || side == EnumFacing.UP) continue;
            if (getWorld().getBlockState(getPos().offset(side)) == liquid.getDefaultState())
                return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        IVertexOperation[] colouredPipeline = ArrayUtils.add(pipeline, new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(this.getPaintingColorForRendering())));
        Textures.STEAM_CASING_BRONZE.render(renderState, translation, colouredPipeline);
        GBTextures.BREAKER_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), false);
        Textures.PIPE_OUT_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BRONZE_BACKGROUND, 176, 130).label(10, 5, this.getMetaFullName());

        builder.widget((new SlotWidget(this.exportItems, 0, 89, 18, true, false)).setBackgroundTexture(GuiTextures.BRONZE_SLOT));

        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.BRONZE_SLOT, 7, 48);
        return builder.build(this.getHolder(), entityPlayer);
    }
}
