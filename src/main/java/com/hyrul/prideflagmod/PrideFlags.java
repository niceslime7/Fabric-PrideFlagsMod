package com.hyrul.prideflagmod;

import com.hyrul.prideflagmod.block.ModBlocks;
import com.hyrul.prideflagmod.item.ModItems;
import com.hyrul.prideflagmod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrideFlags implements ModInitializer {
		public static final String MOD_ID = "prideflagmod";
		public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// registering new blocks & items
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		// adding content to the world chests
		ModLootTableModifiers.modifyLootTables();


		// Adding the patterns & flags to the merchant & wandering merchant trades list
		// define first, for loop then
		Item[] pridePatterns = {
			ModItems.PATTERN_BI,
			ModItems.PATTERN_GAY,
			ModItems.PATTERN_INTER,
			ModItems.PATTERN_LESB,
			ModItems.PATTERN_TRANS,
			ModItems.PATTERN_PRIDE,
			ModItems.PATTERN_PROGRESS,
			ModItems.PATTERN_POLYAMORY,
			ModItems.PATTERN_PANSEXUAL,
			ModItems.PATTERN_NONBINARY,
			ModItems.PATTERN_ASEXUAL,
			ModItems.PATTERN_AROMANTIC,
			ModItems.PATTERN_GENDERFLUID
		};

		Block[] prideFlags = {
				ModBlocks.FLAG_BI,
				ModBlocks.FLAG_GAY,
				ModBlocks.FLAG_INTER,
				ModBlocks.FLAG_LESB,
				ModBlocks.FLAG_TRANS,
				ModBlocks.FLAG_PRIDE,
				ModBlocks.FLAG_PROGRESS,
				ModBlocks.FLAG_POLYAMORY,
				ModBlocks.FLAG_PANSEXUAL,
				ModBlocks.FLAG_NONBINARY,
				ModBlocks.FLAG_ASEXUAL,
				ModBlocks.FLAG_AROMANTIC,
				ModBlocks.FLAG_GENDERFLUID
		};

		// items (patterns)
		for (Item pattern : pridePatterns) {
			Identifier id = Registries.ITEM.getId(pattern); // e.g. prideflags:bi_flag
			String offerKey = id.getPath(); // "bi_flag"

			TradeOfferHelper.registerWanderingTraderOffers(factories -> {
				factories.addAll(Identifier.of(PrideFlags.MOD_ID, offerKey), ((entity, random) -> new TradeOffer(
						new TradedItem(Items.EMERALD, 1),
						new ItemStack(pattern, 1), 5, 3, 0f)
				));
			});
		}


		// blocks (flags)
		for (Block flag : prideFlags) {
			Identifier id = Registries.BLOCK.getId(flag);
			String offerKey = id.getPath();

			TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 2, factories -> {
				factories.add(((entity, random) -> new TradeOffer(
						new TradedItem(Items.EMERALD, 2),
						new ItemStack(flag, 1), 10, 2, 0f)
				));
			});

			TradeOfferHelper.registerWanderingTraderOffers(factories -> {
				factories.addAll(Identifier.of(PrideFlags.MOD_ID, offerKey), ((entity, random) -> new TradeOffer(
						new TradedItem(Items.EMERALD, 2),
						new ItemStack(flag, 1), 5, 3, 0f)
				));
			});
		}

		// Making them flammable
		FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
		registry.add(ModBlocks.FLAG_BI, 30, 60);
		registry.add(ModBlocks.FLAG_TRANS, 30, 60);
		registry.add(ModBlocks.FLAG_GAY, 30, 60);
		registry.add(ModBlocks.FLAG_LESB, 30, 60);
		registry.add(ModBlocks.FLAG_INTER, 30, 60);
		registry.add(ModBlocks.FLAG_PRIDE, 30, 60);
		registry.add(ModBlocks.FLAG_PROGRESS, 30, 60);
		registry.add(ModBlocks.FLAG_POLYAMORY, 30, 60);
		registry.add(ModBlocks.FLAG_PANSEXUAL, 30, 60);
		registry.add(ModBlocks.FLAG_NONBINARY, 30, 60);
		registry.add(ModBlocks.FLAG_ASEXUAL, 30, 60);
		registry.add(ModBlocks.FLAG_AROMANTIC, 30, 60);
		registry.add(ModBlocks.FLAG_GENDERFLUID, 30, 60);
	}
}
