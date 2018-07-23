package shnupbups.tinkersaether.items;

import com.legacy.aether.mc1122.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shnupbups.tinkersaether.TinkersAether;

public class TAItem extends Item {

    private boolean beaconPayment = false;

    public TAItem(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setRegistryName(TinkersAether.modid, name);
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    @Override
    public boolean isBeaconPayment(ItemStack stack) {
        return beaconPayment;
    }

    public TAItem setBeaconPayment() {
        this.beaconPayment = true;
        return this;
    }
}
