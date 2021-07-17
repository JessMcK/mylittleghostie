
package net.mcreator.mylittleghostie.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.mylittleghostie.MyLittleGhostieModElements;

@MyLittleGhostieModElements.ModElement.Tag
public class MusicDiscPirateItem extends MyLittleGhostieModElements.ModElement {
	@ObjectHolder("my_little_ghostie:music_disc_pirate")
	public static final Item block = null;
	public MusicDiscPirateItem(MyLittleGhostieModElements instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, MyLittleGhostieModElements.sounds.get(new ResourceLocation("my_little_ghostie:hes_a_pirate_music_box")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("music_disc_pirate");
		}
	}
}
