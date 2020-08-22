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
	public static ItemFluxpack itemFluxpack;
	public static ItemMeta metaItem;
	public static ItemMetaMods metaItemMods;

	// Simply Jetpacks
	public static ItemStack jetpackCreative;
	public static ItemStack fluxpackCreative;
	public static ItemStack particleDefault;
	public static ItemStack particleNone;
	public static ItemStack particleSmoke;
	public static ItemStack particleRainbow;
	public static ItemStack leatherStrap;
	public static ItemPilotGoggles pilotGoggles;

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
	public static ItemStack unitFlightControl;
	public static ItemStack reinforcedGliderWing;
	public static ItemStack ingotDarkSoularium;
	public static ItemStack armorplatingEIO1;
	public static ItemStack armorplatingEIO2;
	public static ItemStack armorplatingEIO3;
	public static ItemStack armorplatingEIO4;
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
	public static ItemStack fluxpackEIO1;
	public static ItemStack fluxpackEIO1Armored;
	public static ItemStack fluxpackEIO2;
	public static ItemStack fluxpackEIO2Armored;
	public static ItemStack fluxpackEIO3;
	public static ItemStack fluxpackEIO3Armored;

	// Thermal Expansion
	public static ItemStack unitGlowstoneEmpty;
	public static ItemStack unitGlowstone;
	public static ItemStack unitCryotheumEmpty;
	public static ItemStack unitCryotheum;
	public static ItemStack fluxPlate;
	public static ItemStack fluxArmorplating;
	public static ItemStack armorplatingTE1;
	public static ItemStack armorplatingTE2;
	public static ItemStack armorplatingTE3;
	public static ItemStack armorplatingTE4;
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
	public static ItemStack fluxpackTE1;
	public static ItemStack fluxpackTE1Armored;
	public static ItemStack fluxpackTE2;
	public static ItemStack fluxpackTE2Armored;
	public static ItemStack fluxpackTE3;
	public static ItemStack fluxpackTE3Armored;
	public static ItemStack fluxpackTE4;
	public static ItemStack fluxpackTE4Armored;

	// Mekanism
	public static ItemStack armorplatingMek1;
	public static ItemStack armorplatingMek2;
	public static ItemStack armorplatingMek3;
	public static ItemStack armorplatingMek4;
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
	public static ItemStack armorplatingIE1;
	public static ItemStack armorplatingIE2;
	public static ItemStack armorplatingIE3;
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
		performIMC();
	}

	private static <T extends Item> T register(T item) {
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
		SimplyJetpacks.logger.info("Registering Items...");

		// Jetpacks / Fluxpacks
		itemJetpack = register(new ItemJetpack("itemJetpack"));
		itemFluxpack = register(new ItemFluxpack("itemFluxpack"));
		jetpackCreative = Jetpack.JETPACK_CREATIVE.getStackJetpack();
		fluxpackCreative = Fluxpack.FLUXPACK_CREATIVE.getStackFluxpack();

		// Meta Items
		metaItem = register(new ItemMeta("metaItem"));
		metaItemMods = register(new ItemMetaMods("metaItemMods"));
		particleDefault = MetaItems.PARTICLE_DEFAULT.getStackMetaItem();
		particleNone = MetaItems.PARTICLE_NONE.getStackMetaItem();
		particleSmoke = MetaItems.PARTICLE_SMOKE.getStackMetaItem();
		particleRainbow = MetaItems.PARTICLE_RAINBOW.getStackMetaItem();
		leatherStrap = MetaItems.LEATHER_STRAP.getStackMetaItem();
		pilotGoggles = register(new ItemPilotGoggles("pilot_goggles"));
	}

	public static void gatherIngredients() {
		if (integrateEIO) {
			ingotDarkSoularium = MetaItemsMods.INGOT_DARK_SOULARIUM.getStackMetaItem();
			unitFlightControlEmpty = MetaItemsMods.UNIT_FLIGHT_CONTROL_EMPTY.getStackMetaItem();
			reinforcedGliderWing = MetaItemsMods.REINFORCED_GLIDER_WING.getStackMetaItem();
			unitFlightControl = MetaItemsMods.UNIT_FLIGHT_CONTROL.getStackMetaItem();

			thrusterEIO1 = MetaItemsMods.THRUSTER_EIO_1.getStackMetaItem();
			thrusterEIO2 = MetaItemsMods.THRUSTER_EIO_2.getStackMetaItem();
			thrusterEIO3 = MetaItemsMods.THRUSTER_EIO_3.getStackMetaItem();
			thrusterEIO4 = MetaItemsMods.THRUSTER_EIO_4.getStackMetaItem();
			thrusterEIO5 = MetaItemsMods.THRUSTER_EIO_5.getStackMetaItem();

			armorplatingEIO1 = MetaItemsMods.ARMOR_PLATING_EIO_1.getStackMetaItem();
			armorplatingEIO2 = MetaItemsMods.ARMOR_PLATING_EIO_2.getStackMetaItem();
			armorplatingEIO3 = MetaItemsMods.ARMOR_PLATING_EIO_3.getStackMetaItem();
			armorplatingEIO4 = MetaItemsMods.ARMOR_PLATING_EIO_4.getStackMetaItem();

			jetpackEIO1 = Jetpack.JETPACK_EIO_1.getStackJetpack();
			jetpackEIO1Armored = Jetpack.JETPACK_EIO_1_ARMORED.getStackJetpack();
			jetpackEIO2 = Jetpack.JETPACK_EIO_2.getStackJetpack();
			jetpackEIO2Armored = Jetpack.JETPACK_EIO_2_ARMORED.getStackJetpack();
			jetpackEIO3 = Jetpack.JETPACK_EIO_3.getStackJetpack();
			jetpackEIO3Armored = Jetpack.JETPACK_EIO_3_ARMORED.getStackJetpack();
			jetpackEIO4 = Jetpack.JETPACK_EIO_4.getStackJetpack();
			jetpackEIO4Armored = Jetpack.JETPACK_EIO_4_ARMORED.getStackJetpack();
			jetpackEIO5 = Jetpack.JETPLATE_EIO_5.getStackJetpack();

			fluxpackEIO1 = Fluxpack.FLUXPACK_EIO1.getStackFluxpack();
			fluxpackEIO1Armored = Fluxpack.FLUXPACK_EIO1_ARMORED.getStackFluxpack();
			fluxpackEIO2 = Fluxpack.FLUXPACK_EIO2.getStackFluxpack();
			fluxpackEIO2Armored = Fluxpack.FLUXPACK_EIO2_ARMORED.getStackFluxpack();
			fluxpackEIO3 = Fluxpack.FLUXPACK_EIO3.getStackFluxpack();
			fluxpackEIO3Armored = Fluxpack.FLUXPACK_EIO3_ARMORED.getStackFluxpack();
		}

		if (integrateTE) {
			TEItems.init();
			thrusterTE1 = MetaItemsMods.THRUSTER_TE_1.getStackMetaItem();
			thrusterTE2 = MetaItemsMods.THRUSTER_TE_2.getStackMetaItem();
			thrusterTE3 = MetaItemsMods.THRUSTER_TE_3.getStackMetaItem();
			thrusterTE4 = MetaItemsMods.THRUSTER_TE_4.getStackMetaItem();
			thrusterTE5 = MetaItemsMods.THRUSTER_TE_5.getStackMetaItem();

			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				fluxPlate = MetaItemsMods.FLUX_PLATE.getStackMetaItem();
				fluxArmorplating = MetaItemsMods.FLUX_ARMOR_PLATING.getStackMetaItem();
			} else if (integrateRA) {
				fluxPlate = RAItems.fluxArmorplating;
				fluxArmorplating = RAItems.fluxPlate;
			}

			unitGlowstoneEmpty = MetaItemsMods.UNIT_GLOWSTONE_EMPTY.getStackMetaItem();
			unitGlowstone = MetaItemsMods.UNIT_GLOWSTONE.getStackMetaItem();
			unitCryotheumEmpty = MetaItemsMods.UNIT_CRYOTHEUM_EMPTY.getStackMetaItem();
			unitCryotheum = MetaItemsMods.UNIT_CRYOTHEUM.getStackMetaItem();

			armorplatingTE1 = MetaItemsMods.ARMOR_PLATING_TE_1.getStackMetaItem();
			armorplatingTE2 = MetaItemsMods.ARMOR_PLATING_TE_2.getStackMetaItem();
			armorplatingTE3 = MetaItemsMods.ARMOR_PLATING_TE_3.getStackMetaItem();
			armorplatingTE4 = MetaItemsMods.ARMOR_PLATING_TE_4.getStackMetaItem();

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

			fluxpackTE1 = Fluxpack.FLUXPACK_TE1.getStackFluxpack();
			fluxpackTE1Armored = Fluxpack.FLUXPACK_TE1_ARMORED.getStackFluxpack();
			fluxpackTE2 = Fluxpack.FLUXPACK_TE2.getStackFluxpack();
			fluxpackTE2Armored = Fluxpack.FLUXPACK_TE2_ARMORED.getStackFluxpack();
			fluxpackTE3 = Fluxpack.FLUXPACK_TE3.getStackFluxpack();
			fluxpackTE3Armored = Fluxpack.FLUXPACK_TE3_ARMORED.getStackFluxpack();
			fluxpackTE4 = Fluxpack.FLUXPACK_TE4.getStackFluxpack();
			fluxpackTE4Armored = Fluxpack.FLUXPACK_TE4_ARMORED.getStackFluxpack();
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
			armorplatingMek1 = MetaItemsMods.ARMOR_PLATING_MEK_1.getStackMetaItem();
			armorplatingMek2 = MetaItemsMods.ARMOR_PLATING_MEK_2.getStackMetaItem();
			armorplatingMek3 = MetaItemsMods.ARMOR_PLATING_MEK_3.getStackMetaItem();
			armorplatingMek4 = MetaItemsMods.ARMOR_PLATING_MEK_4.getStackMetaItem();
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
			armorplatingIE1 = MetaItemsMods.ARMOR_PLATING_IE_1.getStackMetaItem();
			armorplatingIE2 = MetaItemsMods.ARMOR_PLATING_IE_2.getStackMetaItem();
			armorplatingIE3 = MetaItemsMods.ARMOR_PLATING_IE_3.getStackMetaItem();
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
		RecipeHandler.addOreDictRecipe(new ItemStack(pilotGoggles), " S ", "GIG", 'S', leatherStrap, 'I', "ingotGold", 'G', "paneGlass");
		ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackCreative, jetpackCreative, "particle_customizer"));

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		RecipeHandler.addOreDictRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		RecipeHandler.addOreDictRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		RecipeHandler.addOreDictRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		RecipeHandler.addOreDictRecipe(particleRainbow, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if (integrateEIO) {
			RecipeHandler.addOreDictRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', "gearWood", 'S', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			RecipeHandler.addOreDictRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotVibrantAlloy", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");
			RecipeHandler.addOreDictRecipe(thrusterEIO5, "SES", "CTC", "   ", 'T', thrusterEIO4, 'S', "ingot_dark_soularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);

			RecipeHandler.addOreDictRecipe(reinforcedGliderWing, "  S", " SP", "SPP", 'S', "ingot_dark_soularium", 'P', armorplatingEIO2);
			RecipeHandler.addOreDictRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingot_dark_soularium", 'H', "blockGlassHardened");

			RecipeHandler.addOreDictRecipe(armorplatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingot_dark_soularium", 'C', fluxpackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWing));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackEIO1Armored, jetpackEIO1, armorplatingEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackEIO2Armored, jetpackEIO2, armorplatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackEIO3Armored, jetpackEIO3, armorplatingEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackEIO4Armored, jetpackEIO4, armorplatingEIO4));

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackEIO1, "CUC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'U', EIOItems.capacitorBankBasic, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackEIO2, "CBC", "ISI", "IPI", 'S', fluxpackEIO1, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackEIO3, "CBC", "ISI", "IPI", 'S', fluxpackEIO2, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotVibrantAlloy", 'P', EIOItems.vibrantCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackEIO1Armored, fluxpackEIO1, armorplatingEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackEIO2Armored, fluxpackEIO2, armorplatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackEIO3Armored, fluxpackEIO3, armorplatingEIO3));

			RecipeHelper.addArmoredReverseRecipe(Jetpack.JETPACKS_EIO, Jetpack.JETPACKS_EIO_ARMORED, Fluxpack.FLUXPACKS_EIO, Fluxpack.FLUXPACKS_EIO_ARMORED);

			for (Jetpack jetpack : Jetpack.JETPACKS_EIO) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpack.getStackJetpack(), jetpack.getStackJetpack(), "particle_customizer"));
			}
		}

		if (integrateTE) {
			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				RecipeHandler.addOreDictRecipe(fluxPlate, "I I", "III", "III", 'I', fluxArmorplating);
				RecipeHandler.addOreDictRecipe(fluxArmorplating, "NNN", "GIG", "NNN", 'G', Items.DIAMOND, 'I', "ingotSignalum", 'N', "nuggetSignalum");
				RecipeHandler.addOreDictRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotSignalum", 'H', TEItems.signalumGlass);
				RecipeHandler.addOreDictRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotSignalum", 'H', "blockGlassHardened");
			}

			Object ductFluxLeadstone = integrateTD ? TDItems.ductFluxLeadstone : "blockGlass";
			Object ductFluxHardened = integrateTD ? TDItems.ductFluxHardened : "blockGlass";
			Object ductFluxRedstoneEnergy = integrateTD ? TDItems.ductFluxRedstoneEnergy : "blockGlassHardened";
			Object ductFluxResonant = integrateTD ? TDItems.ductFluxResonant : "blockGlassHardened";

			RecipeHandler.addOreDictRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoNumismatic, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE5, "FPF", "GRG", 'G', unitGlowstone, 'P', fluxPlate, 'R', thrusterTE4, 'F', "ingotSignalum");

			if (integrateRA) {
				RecipeHandler.addOreDictRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened");
				RecipeHandler.addOreDictRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.signalumGlass);
			}

			RecipeHandler.addOreDictRecipe(armorplatingTE1, "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin");

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5, "PAP", "OJO", "TCT", 'A', fluxArmorplating, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxpackTE4Armored, 'T', thrusterTE5, 'P', fluxPlate));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE1Armored, jetpackTE1, armorplatingTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE2Armored, jetpackTE2, armorplatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE3Armored, jetpackTE3, armorplatingTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE4Armored, jetpackTE4, armorplatingTE4));

			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackTE1, "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic, 'S', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackTE2, " C ", "ISI", "LOL", 'I', "ingotInvar", 'L', "ingotLead", 'C', TEItems.cellHardened, 'S', fluxpackTE1, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackTE3, " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.cellReinforced, 'S', fluxpackTE2, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpackTE4, " C ", "ISI", "LOL", 'I', "ingotEnderium", 'L', "ingotLead", 'C', TEItems.cellResonant, 'S', fluxpackTE3, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackTE1Armored, fluxpackTE1, armorplatingTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackTE2Armored, fluxpackTE2, armorplatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackTE3Armored, fluxpackTE3, armorplatingTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxpackTE4Armored, fluxpackTE4, armorplatingTE4));

			if (integrateRR) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5Enderium, "PAP", "OJO", "TCT", 'A', RRItems.fluxGelidChestplate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxpackTE4Armored, 'T', thrusterTE5, 'P', fluxPlate));
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackTE5Enderium, "AJA", "AAA", "AAA", 'A', RRItems.plateArmorGelidEnderium, 'J', jetpackTE5));
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE5Enderium, jetpackTE5Enderium, "particle_customizer"));
			}

			RecipeHelper.addArmoredReverseRecipe(Jetpack.JETPACKS_TE, Jetpack.JETPACKS_TE_ARMORED, Fluxpack.FLUXPACKS_TE, Fluxpack.FLUXPACKS_TE_ARMORED);

			for (Jetpack jetpack : Jetpack.JETPACKS_TE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpack.getStackJetpack(), jetpack.getStackJetpack(), "particle_customizer"));
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
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla1, jetpackVanilla1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla2, jetpackVanilla2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackVanilla3, jetpackVanilla3Armored));

			for (Jetpack jetpack : Jetpack.JETPACKS_VANILLA) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpack.getStackJetpack(), jetpack.getStackJetpack(), "particle_customizer"));
			}
		}

		if (integrateMek) {
			RecipeHandler.addOreDictRecipe(armorplatingMek1, "ICI", "CIC", "ICI", 'I', "ingotOsmium", 'C', MekItems.circuitBasic);
			RecipeHandler.addOreDictRecipe(armorplatingMek2, "ICI", "CIC", "ICI", 'I', "ingotOsmium", 'C', MekItems.circuitAdvanced);
			RecipeHandler.addOreDictRecipe(armorplatingMek3, "ICI", "CIC", "ICI", 'I', "ingotOsmium", 'C', MekItems.circuitElite);
			RecipeHandler.addOreDictRecipe(armorplatingMek4, "ICI", "CIC", "ICI", 'I', "ingotOsmium", 'C', MekItems.circuitUltimate);
			RecipeHandler.addOreDictRecipe(thrusterMek1, "ICI", "UEU", "IAI", 'I', "ingotOsmium", 'C', MekItems.circuitBasic, 'U', MekItems.cableBasic, 'E', MekItems.cubeBasic, 'A', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterMek2, "ICI", "UEU", "IAI", 'I', "ingotOsmium", 'C', MekItems.circuitAdvanced, 'U', MekItems.cableAdvanced, 'E', MekItems.cubeAdvanced, 'A', MekItems.alloyEnriched);
			RecipeHandler.addOreDictRecipe(thrusterMek3, "ICI", "UEU", "IAI", 'I', "ingotOsmium", 'C', MekItems.circuitElite, 'U', MekItems.cableElite, 'E', MekItems.cubeElite, 'A', MekItems.alloyReinforced);
			RecipeHandler.addOreDictRecipe(thrusterMek4, "ICI", "UEU", "IAI", 'I', "ingotOsmium", 'C', MekItems.circuitUltimate, 'U', MekItems.cableUltimate, 'E', MekItems.cubeUltimate, 'A', MekItems.alloyAtomic);
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek1, "ICI", "ISI", "T T", 'I', "ingotOsmium", 'C', MekItems.circuitBasic, 'S', leatherStrap, 'T', thrusterMek1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek2, "ICI", "IJI", "T T", 'I', "ingotOsmium", 'C', MekItems.circuitAdvanced, 'J', jetpackMek1, 'T', thrusterMek2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek3, "ICI", "IJI", "T T", 'I', "ingotOsmium", 'C', MekItems.circuitElite, 'J', jetpackMek2, 'T', thrusterMek3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackMek4, "ICI", "IJI", "T T", 'I', "ingotOsmium", 'C', MekItems.circuitUltimate, 'J', jetpackMek3, 'T', thrusterMek4));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek1Armored, jetpackMek1, armorplatingMek1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek2Armored, jetpackMek2, armorplatingMek2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek3Armored, jetpackMek3, armorplatingMek3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek4Armored, jetpackMek4, armorplatingMek4));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek1, jetpackMek1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek2, jetpackMek2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek3, jetpackMek3Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackMek4, jetpackMek4Armored));

			for (Jetpack jetpack : Jetpack.JETPACKS_MEK) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpack.getStackJetpack(), jetpack.getStackJetpack(), "particle_customizer"));
			}
		}

		if (integrateIE) {
			RecipeHandler.addOreDictRecipe(armorplatingIE1, "FFF", "FWF", "FFF", 'F', IEItems.hempFabric, 'W', Blocks.WOOL);
			RecipeHandler.addOreDictRecipe(armorplatingIE2, "PIP", "IPI", "PIP", 'P', "plateIron", 'I', "ingotIron");
			RecipeHandler.addOreDictRecipe(armorplatingIE3, "PIP", "IPI", "PIP", 'P', "plateSteel", 'I', "ingotSteel");
			RecipeHandler.addOreDictRecipe(thrusterIE1, "PMP", "WCW", "PHP", 'P', "plateIron", 'M', IEItems.componentIron, 'W', IEItems.coilCopper, 'C', IEItems.wireCoilLv, 'H', IEItems.furnace_heater);
			RecipeHandler.addOreDictRecipe(thrusterIE2, "PMP", "WCW", "PHP", 'P', "plateAluminum", 'M', IEItems.electronTube, 'W', IEItems.coilElectrum, 'C', IEItems.wireCoilMv, 'H', IEItems.furnace_heater);
			RecipeHandler.addOreDictRecipe(thrusterIE3, "PMP", "WCW", "PHP", 'P', "plateSteel", 'M', IEItems.circuitBoard, 'W', IEItems.coilSteel, 'C', IEItems.wireCoilHv, 'H', IEItems.furnace_heater);
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE1, "PMP", "PSP", "T T", 'P', "plateIron", 'M', IEItems.componentIron, 'S', leatherStrap, 'T', thrusterIE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE2, "PMP", "PJP", "T T", 'P', "plateAluminum", 'M', IEItems.electronTube, 'J', jetpackIE1, 'T', thrusterIE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpackIE3, "PMP", "PJP", "T T", 'P', "plateSteel", 'M', IEItems.circuitBoard, 'J', jetpackIE2, 'T', thrusterIE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE1Armored, jetpackIE1, armorplatingIE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE2Armored, jetpackIE2, armorplatingIE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE3Armored, jetpackIE3, armorplatingIE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE1, jetpackIE1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE2, jetpackIE2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackIE3, jetpackIE3Armored));

			for (Jetpack jetpack : Jetpack.JETPACKS_IE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpack.getStackJetpack(), jetpack.getStackJetpack(), "particle_customizer"));
			}
		}
	}

	private static void performIMC() {
		SimplyJetpacks.logger.info("Performing IMC...");

		if (integrateEIO) {
			ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
			ingotConductiveIron.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorplatingEIO1, ingotConductiveIron, null, armorplatingEIO2);

			ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
			ingotElectricalSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorplatingEIO2, ingotElectricalSteel, null, armorplatingEIO3);

			ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorplatingEIO3, ingotDarkSteel, null, armorplatingEIO4);

			ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, ingotDarkSoularium);

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
		}

		if (integrateTE) {
			ItemStack i = OreDictionary.getOres("ingotBronze").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(3200, armorplatingTE1, i, armorplatingTE2, null, 0);

			i = OreDictionary.getOres("ingotInvar").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(4800, armorplatingTE2, i, armorplatingTE3, null, 0);

			i = OreDictionary.getOres("ingotEnderium").get(0).copy();
			i.setCount(10);
			TERecipes.addSmelterRecipe(6400, armorplatingTE3, i, armorplatingTE4, null, 0);

			if (integrateRA || Config.addRAItemsIfNotInstalled) {
				TERecipes.addTransposerFill(6400, unitGlowstoneEmpty, unitGlowstone, new FluidStack(FluidRegistry.getFluid("glowstone"), 4000), false);
				TERecipes.addTransposerFill(6400, unitCryotheumEmpty, unitCryotheum, new FluidStack(FluidRegistry.getFluid("cryotheum"), 4000), false);
			}
		}
	}
}
