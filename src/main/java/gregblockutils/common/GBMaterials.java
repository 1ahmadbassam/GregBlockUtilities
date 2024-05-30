package gregblockutils.common;

import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.SolidMaterial;

@IMaterialHandler.RegisterMaterialHandler
public class GBMaterials implements IMaterialHandler{
    @Override
    public void onMaterialsInit() {
        Materials.Stone.addFlag(SolidMaterial.MatFlags.GENERATE_GEAR | SolidMaterial.MatFlags.GENERATE_ROD);
    }
}
