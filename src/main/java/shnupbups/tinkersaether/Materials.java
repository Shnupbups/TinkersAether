package shnupbups.tinkersaether;

import com.google.common.collect.Lists;
import slimeknights.tconstruct.library.materials.Material;

import java.util.List;

public class Materials {
    public static final List<Material> materials = Lists.newArrayList();

    public static Material mat(String name, int color) {
        Material mat = new Material(name, color, false);
        materials.add(mat);
        return mat;
    }
}
