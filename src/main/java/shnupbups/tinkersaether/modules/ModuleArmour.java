package shnupbups.tinkersaether.modules;

import net.minecraftforge.common.MinecraftForge;
import shnupbups.tinkersaether.TinkersAether;
import slimeknights.tconstruct.library.materials.Material;

public class ModuleArmour{
	public static ModuleArmour instance = new ModuleArmour();

	public ModuleArmour() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static final Material skyroot = ModuleBase.skyroot;
	public static final Material holystone = ModuleBase.holystone;
	public static final Material goldenAmber = ModuleBase.goldenAmber
	public static final Material zanite = ModuleBase.zanite;
	public static final Material gravitite = ModuleBase.gravitite;
	public static final Material valkyrie = ModuleBase.valkyrie;
	public static final Material swet = ModuleBase.swet;
	public static final Material candyCane = ModuleBase.candyCane;
	public static final Material aercloudBlue = ModuleBase.aercloudBlue;
	public static final Material aercloudCold = ModuleBase.aercloudCold;
	public static final Material aercloudGold = ModuleBase.aercloudGold;
	public static final Material icestone = ModuleBase.icestone;

	public void preInit(){
		TinkersAether.logger.info("Armour Module - Begin PreInit");
	}

	public void init(){}

	public void postInit(){}
}
