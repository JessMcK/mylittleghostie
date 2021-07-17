
package net.mcreator.mylittleghostie.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.mylittleghostie.MyLittleGhostieModElements;

@MyLittleGhostieModElements.ModElement.Tag
public class MusicDiscSeizeTheDayItem extends MyLittleGhostieModElements.ModElement {
	@ObjectHolder("my_little_ghostie:music_disc_seize_the_day")
	public static final Item block = null;
	public MusicDiscSeizeTheDayItem(MyLittleGhostieModElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, MyLittleGhostieModElements.sounds.get(new ResourceLocation("my_little_ghostie:seize_the_day_newsies")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("music_disc_seize_the_day");
		}
	}
}
