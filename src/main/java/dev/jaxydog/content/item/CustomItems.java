package dev.jaxydog.content.item;

import dev.jaxydog.content.block.CustomBlocks;
import dev.jaxydog.content.item.custom.*;
import dev.jaxydog.register.ContentRegistrar;
import dev.jaxydog.utility.ArmorMap;
import dev.jaxydog.utility.DyeableMap;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item.Settings;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

/** Contains definitions for all custom items */
public final class CustomItems extends ContentRegistrar {

	public static final CloudyItem CLOUDY_CANDY = new CloudyItem("cloudy_candy",
		new Settings().food(FoodComponents.CLOUDY_CANDY_FOOD).rarity(Rarity.UNCOMMON)
	);
	public static final CloudyItem CLOUDY_MANE = new CloudyItem("cloudy_mane", new Settings().rarity(Rarity.UNCOMMON));
	public static final CloudyItem CLOUDY_COTTON = new CloudyItem("cloudy_cotton",
		new Settings().rarity(Rarity.UNCOMMON)
	);
	public static final ArmorMap<DyeableCloudyArmorItem> CLOUD_ARMOR = new ArmorMap<>("cloudy",
		(rawId, type) -> new DyeableCloudyArmorItem(rawId, ArmorMaterials.CLOUDY, type, new Settings())
	);

	public static final MirrorItem MIRROR = new MirrorItem("mirror", new Settings().maxCount(1).rarity(Rarity.RARE));
	public static final PlaceholderItem PLACEHOLDER = new PlaceholderItem("placeholder",
		new Settings().fireproof().maxCount(1).rarity(Rarity.UNCOMMON)
	);

	public static final DyeableMap<CustomBlockItem> DYED_AMETHYST_BLOCKS = new DyeableMap<>("amethyst_block",
		(rawId, color) -> new CustomBlockItem(rawId, CustomBlocks.DYED_AMETHYST_BLOCKS.get(color), new Settings())
	);
	public static final DyeableMap<CustomBlockItem> DYED_AMETHYST_CLUSTERS = new DyeableMap<>("amethyst_cluster",
		(rawId, color) -> new CustomBlockItem(rawId,
			CustomBlocks.DYED_AMETHYST_CLUSTER_BLOCKS.get(color),
			new Settings()
		)
	);

	public static final SprayBottleItem SPRAY_BOTTLE = new SprayBottleItem("spray_bottle",
		new Settings().maxDamage(SprayBottleItem.SPRAY_USES)
	);
	public static final CustomBlockItem RANDOMIZER_BLOCK = new CustomBlockItem("randomizer",
		CustomBlocks.RANDOMIZER,
		new Settings().rarity(Rarity.UNCOMMON)
	);

	public static final ChocolateMilkItem CHOCOLATE_MILK = new ChocolateMilkItem("chocolate_milk",
		new Settings().food(FoodComponents.CHOCOLATE_MILK).maxCount(16));

	// Items for star monkey
	public static final CustomItem ROTTEN_CHORUS_FRUIT = new CustomItem("rotten_chorus_fruit",
		new Settings().food(FoodComponents.ROTTEN_CHORUS_FRUIT)
	);
	public static final CustomItem LIVING_SCULK = new CustomItem("living_sculk", new Settings().rarity(Rarity.RARE));
	public static final CustomItem PIG_CARD = new CustomItem("pig_card", new Settings());
	@SuppressWarnings("SpellCheckingInspection")
	public static final CustomItem APPY_SAUCE = new CustomItem("appy_sauce", new Settings());
	public static final CustomItem VOID_ESSENCE = new CustomItem("void_essence", new Settings().rarity(Rarity.EPIC));

	private static final class FoodComponents {

		public static final FoodComponent CHOCOLATE_MILK = new FoodComponent.Builder().alwaysEdible()
			.hunger(6)
			.saturationModifier(0.25F)
			.build();

		public static final FoodComponent CLOUDY_CANDY_FOOD = new FoodComponent.Builder().alwaysEdible()
			.hunger(2)
			.saturationModifier(0.45F)
			.snack()
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 0, false, true, true), 0.25F)
			.build();

		public static final FoodComponent ROTTEN_CHORUS_FRUIT = new FoodComponent.Builder().alwaysEdible()
			.hunger(1)
			.saturationModifier(0.5F)
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 1F)
			.statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 0), 1F)
			.build();

		private FoodComponents() {}

	}

	private static final class ArmorMaterials {

		public static final CustomArmorMaterial CLOUDY = CustomArmorMaterial.builder("cloud")
			.setDurability(52, 64, 60, 44)
			.setEnchantability(15)
			.setEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)
			.setProtectionAmount(2, 5, 3, 2)
			.setRepairIngredient(Ingredient.ofItems(CLOUDY_COTTON))
			.build();

		private ArmorMaterials() {}

	}

}
