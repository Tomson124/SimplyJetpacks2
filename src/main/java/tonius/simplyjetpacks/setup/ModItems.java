package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.integration.EIOItems;
import tonius.simplyjetpacks.integration.EIORecipes;
import tonius.simplyjetpacks.integration.ModType;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.item.ItemMeta.MetaItem;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.ItemPack.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.util.ItemHelper;

public abstract class ModItems
{
	public static tonius.simplyjetpacks.item.rewrite.ItemJetpack itemJetpack;



	public static ItemJetpack jetpacksCommon;
	public static ItemFluxPack fluxPacksCommon;
	public static ItemJetpack jetpacksEIO;
	public static ItemFluxPack fluxPacksEIO;
	public static ItemJetpack jetpacksBC;
	public static ItemMeta components;
	public static ItemMeta armorPlatings;
	public static ItemMeta particleCustomizers;

	public static ItemStack jetpackPotato;
	public static ItemStack jetpackCreative;
	public static ItemStack fluxPackCreative;

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

	public static ItemStack leatherStrap;
	public static ItemStack jetpackIcon;
	public static ItemStack thrusterEIO1;
	public static ItemStack thrusterEIO2;
	public static ItemStack thrusterEIO3;
	public static ItemStack thrusterEIO4;
	public static ItemStack thrusterEIO5;
	public static ItemStack enderiumUpgrade;
	public static ItemStack ingotDarkSoularium;
	public static ItemStack reinforcedGliderWing;
	public static ItemStack unitFlightControlEmpty;
	public static ItemStack unitFlightControl;

	public static ItemStack armorPlatingEIO1;
	public static ItemStack armorPlatingEIO2;
	public static ItemStack armorPlatingEIO3;
	public static ItemStack armorPlatingEIO4;

	public static ItemStack particleDefault;
	public static ItemStack particleNone;
	public static ItemStack particleSmoke;
	public static ItemStack particleRainbowSmoke;

	private static boolean integrateEIO = false;

	public static void preInit()
	{
		integrateEIO = ModType.ENDER_IO.loaded && Config.enableIntegrationEIO;

		//registerItems();
		initTest();
	}

	public static void init()
	{
		if(integrateEIO)
		{
			EIOItems.init();
		}

		//registerRecipes();
		doIMC();
	}

	public static void initTest()
	{
		itemJetpack = register(new tonius.simplyjetpacks.item.rewrite.ItemJetpack("itemJetpack"));

		//jetpacksCommon = register(new ItemJetpack(ModType.SIMPLY_JETPACKS, "jetpacksCommon"));
		//        jetpacksCommon = register(new ItemJetpack(ModType.SIMPLY_JETPACKS, "jetpacksCommon"));
		        //jetpackPotato = jetpacksCommon.putPack(0, Packs.jetpackPotato, true);
		//jetpackCreative = jetpacksCommon.putPack(9001, Packs.jetpackCreative);
		//        fluxPacksCommon = register(new ItemFluxPack(ModType.SIMPLY_JETPACKS, "fluxpacksCommon"));
		//        fluxPackCreative = fluxPacksCommon.putPack(9001, Packs.fluxPackCreative);
	}

	private static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);

		if (item instanceof tonius.simplyjetpacks.item.rewrite.ItemJetpack) {
			((tonius.simplyjetpacks.item.rewrite.ItemJetpack) item).registerItemModel();
		}

		if(item instanceof ItemPack)
		{
			((ItemPack) item).registerItemModel();
		}

		if(item instanceof ItemMeta)
		{
			((ItemMeta) item).registerItemModel();
		}

		return item;
	}

	private static void registerItems()
	{
		SimplyJetpacks.logger.info("Registering items");

		// For compatibility, do not change item IDs until 1.8+

		//        jetpacksCommon = new ItemJetpack(ModType.SIMPLY_JETPACKS, "jetpacksCommon");
		//        jetpackPotato = jetpacksCommon.putPack(0, Packs.jetpackPotato, true);
		//        jetpackCreative = jetpacksCommon.putPack(9001, Packs.jetpackCreative);
		//        fluxPacksCommon = new ItemFluxPack(ModType.SIMPLY_JETPACKS, "fluxpacksCommon");
		//        fluxPackCreative = fluxPacksCommon.putPack(9001, Packs.fluxPackCreative);

		if(integrateEIO)
		{
			jetpacksEIO = new ItemJetpack(ModType.ENDER_IO, "jetpacksEIO");
			jetpackEIO1 = jetpacksEIO.putPack(1, Packs.jetpackEIO1);
			jetpackEIO1Armored = jetpacksEIO.putPack(101, Packs.jetpackEIO1Armored);
			jetpackEIO2 = jetpacksEIO.putPack(2, Packs.jetpackEIO2);
			jetpackEIO2Armored = jetpacksEIO.putPack(102, Packs.jetpackEIO2Armored);
			jetpackEIO3 = jetpacksEIO.putPack(3, Packs.jetpackEIO3);
			jetpackEIO3Armored = jetpacksEIO.putPack(103, Packs.jetpackEIO3Armored);
			jetpackEIO4 = jetpacksEIO.putPack(4, Packs.jetpackEIO4);
			jetpackEIO4Armored = jetpacksEIO.putPack(104, Packs.jetpackEIO4Armored);
			jetpackEIO5 = jetpacksEIO.putPack(5, Packs.jetpackEIO5);
			fluxPacksEIO = new ItemFluxPack(ModType.ENDER_IO, "fluxpacksEIO");
			fluxPackEIO1 = fluxPacksEIO.putPack(1, Packs.fluxPackEIO1);
			fluxPackEIO2 = fluxPacksEIO.putPack(2, Packs.fluxPackEIO2);
			fluxPackEIO2Armored = fluxPacksEIO.putPack(102, Packs.fluxPackEIO2Armored);
			fluxPackEIO3 = fluxPacksEIO.putPack(3, Packs.fluxPackEIO3);
			fluxPackEIO3Armored = fluxPacksEIO.putPack(103, Packs.fluxPackEIO3Armored);
			fluxPackEIO4 = fluxPacksEIO.putPack(4, Packs.fluxPackEIO4);
			fluxPackEIO4Armored = fluxPacksEIO.putPack(104, Packs.fluxPackEIO4Armored);
		}

		components = new ItemMeta("components");
		armorPlatings = new ItemMeta("armorPlatings");
		particleCustomizers = new ItemMeta("particleCustomizers");

		leatherStrap = components.addMetaItem(0, new MetaItem("leatherStrap", null, EnumRarity.COMMON), false);

		if(integrateEIO)
		{
			thrusterEIO1 = components.addMetaItem(21, new MetaItem("thruster.eio.1", null, EnumRarity.COMMON), false);
			thrusterEIO2 = components.addMetaItem(22, new MetaItem("thruster.eio.2", null, EnumRarity.COMMON), false);
			thrusterEIO3 = components.addMetaItem(23, new MetaItem("thruster.eio.3", null, EnumRarity.UNCOMMON), false);
			thrusterEIO4 = components.addMetaItem(24, new MetaItem("thruster.eio.4", null, EnumRarity.RARE), false);
			thrusterEIO5 = components.addMetaItem(25, new MetaItem("thruster.eio.5", null, EnumRarity.EPIC), false);
			ingotDarkSoularium = components.addMetaItem(70, new MetaItem("ingotDarkSoularium", null, EnumRarity.UNCOMMON, true, false), true);
			reinforcedGliderWing = components.addMetaItem(71, new MetaItem("reinforcedGliderWing", null, EnumRarity.UNCOMMON), false);
			unitFlightControlEmpty = components.addMetaItem(72, new MetaItem("unitFlightControl.empty", null, EnumRarity.COMMON), false);
			unitFlightControl = components.addMetaItem(73, new MetaItem("unitFlightControl", null, EnumRarity.UNCOMMON), false);

			armorPlatingEIO1 = armorPlatings.addMetaItem(11, new MetaItem("armorPlating.eio.1", null, EnumRarity.COMMON), false);
			armorPlatingEIO2 = armorPlatings.addMetaItem(12, new MetaItem("armorPlating.eio.2", null, EnumRarity.COMMON), false);
			armorPlatingEIO3 = armorPlatings.addMetaItem(13, new MetaItem("armorPlating.eio.3", null, EnumRarity.COMMON), false);
			armorPlatingEIO4 = armorPlatings.addMetaItem(14, new MetaItem("armorPlating.eio.4", null, EnumRarity.COMMON), false);
		}

		particleDefault = particleCustomizers.addMetaItem(0, new MetaItem("particle.0", "particleCustomizers", EnumRarity.COMMON), false);
		particleNone = particleCustomizers.addMetaItem(1, new MetaItem("particle.1", "particleCustomizers", EnumRarity.COMMON), false);
		particleSmoke = particleCustomizers.addMetaItem(2, new MetaItem("particle.2", "particleCustomizers", EnumRarity.COMMON), false);
		particleRainbowSmoke = particleCustomizers.addMetaItem(3, new MetaItem("particle.3", "particleCustomizers", EnumRarity.COMMON), false);
	}

	private static void registerRecipes()
	{
		SimplyJetpacks.logger.info("Registering recipes");

		ItemHelper.addShapedOreRecipe(jetpackPotato, "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POTATO, 'R', "dustRedstone");
		ItemHelper.addShapedOreRecipe(jetpackPotato, "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POISONOUS_POTATO, 'R', "dustRedstone");
		GameRegistry.addRecipe(new UpgradingRecipe(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));

		ItemHelper.addShapedOreRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		ItemHelper.addShapedOreRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		ItemHelper.addShapedOreRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		ItemHelper.addShapedOreRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		ItemHelper.addShapedOreRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if(integrateEIO)
		{
			ItemHelper.addShapedOreRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', EIOItems.basicGear, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			ItemHelper.addShapedOreRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotPhasedGold", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");

			ItemHelper.addShapedOreRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

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

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotPhasedGold", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", "P", 'J', jetpackEIO1, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", "P", 'J', jetpackEIO2, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", "P", 'J', jetpackEIO3, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", "P", 'J', jetpackEIO4, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));

			ItemHelper.addShapedOreRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingotDarkSoularium", 'H', "blockGlassHardened");
			ItemHelper.addShapedOreRecipe(thrusterEIO5, "SES", "CTC", 'T', thrusterEIO4, 'S', "ingotDarkSoularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);
			ItemHelper.addShapedOreRecipe(reinforcedGliderWing, "  S", " SP", "SPP", 'S', "ingotDarkSoularium", 'P', armorPlatingEIO2);
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingotDarkSoularium", 'C', fluxPackEIO4Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWing));

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "J", "P", 'J', jetpackEIO5, 'P', new ItemStack(particleCustomizers, 1, OreDictionary.WILDCARD_VALUE)));
		}
	}

	private static void doIMC()
	{
		SimplyJetpacks.logger.info("Doing intermod communication");

		if(integrateEIO)
		{
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
			EIORecipes.addAlloySmelterRecipe("Enriched Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, ingotDarkSoularium);

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
		}
	}
}
