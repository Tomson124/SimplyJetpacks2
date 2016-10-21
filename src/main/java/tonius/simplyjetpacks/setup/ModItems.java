package tonius.simplyjetpacks.setup;

import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.integration.EIOItems;
import tonius.simplyjetpacks.integration.EIORecipes;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.item.ItemPack.ItemFluxPack;
import tonius.simplyjetpacks.item.rewrite.*;
import tonius.simplyjetpacks.util.ItemHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public abstract class ModItems {
	public static ItemJetpack itemJetpack;

	public static ItemMeta metaItem;
	public static ItemMetaEIO metaItemEIO;

	public static ItemJetpack jetpacksEIO;
	public static ItemFluxPack fluxPacksEIO;
	public static ItemJetpack jetpacksBC;
	public static ItemMeta components;
	public static ItemMeta armorPlatings;
	public static ItemMeta particleCustomizers;

	public static ItemStack jetpackCreative;
	public static ItemStack fluxPackCreative;

	public static ItemStack particleDefault;
	public static ItemStack particleSmoke;
	public static ItemStack particleNone;
	public static ItemStack particleRainbowSmoke;

	public static ItemStack leatherStrap;
	public static ItemStack unitFlightControlEmpty;
	public static ItemStack reinforcedGliderWings;
	public static ItemStack unitFlightControl;

	public static ItemStack ingotDarkSoularium;

	public static ItemStack thrusterEIO1;
	public static ItemStack thrusterEIO2;
	public static ItemStack thrusterEIO3;
	public static ItemStack thrusterEIO4;
	public static ItemStack thrusterEIO5;

	public static ItemStack armorPlatingEIO1;
	public static ItemStack armorPlatingEIO2;
	public static ItemStack armorPlatingEIO3;
	public static ItemStack armorPlatingEIO4;

	public static ItemStack jetpackEIO1;
	public static ItemStack jetpackEIO1Armored;
	public static ItemStack jetpackEIO2;
	public static ItemStack jetpackEIO2Armored;
	public static ItemStack jetpackEIO3;
	public static ItemStack jetpackEIO3Armored;
	public static ItemStack jetpackEIO4;
	public static ItemStack jetpackEIO4Armored;

	public static ItemStack jetpackEIO5;
	public static ItemStack fluxPackEIO1;
	public static ItemStack fluxPackEIO2;
	public static ItemStack fluxPackEIO2Armored;
	public static ItemStack fluxPackEIO3;
	public static ItemStack fluxPackEIO3Armored;
	public static ItemStack fluxPackEIO4;
	public static ItemStack fluxPackEIO4Armored;

	public static ItemStack enderiumUpgrade;

	public static boolean integrateEIO = false;

	public static void preInit() {
		integrateEIO = ModType.ENDER_IO.loaded && Config.enableIntegrationEIO;

		registerItems();
		registerOreDicts();
	}

	public static void init() {
		if (integrateEIO) {
			EIOItems.init();
		}

		registerRecipes();
		doIMC();
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemJetpack) {
			((ItemJetpack) item).registerItemModel();
		}

		if (item instanceof ItemMeta) {
			((ItemMeta) item).registerItemModel();
		}

		return item;
	}

	private static void registerOreDicts() {
		for (MetaItems item : MetaItems.PARTICLE_CUSTOMIZERS) {
			OreDictionary.registerOre("particleCustomizer", new ItemStack(metaItem, 1, item.ordinal()));

		}
		if (integrateEIO) {
			OreDictionary.registerOre(MetaItemsEIO.INGOT_DARK_SOULARIUM.getName(), ingotDarkSoularium);
		}
	}

	private static void registerItems() {
		Log.info("Registering items...");

		//Jetpacks
		itemJetpack = register(new ItemJetpack("itemJetpack"));

		//Meta Items
		metaItem = register(new ItemMeta("metaItem"));

		particleDefault = MetaItems.PARTICLE_DEFAULT.getStackMetaItem();
		particleSmoke = MetaItems.PARTICLE_SMOKE.getStackMetaItem();
		particleNone = MetaItems.PARTICLE_NONE.getStackMetaItem();
		particleRainbowSmoke = MetaItems.PARTICLE_RAINBOWSMOKE.getStackMetaItem();

		leatherStrap = MetaItems.LEATHER_STRAP.getStackMetaItem();


		if (integrateEIO) {
			metaItemEIO = register(new ItemMetaEIO("metaItemEIO"));

			ingotDarkSoularium = MetaItemsEIO.INGOT_DARK_SOULARIUM.getStackMetaItemEIO();
			unitFlightControlEmpty = MetaItemsEIO.UNIT_FLIGHT_CONTROL_EMPTY.getStackMetaItemEIO();
			reinforcedGliderWings = MetaItemsEIO.REINFORCED_GLIDERWINGS.getStackMetaItemEIO();
			unitFlightControl = MetaItemsEIO.UNIT_FLIGHT_CONTROL.getStackMetaItemEIO();

			thrusterEIO1 = MetaItemsEIO.THRUSTER_EIO_1.getStackMetaItemEIO();
			thrusterEIO2 = MetaItemsEIO.THRUSTER_EIO_2.getStackMetaItemEIO();
			thrusterEIO3 = MetaItemsEIO.THRUSTER_EIO_3.getStackMetaItemEIO();
			thrusterEIO4 = MetaItemsEIO.THRUSTER_EIO_4.getStackMetaItemEIO();
			thrusterEIO5 = MetaItemsEIO.THRUSTER_EIO_5.getStackMetaItemEIO();

			armorPlatingEIO1 = MetaItemsEIO.ARMOR_PLATING_EIO_1.getStackMetaItemEIO();
			armorPlatingEIO2 = MetaItemsEIO.ARMOR_PLATING_EIO_2.getStackMetaItemEIO();
			armorPlatingEIO3 = MetaItemsEIO.ARMOR_PLATING_EIO_3.getStackMetaItemEIO();
			armorPlatingEIO4 = MetaItemsEIO.ARMOR_PLATING_EIO_4.getStackMetaItemEIO();

			jetpackEIO1 = Jetpack.JETPACK_EIO_1.getStackJetpack();
			jetpackEIO2 = Jetpack.JETPACK_EIO_2.getStackJetpack();
			jetpackEIO3 = Jetpack.JETPACK_EIO_3.getStackJetpack();
			jetpackEIO4 = Jetpack.JETPACK_EIO_4.getStackJetpack();
			jetpackEIO1Armored = Jetpack.JETPACK_EIO_1_ARMORED.getStackJetpack();
			jetpackEIO2Armored = Jetpack.JETPACK_EIO_2_ARMORED.getStackJetpack();
			jetpackEIO3Armored = Jetpack.JETPACK_EIO_3_ARMORED.getStackJetpack();
			jetpackEIO4Armored = Jetpack.JETPACK_EIO_4_ARMORED.getStackJetpack();
		}
	}

	private static void registerRecipes() {

		ItemHelper.addShapedOreRecipe(Jetpack.POTATO_JETPACK.getStackJetpack(), "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POTATO, 'R', "dustRedstone");
		ItemHelper.addShapedOreRecipe(Jetpack.POTATO_JETPACK.getStackJetpack(), "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POISONOUS_POTATO, 'R', "dustRedstone");

		ItemHelper.addShapedOreRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		ItemHelper.addShapedOreRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		ItemHelper.addShapedOreRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		ItemHelper.addShapedOreRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		ItemHelper.addShapedOreRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if (integrateEIO) {
			ItemHelper.addShapedOreRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', EIOItems.basicGear, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			ItemHelper.addShapedOreRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotVibrantAlloy", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");
			ItemHelper.addShapedOreRecipe(thrusterEIO5, "SES", "CTC", "   ", 'T', thrusterEIO4, 'S', "ingotDarkSoularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);

			ItemHelper.addShapedOreRecipe(reinforcedGliderWings, "  S", " SP", "SPP", 'S', "ingotDarkSoularium", 'P', armorPlatingEIO2);
			ItemHelper.addShapedOreRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingotDarkSoularium", 'H', "blockGlassHardened");

			ItemHelper.addShapedOreRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

			//Jetpacks
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", "P", 'J', jetpackEIO1, 'P', "particleCustomizer"));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", "P", 'J', jetpackEIO2, 'P', "particleCustomizer"));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", "P", 'J', jetpackEIO3, 'P', "particleCustomizer"));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", "P", 'J', jetpackEIO4, 'P', "particleCustomizer"));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));
		}
	}

//	private static void registerItems()
//	{
//		       fluxPacksCommon = new ItemFluxPack(ModType.SIMPLY_JETPACKS, "fluxpacksCommon");
//		       fluxPackCreative = fluxPacksCommon.putPack(9001, Packs.fluxPackCreative);
//
//		if(integrateEIO)
//		{
//			jetpackEIO5 = jetpacksEIO.putPack(5, Packs.jetpackEIO5);
//			fluxPacksEIO = new ItemFluxPack(ModType.ENDER_IO, "fluxpacksEIO");
//			fluxPackEIO1 = fluxPacksEIO.putPack(1, Packs.fluxPackEIO1);
//			fluxPackEIO2 = fluxPacksEIO.putPack(2, Packs.fluxPackEIO2);
//			fluxPackEIO2Armored = fluxPacksEIO.putPack(102, Packs.fluxPackEIO2Armored);
//			fluxPackEIO3 = fluxPacksEIO.putPack(3, Packs.fluxPackEIO3);
//			fluxPackEIO3Armored = fluxPacksEIO.putPack(103, Packs.fluxPackEIO3Armored);
//			fluxPackEIO4 = fluxPacksEIO.putPack(4, Packs.fluxPackEIO4);
//			fluxPackEIO4Armored = fluxPacksEIO.putPack(104, Packs.fluxPackEIO4Armored);
//		}
//	}

	/*private static void registerRecipes()
	{
		SimplyJetpacks.logger.info("Registering recipes");

		GameRegistry.addRecipe(new UpgradingRecipe(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));

		if(integrateEIO)
		{
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO1, "CIC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "DCD", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.doubleCapacitor, 'I', "ingotElectricalSteel", 'P', "dustGold"));
			if(EIOItems.capacitorBank != null && EIOItems.capacitorBank.getItem() != null)
			{
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "BCB", "ISI", "CPC", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
			}
			else
			{
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "CBC", "ISI", "BPB", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
			}

			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4Armored, "P", "J", 'J', fluxPackEIO4, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "J", 'J', fluxPackEIO4Armored));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingotDarkSoularium", 'C', fluxPackEIO4Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWing));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "J", "P", 'J', jetpackEIO5, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
		}
	}*/

	private static void doIMC() {
		SimplyJetpacks.logger.info("Doing intermod communication");

		if (integrateEIO) {
			ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
			ingotConductiveIron.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1, ingotConductiveIron, null, armorPlatingEIO2);

			ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
			ingotElectricalSteel.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2, ingotElectricalSteel, null, armorPlatingEIO3);

			ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
			ingotDarkSteel.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3, ingotDarkSteel, null, armorPlatingEIO4);

			ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
			ingotDarkSteel.stackSize = 1;
			EIORecipes.addAlloySmelterRecipe("Dark Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, ingotDarkSoularium);

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
		}
	}
}
