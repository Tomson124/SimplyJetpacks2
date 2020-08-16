package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipeShaped;
import tonius.simplyjetpacks.crafting.UpgradingRecipeShapeless;
import tonius.simplyjetpacks.integration.*;
import tonius.simplyjetpacks.item.*;
import tonius.simplyjetpacks.util.crafting.RecipeHandler;
import tonius.simplyjetpacks.util.crafting.RecipeHelper;

public abstract class ModItems {

	public static ItemJetpack itemJetpack;
	public static ItemFluxpack itemFluxPack;
	public static ItemMeta metaItem;
	public static ItemMetaMods metaItemMods;

	// Simply Jetpacks
	public static ItemPilotGoggles pilotGoggles;
	public static ItemStack leatherStrap;
	public static ItemStack jetpackCreative;
	public static ItemStack fluxPackCreative;
	public static ItemStack particleDefault;
	public static ItemStack particleSmoke;
	public static ItemStack particleNone;
	public static ItemStack particleRainbowSmoke;

	// Vanilla
	public static ItemStack thrusterVanilla1;
	public static ItemStack thrusterVanilla2;
	public static ItemStack thrusterVanilla3;

	public static ItemStack jetpackVanilla1;
	public static ItemStack jetpackVanilla1Armored;
	public static ItemStack jetpackVanilla2;
	public static ItemStack jetpackVanilla2Armored;
	public static ItemStack jetpackVanilla3;
	public static ItemStack jetpackVanilla3Armored;

	// EnderIO
	public static ItemStack unitFlightControlEmpty;
	public static ItemStack reinforcedGliderWings;
	public static ItemStack unitFlightControl;
	public static ItemStack ingotDarkSoularium;
	
	public static ItemStack armorPlatingEIO1;
	public static ItemStack armorPlatingEIO2;
	public static ItemStack armorPlatingEIO3;
	public static ItemStack armorPlatingEIO4;

	public static ItemStack thrusterEIO1;
	public static ItemStack thrusterEIO2;
	public static ItemStack thrusterEIO3;
	public static ItemStack thrusterEIO4;
	public static ItemStack thrusterEIO5;

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

	// Thermal Expansion
	public static ItemStack unitGlowstone;
	public static ItemStack unitGlowstoneEmpty;
	public static ItemStack unitCryotheumEmpty;
	public static ItemStack unitCryotheum;
	public static ItemStack plateFlux;
	public static ItemStack armorFluxPlate;

	public static ItemStack armorPlatingTE1;
	public static ItemStack armorPlatingTE2;
	public static ItemStack armorPlatingTE3;
	public static ItemStack armorPlatingTE4;

	public static ItemStack thrusterTE1;
	public static ItemStack thrusterTE2;
	public static ItemStack thrusterTE3;
	public static ItemStack thrusterTE4;
	public static ItemStack thrusterTE5;

	public static ItemStack jetpackTE1;
	public static ItemStack jetpackTE1Armored;
	public static ItemStack jetpackTE2;
	public static ItemStack jetpackTE2Armored;
	public static ItemStack jetpackTE3;
	public static ItemStack jetpackTE3Armored;
	public static ItemStack jetpackTE4;
	public static ItemStack jetpackTE4Armored;
	public static ItemStack jetpackTE5;

	public static ItemStack fluxPackTE1;
	public static ItemStack fluxPackTE2;
	public static ItemStack fluxPackTE2Armored;
	public static ItemStack fluxPackTE3;
	public static ItemStack fluxPackTE3Armored;

	// Mekanism
	public static ItemStack armorPlatingMek1;
	public static ItemStack armorPlatingMek2;
	public static ItemStack armorPlatingMek3;
	public static ItemStack armorPlatingMek4;

	public static ItemStack thrusterMek1;
	public static ItemStack thrusterMek2;
	public static ItemStack thrusterMek3;
	public static ItemStack thrusterMek4;

	public static ItemStack jetpackMek1;
	public static ItemStack jetpackMek1Armored;
	public static ItemStack jetpackMek2;
	public static ItemStack jetpackMek2Armored;
	public static ItemStack jetpackMek3;
	public static ItemStack jetpackMek3Armored;
	public static ItemStack jetpackMek4;
	public static ItemStack jetpackMek4Armored;
	
	// Immersive Engineering
	public static ItemStack armorPlatingIE1;
	public static ItemStack armorPlatingIE2;
	public static ItemStack armorPlatingIE3;

	public static ItemStack thrusterIE1;
	public static ItemStack thrusterIE2;
	public static ItemStack thrusterIE3;

	public static ItemStack jetpackIE1;
	public static ItemStack jetpackIE1Armored;
	public static ItemStack jetpackIE2;
	public static ItemStack jetpackIE2Armored;
	public static ItemStack jetpackIE3;
	public static ItemStack jetpackIE3Armored;
	
	// Redstone Repository
	public static ItemStack jetpackTE5Enderium;

	public static boolean integrateVanilla = Config.enableIntegrationVanilla;
	public static boolean integrateEIO = ModType.ENDER_IO.loaded && Config.enableIntegrationEIO;
	public static boolean integrateTE = ModType.THERMAL_EXPANSION.loaded && Config.enableIntegrationTE;
	public static boolean integrateTD = ModType.THERMAL_DYNAMICS.loaded && Config.enableIntegrationTD;
	public static boolean integrateRA = ModType.REDSTONE_ARSENAL.loaded && Config.enableIntegrationRA;
	public static boolean integrateMek = ModType.MEKANISM.loaded && Config.enableIntegrationMek;
	public static boolean integrateIE = ModType.IMMERSIVE_ENGINEERING.loaded && Config.enableIntegrationIE;
	public static boolean integrateRR = ModType.REDSTONE_REPOSITORY.loaded && Config.enableIntegrationRR;

	public static void preInit() {
		registerItems();
	}

	public static void init() {
		registerOreDictionary();
		doIMC();
	}

	private static <T extends Item> T register(T item) {
		//GameRegistry.register(item);
		ForgeRegistries.ITEMS.register(item);
		if (item instanceof ItemJetpack) {
			((ItemJetpack) item).registerItemModel();
		}
		if (item instanceof ItemFluxpack) {
			((ItemFluxpack) item).registerItemModel();
		}
		if (item instanceof ItemMeta) {
			((ItemMeta) item).registerItemModel();
		}
		if (item instanceof ItemMetaMods) {
			((ItemMetaMods) item).registerItemModel();
		}
		if (item instanceof ItemPilotGoggles) {
			((ItemPilotGoggles) item).registerItemModel();
		}
		return item;
	}

	private static void registerOreDictionary() {
		for (MetaItems item : MetaItems.PARTICLE_CUSTOMIZERS) {
			OreDictionary.registerOre("particle_customizer", new ItemStack(metaItem, 1, item.ordinal()));
		}
		if (integrateEIO) {
			OreDictionary.registerOre(MetaItemsMods.INGOT_DARK_SOULARIUM.getName(), ingotDarkSoularium);
		}
	}

	private static void registerItems() {
		SimplyJetpacks.logger.info("Registering items...");

		// Jetpacks / Fluxpacks
		itemJetpack = register(new ItemJetpack("itemJetpack"));
		itemFluxPack = register(new ItemFluxpack("itemFluxpack"));

		// Meta Items
		metaItem = register(new ItemMeta("metaItem"));
		metaItemMods = register(new ItemMetaMods("metaItemMods"));

		jetpackCreative = Jetpack.JETPACK_CREATIVE.getStackJetpack();
		fluxPackCreative = Fluxpack.FLUXPACK_CREATIVE.getStackFluxpack();

		particleDefault = MetaItems.PARTICLE_DEFAULT.getStackMetaItem();
		particleSmoke = MetaItems.PARTICLE_SMOKE.getStackMetaItem();
		particleNone = MetaItems.PARTICLE_NONE.getStackMetaItem();
		particleRainbowSmoke = MetaItems.PARTICLE_RAINBOW.getStackMetaItem();

		leatherStrap = MetaItems.LEATHER_STRAP.getStackMetaItem();
		pilotGoggles = register(new ItemPilotGoggles("pilot_goggles"));
	}

	public static void gatherIngredients() {
		if (integrateEIO) {
			ingotDarkSoularium = MetaItemsMods.INGOT_DARK_SOULARIUM.getStackMetaItem();
			unitFlightControlEmpty = MetaItemsMods.UNIT_FLIGHT_CONTROL_EMPTY.getStackMetaItem();
			reinforcedGliderWings = MetaItemsMods.REINFORCED_GLIDERWINGS.getStackMetaItem();
			unitFlightControl = MetaItemsMods.UNIT_FLIGHT_CONTROL.getStackMetaItem();

			thrusterEIO1 = MetaItemsMods.THRUSTER_EIO_1.getStackMetaItem();
			thrusterEIO2 = MetaItemsMods.THRUSTER_EIO_2.getStackMetaItem();
			thrusterEIO3 = MetaItemsMods.THRUSTER_EIO_3.getStackMetaItem();
			thrusterEIO4 = MetaItemsMods.THRUSTER_EIO_4.getStackMetaItem();
			thrusterEIO5 = MetaItemsMods.THRUSTER_EIO_5.getStackMetaItem();

			armorPlatingEIO1 = MetaItemsMods.ARMOR_PLATING_EIO_1.getStackMetaItem();
			armorPlatingEIO2 = MetaItemsMods.ARMOR_PLATING_EIO_2.getStackMetaItem();
			armorPlatingEIO3 = MetaItemsMods.ARMOR_PLATING_EIO_3.getStackMetaItem();
			armorPlatingEIO4 = MetaItemsMods.ARMOR_PLATING_EIO_4.getStackMetaItem();

			jetpackEIO1 = Jetpack.JETPACK_EIO_1.getStackJetpack();
			jetpackEIO1Armored = Jetpack.JETPACK_EIO_1_ARMORED.getStackJetpack();
			jetpackEIO2 = Jetpack.JETPACK_EIO_2.getStackJetpack();
			jetpackEIO2Armored = Jetpack.JETPACK_EIO_2_ARMORED.getStackJetpack();
			jetpackEIO3 = Jetpack.JETPACK_EIO_3.getStackJetpack();
			jetpackEIO3Armored = Jetpack.JETPACK_EIO_3_ARMORED.getStackJetpack();
			jetpackEIO4 = Jetpack.JETPACK_EIO_4.getStackJetpack();
			jetpackEIO4Armored = Jetpack.JETPACK_EIO_4_ARMORED.getStackJetpack();
			jetpackEIO5 = Jetpack.JETPLATE_EIO_5.getStackJetpack();

			fluxPackEIO1 = Fluxpack.FLUXPACK_EIO1.getStackFluxpack();
			fluxPackEIO2 = Fluxpack.FLUXPACK_EIO2.getStackFluxpack();
			fluxPackEIO2Armored = Fluxpack.FLUXPACK_EIO2_ARMORED.getStackFluxpack();
			fluxPackEIO3 = Fluxpack.FLUXPACK_EIO3.getStackFluxpack();
			fluxPackEIO3Armored = Fluxpack.FLUXPACK_EIO3_ARMORED.getStackFluxpack();
		}

		if (integrateTE) {
			TEItems.init();
			thrusterTE1 = MetaItemsMods.THRUSTER_TE_1.getStackMetaItem();
			thrusterTE2 = MetaItemsMods.THRUSTER_TE_2.getStackMetaItem();
			thrusterTE3 = MetaItemsMods.THRUSTER_TE_3.getStackMetaItem();
			thrusterTE4 = MetaItemsMods.THRUSTER_TE_4.getStackMetaItem();
			thrusterTE5 = MetaItemsMods.THRUSTER_TE_5.getStackMetaItem();

			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				plateFlux = MetaItemsMods.PLATE_FLUX.getStackMetaItem();
				armorFluxPlate = MetaItemsMods.ARMOR_PLATE_FLUX.getStackMetaItem();
			} else if (integrateRA) {
				plateFlux = RAItems.fluxPlating;
				armorFluxPlate = RAItems.armorChestPlate;
			}

			unitGlowstone = MetaItemsMods.UNIT_GLOWSTONE.getStackMetaItem();
			unitGlowstoneEmpty = MetaItemsMods.UNIT_GLOWSTONE_EMPTY.getStackMetaItem();
			unitCryotheum = MetaItemsMods.UNIT_CRYOTHEUM.getStackMetaItem();
			unitCryotheumEmpty = MetaItemsMods.UNIT_CRYOTHEUM_EMPTY.getStackMetaItem();

			armorPlatingTE1 = MetaItemsMods.ARMOR_PLATING_TE_1.getStackMetaItem();
			armorPlatingTE2 = MetaItemsMods.ARMOR_PLATING_TE_2.getStackMetaItem();
			armorPlatingTE3 = MetaItemsMods.ARMOR_PLATING_TE_3.getStackMetaItem();
			armorPlatingTE4 = MetaItemsMods.ARMOR_PLATING_TE_4.getStackMetaItem();

			jetpackTE1 = Jetpack.JETPACK_TE_1.getStackJetpack();
			jetpackTE1Armored = Jetpack.JETPACK_TE_1_ARMORED.getStackJetpack();
			jetpackTE2 = Jetpack.JETPACK_TE_2.getStackJetpack();
			jetpackTE2Armored = Jetpack.JETPACK_TE_2_ARMORED.getStackJetpack();
			jetpackTE3 = Jetpack.JETPACK_TE_3.getStackJetpack();
			jetpackTE3Armored = Jetpack.JETPACK_TE_3_ARMORED.getStackJetpack();
			jetpackTE4 = Jetpack.JETPACK_TE_4.getStackJetpack();
			jetpackTE4Armored = Jetpack.JETPACK_TE_4_ARMORED.getStackJetpack();
			jetpackTE5 = Jetpack.JETPLATE_TE_5.getStackJetpack();

			if (integrateRR) {
				jetpackTE5Enderium = Jetpack.JETPLATE_TE_5_ENDERIUM.getStackJetpack();
			}

			fluxPackTE1 = Fluxpack.FLUXPACK_TE1.getStackFluxpack();
			fluxPackTE2 = Fluxpack.FLUXPACK_TE2.getStackFluxpack();
			fluxPackTE2Armored = Fluxpack.FLUXPACK_TE2_ARMORED.getStackFluxpack();
			fluxPackTE3 = Fluxpack.FLUXPACK_TE3.getStackFluxpack();
			fluxPackTE3Armored = Fluxpack.FLUXPACK_TE3_ARMORED.getStackFluxpack();
		}

		if (integrateVanilla) {
			thrusterVanilla1 = MetaItemsMods.THRUSTER_VANILLA_1.getStackMetaItem();
			thrusterVanilla2 = MetaItemsMods.THRUSTER_VANILLA_2.getStackMetaItem();
			thrusterVanilla3 = MetaItemsMods.THRUSTER_VANILLA_3.getStackMetaItem();
			jetpackVanilla1 = Jetpack.JETPACK_VANILLA_1.getStackJetpack();
			jetpackVanilla1Armored = Jetpack.JETPACK_VANILLA_1_ARMORED.getStackJetpack();
			jetpackVanilla2 = Jetpack.JETPACK_VANILLA_2.getStackJetpack();
			jetpackVanilla2Armored = Jetpack.JETPACK_VANILLA_2_ARMORED.getStackJetpack();
			jetpackVanilla3 = Jetpack.JETPACK_VANILLA_3.getStackJetpack();
			jetpackVanilla3Armored = Jetpack.JETPACK_VANILLA_3_ARMORED.getStackJetpack();
		}

		if (integrateMek) {
			armorPlatingMek1 = MetaItemsMods.ARMOR_PLATING_MEK_1.getStackMetaItem();
			armorPlatingMek2 = MetaItemsMods.ARMOR_PLATING_MEK_2.getStackMetaItem();
			armorPlatingMek3 = MetaItemsMods.ARMOR_PLATING_MEK_3.getStackMetaItem();
			armorPlatingMek4 = MetaItemsMods.ARMOR_PLATING_MEK_4.getStackMetaItem();
			thrusterMek1 = MetaItemsMods.THRUSTER_MEK_1.getStackMetaItem();
			thrusterMek2 = MetaItemsMods.THRUSTER_MEK_2.getStackMetaItem();
			thrusterMek3 = MetaItemsMods.THRUSTER_MEK_3.getStackMetaItem();
			thrusterMek4 = MetaItemsMods.THRUSTER_MEK_4.getStackMetaItem();
			jetpackMek1 = Jetpack.JETPACK_MEK_1.getStackJetpack();
			jetpackMek1Armored = Jetpack.JETPACK_MEK_1_ARMORED.getStackJetpack();
			jetpackMek2 = Jetpack.JETPACK_MEK_2.getStackJetpack();
			jetpackMek2Armored = Jetpack.JETPACK_MEK_2_ARMORED.getStackJetpack();
			jetpackMek3 = Jetpack.JETPACK_MEK_3.getStackJetpack();
			jetpackMek3Armored = Jetpack.JETPACK_MEK_3_ARMORED.getStackJetpack();
			jetpackMek4 = Jetpack.JETPACK_MEK_4.getStackJetpack();
			jetpackMek4Armored = Jetpack.JETPACK_MEK_4_ARMORED.getStackJetpack();
		}

		if (integrateIE) {
			armorPlatingIE1 = MetaItemsMods.ARMOR_PLATING_IE_1.getStackMetaItem();
			armorPlatingIE2 = MetaItemsMods.ARMOR_PLATING_IE_2.getStackMetaItem();
			armorPlatingIE3 = MetaItemsMods.ARMOR_PLATING_IE_3.getStackMetaItem();
			thrusterIE1 = MetaItemsMods.THRUSTER_IE_1.getStackMetaItem();
			thrusterIE2 = MetaItemsMods.THRUSTER_IE_2.getStackMetaItem();
			thrusterIE3 = MetaItemsMods.THRUSTER_IE_3.getStackMetaItem();
			jetpackIE1 = Jetpack.JETPACK_IE_1.getStackJetpack();
			jetpackIE1Armored = Jetpack.JETPACK_IE_1_ARMORED.getStackJetpack();
			jetpackIE2 = Jetpack.JETPACK_IE_2.getStackJetpack();
			jetpackIE2Armored = Jetpack.JETPACK_IE_2_ARMORED.getStackJetpack();
			jetpackIE3 = Jetpack.JETPACK_IE_3.getStackJetpack();
			jetpackIE3Armored = Jetpack.JETPACK_IE_3_ARMORED.getStackJetpack();
		}
	}

	public static void registerRecipes() {
		gatherIngredients();
		RecipeHandler.addOreDictRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");
		ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', "particle_customizer"));

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		RecipeHandler.addOreDictRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		RecipeHandler.addOreDictRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		RecipeHandler.addOreDictRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		RecipeHandler.addOreDictRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if (integrateEIO) {
			RecipeHandler.addOreDictRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', "gearWood", 'S', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			RecipeHandler.addOreDictRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotVibrantAlloy", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");
			RecipeHandler.addOreDictRecipe(thrusterEIO5, "SES", "CTC", "   ", 'T', thrusterEIO4, 'S', "ingot_dark_soularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);

			RecipeHandler.addOreDictRecipe(reinforcedGliderWings, "  S", " SP", "SPP", 'S', "ingot_dark_soularium", 'P', armorPlatingEIO2);
			RecipeHandler.addOreDictRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingot_dark_soularium", 'H', "blockGlassHardened");

			RecipeHandler.addOreDictRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingot_dark_soularium", 'C', fluxPackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWings));

			for (Jetpack jetpack : Jetpack.PACKS_EIO) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO4, "J", 'J', jetpackEIO4Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO1, "CUC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'U', EIOItems.capacitorBankBasic, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO2, "CBC", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotVibrantAlloy", 'P', EIOItems.vibrantCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO3));
		}

		if (integrateTE) {
			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				RecipeHandler.addOreDictRecipe(plateFlux, "NNN", "GIG", "NNN", 'G', Items.DIAMOND, 'I', "ingotSignalum", 'N', "nuggetSignalum");
				RecipeHandler.addOreDictRecipe(armorFluxPlate, "I I", "III", "III", 'I', plateFlux);
				RecipeHandler.addOreDictRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotSignalum", 'H', "blockGlassHardened");
				RecipeHandler.addOreDictRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotSignalum", 'H', TEItems.signalumGlass); //TODO: Change Glowstone to lamp
			}

			Object ductFluxLeadstone = integrateTD ? TDItems.ductFluxLeadstone : "blockGlass";
			Object ductFluxHardened = integrateTD ? TDItems.ductFluxHardened : "blockGlass";
			Object ductFluxRedstoneEnergy = integrateTD ? TDItems.ductFluxRedstoneEnergy : "blockGlassHardened";
			Object ductFluxResonant = integrateTD ? TDItems.ductFluxResonant : "blockGlassHardened";

			RecipeHandler.addOreDictRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoNumismatic, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE5, "FPF", "GRG", 'G', unitGlowstone, 'P', plateFlux, 'R', thrusterTE4, 'F', "ingotSignalum");

			if (integrateRA) {
				RecipeHandler.addOreDictRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened");
				RecipeHandler.addOreDictRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.signalumGlass);
			}

			RecipeHandler.addOreDictRecipe(armorPlatingTE1, "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin");

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE1Armored, jetpackTE1, armorPlatingTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE2Armored, jetpackTE2, armorPlatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE3Armored, jetpackTE3, armorPlatingTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE4Armored, jetpackTE4, armorPlatingTE4));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5, "PAP", "OJO", "TCT", 'A', armorFluxPlate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxPackTE3Armored, 'T', thrusterTE5, 'P', plateFlux));

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackTE1, "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic, 'S', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackTE2, " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.cellReinforced, 'S', fluxPackTE1, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxPackTE3, " C ", "ISI", "LOL", 'I', "ingotEnderium", 'L', "ingotLead", 'C', TEItems.cellResonant, 'S', fluxPackTE2, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxPackTE2Armored, fluxPackTE2, armorPlatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxPackTE3Armored, fluxPackTE3, armorPlatingTE4));

			if(integrateRR){
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5Enderium, "PAP", "OJO", "TCT", 'A', RRItems.fluxGelidChestplate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxPackTE3Armored, 'T', thrusterTE5, 'P', plateFlux));
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5Enderium, "AJA", "AAA", "AAA", 'A', RRItems.plateArmorGelidEnderium, 'J', jetpackTE5));
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5Enderium, "J", "P", 'J', jetpackTE5Enderium, 'P', "particleCustomizer"));
			}

			RecipeHelper.addArmoredReverseRecipe(Jetpack.PACKS_TE, Jetpack.PACKS_TE_ARMORED, Fluxpack.TE_FLUXPACKS, Fluxpack.TE_FLUXPACKS_ARMORED);

			for (Jetpack jetpack : Jetpack.PACKS_TE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}
		}

		if (integrateVanilla) {
			RecipeHandler.addOreDictRecipe(thrusterVanilla1, " I ", "IFI", "IBI", 'I', Items.IRON_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
			RecipeHandler.addOreDictRecipe(thrusterVanilla2, " I ", "IFI", "IBI", 'I', Items.GOLD_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
			RecipeHandler.addOreDictRecipe(thrusterVanilla3, " I ", "IFI", "IBI", 'I', Items.DIAMOND, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla1, "IBI", "IJI", "T T", 'I', Items.IRON_INGOT, 'B', Items.COMPARATOR, 'T', thrusterVanilla1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla2, "IBI", "IJI", "T T", 'I', Items.GOLD_INGOT, 'B', Blocks.REDSTONE_BLOCK, 'T', thrusterVanilla2, 'J', jetpackVanilla1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla3, "IBI", "IJI", "T T", 'I', Items.DIAMOND, 'B', Blocks.REDSTONE_BLOCK, 'T', thrusterVanilla3, 'J', jetpackVanilla2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla1Armored, jetpackVanilla1, Items.IRON_CHESTPLATE));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla2Armored, jetpackVanilla2, Items.GOLDEN_CHESTPLATE));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla3Armored, jetpackVanilla3, Items.DIAMOND_CHESTPLATE));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla1, "J", 'J', jetpackVanilla1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla2, "J", 'J', jetpackVanilla2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla3, "J", 'J', jetpackVanilla3Armored));

			for (Jetpack jetpack : Jetpack.PACKS_VANILLA) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}
		}
		if (integrateMek) {
			//RecipeHandler.addOreDictRecipe(thrusterVanilla1, " I ", "IFI", "IBI", 'I', Items.IRON_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
			//ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla1, "IBI", "IJI", "T T", 'I', Items.IRON_INGOT, 'B', Items.COMPARATOR, 'T', thrusterVanilla1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek1Armored, jetpackMek1, armorPlatingMek1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek2Armored, jetpackMek2, armorPlatingMek2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek3Armored, jetpackMek3, armorPlatingMek3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek4Armored, jetpackMek4, armorPlatingMek4));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek1, "J", 'J', jetpackMek1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek2, "J", 'J', jetpackMek2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek3, "J", 'J', jetpackMek3Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek4, "J", 'J', jetpackMek4Armored));

			for (Jetpack jetpack : Jetpack.PACKS_MEK) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}
		}
		if (integrateIE) {
			//RecipeHandler.addOreDictRecipe(thrusterVanilla1, " I ", "IFI", "IBI", 'I', Items.IRON_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
			//ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackVanilla1, "IBI", "IJI", "T T", 'I', Items.IRON_INGOT, 'B', Items.COMPARATOR, 'T', thrusterVanilla1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE1Armored, jetpackIE1, armorPlatingIE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE2Armored, jetpackIE2, armorPlatingIE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE2Armored, jetpackIE3, armorPlatingIE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE1, "J", 'J', jetpackIE1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE2, "J", 'J', jetpackIE2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE3, "J", 'J', jetpackIE2Armored));

			for (Jetpack jetpack : Jetpack.PACKS_IE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}
		}
	}

	private static void doIMC() {
		SimplyJetpacks.logger.info("Performing inter-mod communication...");

		if (integrateEIO) {
			ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
			ingotConductiveIron.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1, ingotConductiveIron, null, armorPlatingEIO2);

			ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
			ingotElectricalSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2, ingotElectricalSteel, null, armorPlatingEIO3);

			ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3, ingotDarkSteel, null, armorPlatingEIO4);

			ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, ingotDarkSoularium);

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
		}

		if (integrateTE) {
			ItemStack i = OreDictionary.getOres("ingotBronze").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(3200, armorPlatingTE1, i, armorPlatingTE2, null, 0);

			i = OreDictionary.getOres("ingotInvar").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(4800, armorPlatingTE2, i, armorPlatingTE3, null, 0);

			i = OreDictionary.getOres("ingotEnderium").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(6400, armorPlatingTE3, i, armorPlatingTE4, null, 0);

			if (integrateRA || Config.addRAItemsIfNotInstalled) {
				TERecipes.addTransposerFill(6400, unitGlowstoneEmpty, unitGlowstone, new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
				TERecipes.addTransposerFill(6400, unitCryotheumEmpty, unitCryotheum, new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
			}
		}
	}
}
