package shnupbups.tinkersaether.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import shnupbups.tinkersaether.misc.MiscUtils;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.List;

public class Zany extends AbstractTrait {
    public static final Zany zany = new Zany();

    public Zany() {
        super("zany",0x6611DD);
    }

    private int calcBonus(ItemStack tool) {
        int durability = ToolHelper.getCurrentDurability(tool);
        int maxDurability = ToolHelper.getMaxDurability(tool);

        if (MiscUtils.between(durability, 0, MiscUtils.percent(maxDurability,20))) {
            return 6;
        } else if (MiscUtils.between(durability, MiscUtils.percent(maxDurability,20), MiscUtils.percent(maxDurability,40))) {
            return 4;
        } else if (MiscUtils.between(durability, MiscUtils.percent(maxDurability,40), MiscUtils.percent(maxDurability,60))) {
            return 3;
        } else if (MiscUtils.between(durability, MiscUtils.percent(maxDurability,60), MiscUtils.percent(maxDurability,80))) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        if(ToolHelper.isToolEffective2(tool, event.getState())) {
            event.setNewSpeed(event.getNewSpeed() * calcBonus(tool));
        }
    }

    @Override
    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
        String loc = String.format(LOC_Extra, getModifierIdentifier());

        return ImmutableList.of(Util.translateFormatted(loc, Util.df.format(calcBonus(tool))));
    }
}
