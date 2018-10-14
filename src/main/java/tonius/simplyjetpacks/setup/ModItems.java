package tonius.simplyjetpacks.setup;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
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

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID)
public abstract class ModItems {


	public static ItemFluxpack itemFluxPack;

	public static ItemMeta metaItem;
	public static ItemIngredients metaItemMods;

	public static Item jetpackCreative = new ItemJetpack(Packs.CREATIVE_JETPACK.getBaseName());
	public static Item itemJetpackTest = new ItemJetpack("jetpack_Test");
	public static Item fluxPackCreative = new ItemFluxpack(Packs.CREATIVE_FLUXPACK.getBaseName());

	public static Item particleDefault = new ItemIngredients(ItemsSJ2.PARTICLE_DEFAULT.getName());
	public static Item particleSmoke = new ItemIngredients(ItemsSJ2.PARTICLE_SMOKE.getName());
	public static Item particleNone = new ItemIngredients(ItemsSJ2.PARTICLE_NONE.getName());
	public static Item particleRainbowSmoke = new ItemIngredients(ItemsSJ2.PARTICLE_RAINBOWSMOKE.getName());

	public static Item leatherStrap = new ItemIngredients(ItemsSJ2.LEATHER_STRAP.getName());

	//EnderIO Packs
	public static Item unitFlightControlEmpty = new ItemIngredients(ItemsSJ2.UNIT_FLIGHT_CONTROL_EMPTY.getName());
	public static Item unitFlightControl = new ItemIngredients(ItemsSJ2.UNIT_FLIGHT_CONTROL.getName());
	public static Item reinforcedGliderWings = new ItemIngredients(ItemsSJ2.REINFORCED_GLIDERWINGS.getName());
	public static Item ingotDarkSoularium = new ItemIngredients(ItemsSJ2.INGOT_DARK_SOULARIUM.getName());

	public static Item thrusterEIO1 = new ItemIngredients(ItemsSJ2.THRUSTER_EIO_1.getName());
	public static Item thrusterEIO2 = new ItemIngredients(ItemsSJ2.THRUSTER_EIO_2.getName());
	public static Item thrusterEIO3 = new ItemIngredients(ItemsSJ2.THRUSTER_EIO_3.getName());
	public static Item thrusterEIO4 = new ItemIngredients(ItemsSJ2.THRUSTER_EIO_4.getName());
	public static Item thrusterEIO5 = new ItemIngredients(ItemsSJ2.THRUSTER_EIO_5.getName());

	public static Item armorPlatingEIO1 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_EIO_1.getName());
	public static Item armorPlatingEIO2 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_EIO_2.getName());
	public static Item armorPlatingEIO3 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_EIO_3.getName());
	public static Item armorPlatingEIO4 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_EIO_4.getName());

	public static Item jetpackEIO1 = register(new ItemJetpack(Packs.JETPACK_EIO_1.getBaseName()));
	public static Item jetpackEIO2 = register(new ItemJetpack(Packs.JETPACK_EIO_2.getBaseName()));
	public static Item jetpackEIO3 = register(new ItemJetpack(Packs.JETPACK_EIO_3.getBaseName()));
	public static Item jetpackEIO4 = register(new ItemJetpack(Packs.JETPACK_EIO_4.getBaseName()));
	public static Item jetpackEIO5 = register(new ItemJetpack(Packs.JETPLATE_EIO_5.getBaseName()));
	public static Item jetpackEIO1Armored = register(new ItemJetpack(Packs.JETPACK_EIO_1_ARMORED.getBaseName()));
	public static Item jetpackEIO2Armored = register(new ItemJetpack(Packs.JETPACK_EIO_2_ARMORED.getBaseName()));
	public static Item jetpackEIO3Armored = register(new ItemJetpack(Packs.JETPACK_EIO_3_ARMORED.getBaseName()));
	public static Item jetpackEIO4Armored = register(new ItemJetpack(Packs.JETPACK_EIO_4_ARMORED.getBaseName()));

	public static Item fluxPackEIO1 = register(new ItemFluxpack(Packs.FLUXPACK_EIO1.getBaseName()));
	public static Item fluxPackEIO2 = register(new ItemFluxpack(Packs.FLUXPACK_EIO2.getBaseName()));
	public static Item fluxPackEIO3 = register(new ItemFluxpack(Packs.FLUXPACK_EIO3.getBaseName()));
	public static Item fluxPackEIO2Armored = register(new ItemFluxpack(Packs.FLUXPACK_EIO2_ARMORED.getBaseName()));
	public static Item fluxPackEIO3Armored = register(new ItemFluxpack(Packs.FLUXPACK_EIO3_ARMORED.getBaseName()));

	//ThermalExpansion Packs
	public static Item unitGlowstone = new ItemIngredients(ItemsSJ2.UNIT_GLOWSTONE.getName());
	public static Item unitGlowstoneEmpty = new ItemIngredients(ItemsSJ2.UNIT_GLOWSTONE_EMTPY.getName());
	public static Item unitCryotheumEmpty = new ItemIngredients(ItemsSJ2.UNIT_CRYOTHEUM.getName());
	public static Item unitCryotheum = new ItemIngredients(ItemsSJ2.UNIT_CRYOTHEUM_EMTPY.getName());
	public static Item plateFlux = new ItemIngredients(ItemsSJ2.PLATE_FLUX.getName());
	public static Item armorFluxPlate = new ItemIngredients(ItemsSJ2.ARMOR_PLATE_FLUX.getName());

	public static Item thrusterTE1 = new ItemIngredients(ItemsSJ2.THRUSTER_TE_1.getName());
	public static Item thrusterTE2 = new ItemIngredients(ItemsSJ2.THRUSTER_TE_2.getName());
	public static Item thrusterTE3 = new ItemIngredients(ItemsSJ2.THRUSTER_TE_3.getName());
	public static Item thrusterTE4 = new ItemIngredients(ItemsSJ2.THRUSTER_TE_4.getName());
	public static Item thrusterTE5 = new ItemIngredients(ItemsSJ2.THRUSTER_TE_5.getName());

	public static Item armorPlatingTE1 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_TE_1.getName());
	public static Item armorPlatingTE2 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_TE_2.getName());
	public static Item armorPlatingTE3 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_TE_3.getName());
	public static Item armorPlatingTE4 = new ItemIngredients(ItemsSJ2.ARMOR_PLATING_TE_4.getName());

	public static Item jetpackTE1 = new ItemJetpack(Packs.JETPACK_TE_1.getBaseName());
	public static Item jetpackTE2 = new ItemJetpack(Packs.JETPACK_TE_2.getBaseName());
	public static Item jetpackTE3 = new ItemJetpack(Packs.JETPACK_TE_3.getBaseName());
	public static Item jetpackTE4 = new ItemJetpack(Packs.JETPACK_TE_4.getBaseName());
	public static Item jetpackTE5 = new ItemJetpack(Packs.JETPLATE_TE_5.getBaseName());
	public static Item jetpackTE1Armored = new ItemJetpack(Packs.JETPACK_TE_1_ARMORED.getBaseName());
	public static Item jetpackTE2Armored = new ItemJetpack(Packs.JETPACK_TE_2_ARMORED.getBaseName());
	public static Item jetpackTE3Armored = new ItemJetpack(Packs.JETPACK_TE_3_ARMORED.getBaseName());
	public static Item jetpackTE4Armored = new ItemJetpack(Packs.JETPACK_TE_4_ARMORED.getBaseName());

	public static Item fluxPackTE1 = new ItemFluxpack(Packs.FLUXPACK_TE1.getBaseName());
	public static Item fluxPackTE2 = new ItemFluxpack(Packs.FLUXPACK_TE2.getBaseName());
	public static Item fluxPackTE3 = new ItemFluxpack(Packs.FLUXPACK_TE3.getBaseName());
	public static Item fluxPackTE2Armored = new ItemFluxpack(Packs.FLUXPACK_TE2_ARMORED.getBaseName());
	public static Item fluxPackTE3Armored = new ItemFluxpack(Packs.FLUXPACK_TE3_ARMORED.getBaseName());


	//RR
	public static Item jetpackTE5Enderium = new ItemJetpack(Packs.JETPLATE_TE_5_ENDERIUM.getBaseName());

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

		return item;
	}

	private static void registerOreDicts() {
		for (MetaItems item : MetaItems.PARTICLE_CUSTOMIZERS) {
			OreDictionary.registerOre("particleCustomizer", new ItemStack(metaItem, 1, item.ordinal()));

		}
		if (integrateEIO) {
			OreDictionary.registerOre(ItemsSJ2.INGOT_DARK_SOULARIUM.getName(), ingotDarkSoularium);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Log.info("Registering items...");
		IForgeRegistry<Item> r = event.getRegistry();

		r.register(itemJetpackTest);
		r.register(jetpackCreative);
		r.register(fluxPackCreative);

		r.register(particleDefault);
		r.register(particleSmoke);
		r.register(particleRainbowSmoke);
		r.register(particleNone);
		r.register(leatherStrap);

		if (integrateEIO) {
			r.register(unitFlightControlEmpty);
			r.register(unitFlightControl);
			r.register(reinforcedGliderWings);
			r.register(ingotDarkSoularium);

			r.register(thrusterEIO1);
			r.register(thrusterEIO2);
			r.register(thrusterEIO3);
			r.register(thrusterEIO4);
			r.register(thrusterEIO5);
			r.register(armorPlatingEIO1);
			r.register(armorPlatingEIO2);
			r.register(armorPlatingEIO3);
			r.register(armorPlatingEIO4);

			r.register(jetpackEIO1);
			r.register(jetpackEIO2);
			r.register(jetpackEIO3);
			r.register(jetpackEIO4);
			r.register(jetpackEIO5);
			r.register(jetpackEIO1Armored);
			r.register(jetpackEIO2Armored);
			r.register(jetpackEIO3Armored);
			r.register(jetpackEIO4Armored);

			r.register(fluxPackEIO1);
			r.register(fluxPackEIO2);
			r.register(fluxPackEIO3);
			r.register(fluxPackEIO2Armored);
			r.register(fluxPackEIO3Armored);
		}

		if (integrateTE) {
			r.register(unitCryotheumEmpty);
			r.register(unitCryotheum);
			r.register(unitGlowstoneEmpty);
			r.register(unitGlowstone);

			r.register(thrusterTE1);
			r.register(thrusterTE2);
			r.register(thrusterTE3);
			r.register(thrusterTE4);
			r.register(thrusterTE5);
			r.register(armorPlatingTE1);
			r.register(armorPlatingTE2);
			r.register(armorPlatingTE3);
			r.register(armorPlatingTE4);

			r.register(jetpackTE1);
			r.register(jetpackTE2);
			r.register(jetpackTE3);
			r.register(jetpackTE4);
			r.register(jetpackTE5);
			r.register(jetpackTE1Armored);
			r.register(jetpackTE2Armored);
			r.register(jetpackTE3Armored);
			r.register(jetpackTE4Armored);

			r.register(fluxPackTE1);
			r.register(fluxPackTE2);
			r.register(fluxPackTE3);
			r.register(fluxPackTE2Armored);
			r.register(fluxPackTE3Armored);

			TEItems.init();

			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				r.register(plateFlux);
				r.register(armorFluxPlate);
			}
			else if (integrateRA) {
				plateFlux = RAItems.fluxPlating.getItem();
				armorFluxPlate = RAItems.armorChestPlate.getItem();
				r.register(plateFlux);
				r.register(armorFluxPlate);
			}

			if(integrateRR){
				r.register(jetpackTE5);
			}
		}
	}

	@SubscribeEvent()
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) { //TODO: Use Json recipes for parts or all of the recipes? Recipe Factory?
		IForgeRegistry<IRecipe> r = evt.getRegistry();

		RecipeHandler.addShapedRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		RecipeHandler.addShapedRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		RecipeHandler.addShapedRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		RecipeHandler.addShapedRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		RecipeHandler.addShapedRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if (integrateEIO) {
			RecipeHandler.addShapedRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', "gearWood", 'S', "dustRedstone");
			RecipeHandler.addShapedRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			RecipeHandler.addShapedRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			RecipeHandler.addShapedRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotVibrantAlloy", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");

			//RecipeHandler.addOreDictRecipe(thrusterEIO5, "SES", "CTC", "   ", 'T', thrusterEIO4, 'S', "ingot_dark_soularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);

			RecipeHandler.addShapedRecipe(reinforcedGliderWings, "  S", " SP", "SPP", 'S', "ingot_dark_soularium", 'P', armorPlatingEIO2);
			RecipeHandler.addShapedRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingot_dark_soularium", 'H', "blockGlassHardened");

			RecipeHandler.addShapedRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

			r.register(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			r.register(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			r.register(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			r.register(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			r.register(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingot_dark_soularium", 'C', fluxPackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWings));


			r.register(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			r.register(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			r.register(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			r.register(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			r.register(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingot_dark_soularium", 'C', fluxPackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWings));

			/*for (Packs jetpack : Packs.PACKS_EIO) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}*/ //TODO: Particle Customizer Recipe

			r.register(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			r.register(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			r.register(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			r.register(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			r.register(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			r.register(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			r.register(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
			r.register(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));

			r.register(new UpgradingRecipe(fluxPackEIO1, "CUC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'U', EIOItems.capacitorBankBasic, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			r.register(new UpgradingRecipe(fluxPackEIO2, "CBC", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
			r.register(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotVibrantAlloy", 'P', EIOItems.vibrantCrystal));
			r.register(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO2));
			r.register(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			r.register(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO3));
			r.register(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
		}

		if (integrateTE) {
			if (!integrateRA && Config.addRAItemsIfNotInstalled) {
				RecipeHandler.addShapedRecipe(plateFlux, "NNN", "GIG", "NNN", 'G', Items.DIAMOND, 'I', "ingotSignalum", 'N', "nuggetSignalum");
				RecipeHandler.addShapedRecipe(armorFluxPlate, "I I", "III", "III", 'I', plateFlux);
				RecipeHandler.addShapedRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotSignalum", 'H', "blockGlassHardened");
				RecipeHandler.addShapedRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotSignalum", 'H', TEItems.signalumGlass); //TODO: Change Glowstone to lamp
			}

			Object ductFluxLeadstone = integrateTD ? TDItems.ductFluxLeadstone : "blockGlass";
			Object ductFluxHardened = integrateTD ? TDItems.ductFluxHardened : "blockGlass";
			Object ductFluxRedstoneEnergy = integrateTD ? TDItems.ductFluxRedstoneEnergy : "blockGlassHardened";
			Object ductFluxResonant = integrateTD ? TDItems.ductFluxResonant : "blockGlassHardened";

			RecipeHandler.addShapedRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoSteam, 'R', "dustRedstone");
			RecipeHandler.addShapedRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
			RecipeHandler.addShapedRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', TEItems.bucketRedstone);
			RecipeHandler.addShapedRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', TEItems.bucketRedstone);
			RecipeHandler.addShapedRecipe(thrusterTE5, "FPF", "GRG", 'G', unitGlowstone, 'P', plateFlux, 'R', thrusterTE4, 'F', "ingotSignalum");

			if (integrateRA) {
				RecipeHandler.addShapedRecipe(unitCryotheumEmpty, "FTF", "THT", "FTF", 'T', "ingotTin", 'F', "ingotElectrumFlux", 'H', "blockGlassHardened");
				RecipeHandler.addShapedRecipe(unitGlowstoneEmpty, "FLF", "LHL", "FLF", 'L', "ingotLumium", 'F', "ingotElectrumFlux", 'H', TEItems.signalumGlass);
			}


			RecipeHandler.addShapedRecipe(armorPlatingTE1, "TIT", "III", "TIT", 'I', "ingotIron", 'T', "ingotTin");

			r.register(new UpgradingRecipe(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
			r.register(new UpgradingRecipe(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
			r.register(new UpgradingRecipe(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
			r.register(new UpgradingRecipe(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));
			r.register(new UpgradingRecipeShapeless(jetpackTE1Armored, jetpackTE1, armorPlatingTE1));
			r.register(new UpgradingRecipeShapeless(jetpackTE2Armored, jetpackTE2, armorPlatingTE2));
			r.register(new UpgradingRecipeShapeless(jetpackTE3Armored, jetpackTE3, armorPlatingTE3));
			r.register(new UpgradingRecipeShapeless(jetpackTE4Armored, jetpackTE4, armorPlatingTE4));
			r.register(new UpgradingRecipe(jetpackTE5, "PAP", "OJO", "TCT", 'A', armorFluxPlate, 'J', jetpackTE4Armored, 'O', unitCryotheum, 'C', fluxPackTE3Armored, 'T', thrusterTE5, 'P', plateFlux));


			r.register(new UpgradingRecipe(fluxPackTE1, "ICI", "ISI", 'I', "ingotLead", 'C', TEItems.cellBasic, 'S', leatherStrap));
			r.register(new UpgradingRecipe(fluxPackTE2, " C ", "ISI", "LOL", 'I', "ingotElectrum", 'L', "ingotLead", 'C', TEItems.cellReinforced, 'S', fluxPackTE1, 'O', TEItems.powerCoilElectrum));
			r.register(new UpgradingRecipe(fluxPackTE3, " C ", "ISI", "LOL", 'I', "ingotEnderium", 'L', "ingotLead", 'C', TEItems.cellResonant, 'S', fluxPackTE2, 'O', TEItems.powerCoilElectrum));
			r.register(new UpgradingRecipeShapeless(fluxPackTE2Armored, fluxPackTE2, armorPlatingTE2));
			r.register(new UpgradingRecipeShapeless(fluxPackTE3Armored, fluxPackTE3, armorPlatingTE4));

			if(integrateRR){
				r.register(new UpgradingRecipe(jetpackTE5Enderium, "AAA", "AJA", "AAA", 'A', "ingotGelidEnderium", 'J', jetpackTE5));
			}

			/*RecipeHelper.addArmoredReverseRecipe(Packs.PACKS_TE, Packs.PACKS_TE_ARMORED, Fluxpack.TE_FLUXPACKS, Fluxpack.TE_FLUXPACKS_ARMORED);

			for (Packs jetpack : Packs.PACKS_TE) {
				ForgeRegistries.RECIPES.register(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}*/
		}
	}

	private static void doIMC() {
		SimplyJetpacks.logger.info("Doing intermod communication");

		if (integrateEIO) {
			ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
			ingotConductiveIron.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, new ItemStack(armorPlatingEIO1), ingotConductiveIron, null, new ItemStack(armorPlatingEIO2));

			ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
			ingotElectricalSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, new ItemStack(armorPlatingEIO2), ingotElectricalSteel, null, new ItemStack(armorPlatingEIO3));

			ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, new ItemStack(armorPlatingEIO3), ingotDarkSteel, null, new ItemStack(armorPlatingEIO4));

			ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
			ingotDarkSteel.setCount(10);
			EIORecipes.addAlloySmelterRecipe("Dark Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, new ItemStack(ingotDarkSoularium));

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", new ItemStack(unitFlightControlEmpty), new ItemStack(unitFlightControl));
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
