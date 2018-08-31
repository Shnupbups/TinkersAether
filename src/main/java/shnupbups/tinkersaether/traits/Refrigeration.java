package shnupbups.tinkersaether.traits;

import com.legacy.aether.api.AetherAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class Refrigeration extends TATrait {
	public static final Refrigeration refrigeration = new Refrigeration();

	public Refrigeration() {
		super("refrigeration",0x99999F);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		List<ItemStack> drops = event.getDrops();
		for(ItemStack i:drops) {
			if(AetherAPI.getInstance().hasFreezable(i)) {
				drops.set(drops.indexOf(i),AetherAPI.getInstance().getFreezable(i).getOutput());
			}
		}
	}

}
