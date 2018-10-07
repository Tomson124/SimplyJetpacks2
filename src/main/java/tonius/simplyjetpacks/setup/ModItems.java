package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.crafting.UpgradingRecipeShapeless;
import tonius.simplyjetpacks.integration.*;
import tonius.simplyjetpacks.item.*;
import tonius.simplyjetpacks.util.crafting.RecipeHandler;
import tonius.simplyjetpacks.util.crafting.RecipeHelper;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID)
public abstract class ModItems {


	public static ItemFluxpack itemFluxPack;

	public static ItemMeta metaItem;
	public static ItemMetaMods metaItemMods;

	public static Item jetpackCreative = new ItemJetpack("jetpack_Creative");
	public static Item itemJetpackTest = new ItemJetpack("jetpack_Test");
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

	public static ItemStack thrusterVanilla1;
	public static ItemStack thrusterVanilla2;
	public static ItemStack thrusterVanilla3;

	//EnderIO Packs
	public static ItemStack armorPlatingEIO1;
	public static ItemStack armorPlatingEIO2;
	public static ItemStack armorPlatingEIO3;
	public static ItemStack armorPlatingEIO4;

	public static Item jetpackEIO1 = register(new ItemJetpack(Jetpack.JETPACK_EIO_1.getBaseName()));
	public static Item jetpackEIO2 = register(new ItemJetpack(Jetpack.JETPACK_EIO_2.getBaseName()));
	public static Item jetpackEIO3 = register(new ItemJetpack(Jetpack.JETPACK_EIO_3.getBaseName()));
	public static Item jetpackEIO4 = register(new ItemJetpack(Jetpack.JETPACK_EIO_4.getBaseName()));
	public static Item jetpackEIO5 = register(new ItemJetpack(Jetpack.JETPLATE_EIO_5.getBaseName()));
	public static Item jetpackEIO1Armored = register(new ItemJetpack(Jetpack.JETPACK_EIO_1_ARMORED.getBaseName()));
	public static Item jetpackEIO2Armored = register(new ItemJetpack(Jetpack.JETPACK_EIO_2_ARMORED.getBaseName()));
	public static Item jetpackEIO3Armored = register(new ItemJetpack(Jetpack.JETPACK_EIO_3_ARMORED.getBaseName()));
	public static Item jetpackEIO4Armored = register(new ItemJetpack(Jetpack.JETPACK_EIO_4_ARMORED.getBaseName()));

	public static ItemStack fluxPackEIO1;
	public static ItemStack fluxPackEIO2;
	public static ItemStack fluxPackEIO2Armored;
	public static ItemStack fluxPackEIO3;
	public static ItemStack fluxPackEIO3Armored;
	public static ItemStack fluxPackEIO4;
	public static ItemStack fluxPackEIO4Armored;

	//ThermalExpansion Packs
	public static ItemStack thrusterTE1;
	public static ItemStack thrusterTE2;
	public static ItemStack thrusterTE3;
	public static ItemStack thrusterTE4;
	public static ItemStack thrusterTE5;

	public static ItemStack armorPlatingTE1;
	public static ItemStack armorPlatingTE2;
	public static ItemStack armorPlatingTE3;
	public static ItemStack armorPlatingTE4;

	public static ItemStack unitGlowstone;
	public static ItemStack unitGlowstoneEmpty;
	public static ItemStack unitCryotheumEmpty;
	public static ItemStack unitCryotheum;
	public static ItemStack plateFlux;
	public static ItemStack armorFluxPlate;

	public static Item jetpackTE1 = new ItemJetpack(Jetpack.JETPACK_TE_1.getBaseName());
	public static Item jetpackTE2 = new ItemJetpack(Jetpack.JETPACK_TE_2.getBaseName());
	public static Item jetpackTE3 = new ItemJetpack(Jetpack.JETPACK_TE_3.getBaseName());
	public static Item jetpackTE4 = new ItemJetpack(Jetpack.JETPACK_TE_4.getBaseName());
	public static Item jetpackTE5 = new ItemJetpack(Jetpack.JETPLATE_TE_5.getBaseName());
	public static Item jetpackTE1Armored = new ItemJetpack(Jetpack.JETPACK_TE_1_ARMORED.getBaseName());
	public static Item jetpackTE2Armored = new ItemJetpack(Jetpack.JETPACK_TE_2_ARMORED.getBaseName());
	public static Item jetpackTE3Armored = new ItemJetpack(Jetpack.JETPACK_TE_3_ARMORED.getBaseName());
	public static Item jetpackTE4Armored = new ItemJetpack(Jetpack.JETPACK_TE_4_ARMORED.getBaseName());


	//RR
	public static Item jetpackTE5Enderium = new ItemJetpack(Jetpack.JETPLATE_TE_5_ENDERIUM.getBaseName());

	public static ItemStack fluxPackTE1;
	public static ItemStack fluxPackTE2;
	public static ItemStack fluxPackTE3;
	public static ItemStack fluxPackTE2Armored;
	public static ItemStack fluxPackTE3Armored;

	public static boolean integrateEIO = ModType.ENDER_IO.loaded && Config.enableIntegrationEIO;
	public static boolean integrateTE = ModType.THERMAL_EXPANSION.loaded && Config.enableIntegrationTE;
	public static boolean integrateTD = ModType.THERMAL_DYNAMICS.loaded && Config.enableIntegrationTD;
	public static boolean integrateRA = ModType.REDSTONE_ARSENAL.loaded && Config.enableIntegrationRA;
	public static boolean integrateRR = ModType.REDSTONE_REPOSITORY.loaded && Config.enableIntegrationRR;
	public static boolean integrateVanilla = Config.enableIntegrationVanilla;

	public static void init() {
		registerOreDicts();
		doIMC();
	}

	private static <T extends Item> T register(T item) {
		//GameRegistry.register(item);
		ForgeRegistries.ITEMS.register(item);

		if (item instanceof ItemFluxpack) {
			((ItemFluxpack) item).registerItemModel();
		}

		if (item instanceof ItemMeta) {
			((ItemMeta) item).registerItemModel();
		}

		if (item instanceof ItemMetaMods) {
			((ItemMetaMods) item).registerItemModel();
		}

		return item;
	}

	private static void registerOreDicts() {
		for (MetaItems item : MetaItems.PARTICLE_CUSTOMIZERS) {
			OreDictionary.registerOre("particleCustomizer", new ItemStack(metaItem, 1, item.ordinal()));

		}
		if (integrateEIO) {
			OreDictionary.registerOre(MetaItemsMods.INGOT_DARK_SOULARIUM.getName(), ingotDarkSoularium);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Log.info("Registering items...");
		IForgeRegistry<Item> r = event.getRegistry();

		//Jetpacks
		//itemJetpack = register(new ItemJetpack("itemJetpack"));
		r.register(itemJetpackTest);
		r.register(jetpackCreative);

		if (integrateEIO) {
			r.register(jetpackEIO1);
			r.register(jetpackEIO2);
			r.register(jetpackEIO3);
			r.register(jetpackEIO4);
			r.register(jetpackEIO5);
			r.register(jetpackEIO1Armored);
			r.register(jetpackEIO2Armored);
			r.register(jetpackEIO3Armored);
			r.register(jetpackEIO4Armored);
		}

		if (integrateTE) {
			r.register(jetpackTE1);
			r.register(jetpackTE2);
			r.register(jetpackTE3);
			r.register(jetpackTE4);
			r.register(jetpackTE5);
			r.register(jetpackTE1Armored);
			r.register(jetpackTE2Armored);
			r.register(jetpackTE3Armored);
			r.register(jetpackTE4Armored);

			if(integrateRR){
				r.register(jetpackTE5);
			}
		}

		//Meta Items
		metaItem = register(new ItemMeta("metaItem"));
		metaItemMods = register(new ItemMetaMods("metaItemMods"));

		//FluxPacks
		itemFluxPack = register(new ItemFluxpack("itemFluxpack"));

		//jetpackCreative = Jetpack.CREATIVE_JETPACK.getStackJetpack();
		fluxPackCreative = Fluxpack.CREATIVE_FLUXPACK.getStackFluxpack();

		particleDefault = MetaItems.PARTICLE_DEFAULT.getStackMetaItem();
		particleSmoke = MetaItems.PARTICLE_SMOKE.getStackMetaItem();
		particleNone = MetaItems.PARTICLE_NONE.getStackMetaItem();
		particleRainbowSmoke = MetaItems.PARTICLE_RAINBOWSMOKE.getStackMetaItem();

		leatherStrap = MetaItems.LEATHER_STRAP.getStackMetaItem();
	}

	public static void gatherIngredients(){
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

			fluxPackEIO1 = Fluxpack.FLUXPACK_EIO1.getStackFluxpack();
			fluxPackEIO2 = Fluxpack.FLUXPACK_EIO2.getStackFluxpack();
			fluxPackEIO3 = Fluxpack.FLUXPACK_EIO3.getStackFluxpack();
			fluxPackEIO2Armored = Fluxpack.FLUXPACK_EIO2_ARMORED.getStackFluxpack();
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
			}
			else if (integrateRA) {
				plateFlux = RAItems.fluxPlating;
				armorFluxPlate = RAItems.armorChestPlate;
			}

			unitGlowstone = MetaItemsMods.UNIT_GLOWSTONE.getStackMetaItem();
			unitGlowstoneEmpty = MetaItemsMods.UNIT_GLOWSTONE_EMTPY.getStackMetaItem();
			unitCryotheum = MetaItemsMods.UNIT_CRYOTHEUM.getStackMetaItem();
			unitCryotheumEmpty = MetaItemsMods.UNIT_CRYOTHEUM_EMTPY.getStackMetaItem();

			armorPlatingTE1 = MetaItemsMods.ARMOR_PLATING_TE_1.getStackMetaItem();
			armorPlatingTE2 = MetaItemsMods.ARMOR_PLATING_TE_2.getStackMetaItem();
			armorPlatingTE3 = MetaItemsMods.ARMOR_PLATING_TE_3.getStackMetaItem();
			armorPlatingTE4 = MetaItemsMods.ARMOR_PLATING_TE_4.getStackMetaItem();

			fluxPackTE1 = Fluxpack.FLUXPACK_TE1.getStackFluxpack();
			fluxPackTE2 = Fluxpack.FLUXPACK_TE2.getStackFluxpack();
			fluxPackTE3 = Fluxpack.FLUXPACK_TE3.getStackFluxpack();
			fluxPackTE2Armored = Fluxpack.FLUXPACK_TE2_ARMORED.getStackFluxpack();
			fluxPackTE3Armored = Fluxpack.FLUXPACK_TE3_ARMORED.getStackFluxpack();

		}
	}

	public static void registerRecipes() {
		gatherIngredients();
		RecipeHandler.addOreDictRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");
		//ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', "particleCustomizer"));

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

			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingot_dark_soularium", 'C', fluxPackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWings));

			/*for (Jetpack jetpack : Jetpack.PACKS_EIO) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}*/ //TODO: Particle Customizer Recipe

			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));

			/*ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO1, "CUC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'U', EIOItems.capacitorBankBasic, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO2, "CBC", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotVibrantAlloy", 'P', EIOItems.vibrantCrystal));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));*/
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

			RecipeHandler.addOreDictRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoSteam, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
			RecipeHandler.addOreDictRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', TEItems.bucketRedstone);
			RecipeHandler.addOreDictRecipe(thrusterTE5, "FPF", "GRG", 'G', unitGlowstone, 'P', plateFlux, 'R', thrusterTE4, 'F', "ingotSignalum");

			if (integrateRA) {
				RecipeHandler.addOreDictRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened");
				RecipeHandler.addOreDictRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.signalumGlass);
			}


			RecipeHandler.addOreDictRecipe(armorPlatingTE1, "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin");

			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));
			/*ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE1Armored, jetpackTE1, armorPlatingTE1));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE2Armored, jetpackTE2, armorPlatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE3Armored, jetpackTE3, armorPlatingTE3));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(jetpackTE4Armored, jetpackTE4, armorPlatingTE4));*/
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE5, "PAP", "OJO", "TCT", 'A', armorFluxPlate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxPackTE3Armored, 'T', thrusterTE5, 'P', plateFlux));


			/*ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackTE1, "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic, 'S', leatherStrap));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackTE2, " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.cellReinforced, 'S', fluxPackTE1, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipe(fluxPackTE3, " C ", "ISI", "LOL", 'I', "ingotEnderium", 'L', "ingotLead", 'C', TEItems.cellResonant, 'S', fluxPackTE2, 'O', TEItems.powerCoilElectrum));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxPackTE2Armored, fluxPackTE2, armorPlatingTE2));
			ForgeRegistries.RECIPES.register(new UpgradingRecipeShapeless(fluxPackTE3Armored, fluxPackTE3, armorPlatingTE4));*/

			if(integrateRR){
				ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpackTE5Enderium, "AAA", "AJA", "AAA", 'A', "ingotGelidEnderium", 'J', jetpackTE5));
			}

			/*RecipeHelper.addArmoredReverseRecipe(Jetpack.PACKS_TE, Jetpack.PACKS_TE_ARMORED, Fluxpack.TE_FLUXPACKS, Fluxpack.TE_FLUXPACKS_ARMORED);

			for (Jetpack jetpack : Jetpack.PACKS_TE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}*/
		}
	}

	private static void doIMC() {
		SimplyJetpacks.logger.info("Doing intermod communication");

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
