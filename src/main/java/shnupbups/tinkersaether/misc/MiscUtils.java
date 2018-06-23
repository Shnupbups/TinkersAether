package shnupbups.tinkersaether.misc;

import com.google.common.base.Throwables;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Map;

public class MiscUtils {
    private static final Map<String, Material> tinkerMaterials;

    static {
        try {
            Field temp = TinkerRegistry.class.getDeclaredField("materials");
            temp.setAccessible(true);
            tinkerMaterials = (Map<String, Material>) MethodHandles.lookup().unreflectGetter(temp).invokeExact();
        } catch (Throwable e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    public static void displace(String displace) {
        Material displaced = tinkerMaterials.remove(displace);
        tinkerMaterials.put(displace, displaced);
    }

    public static ItemStack stackFromOreDict(String od) {
        NonNullList<ItemStack> list = OreDictionary.getOres(od,false);
        if(!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static boolean between(float num, float min, float max) {
        return num <= max && num >= min ? true : false;
    }

    public static float percent(float num, float pc) {
        return (pc/100)*num;
    }
}
