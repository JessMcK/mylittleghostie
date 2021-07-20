package net.mcreator.mylittleghostie.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;

import net.mcreator.mylittleghostie.MyLittleGhostieModElements;
import net.mcreator.mylittleghostie.MyLittleGhostieMod;

import java.util.Map;

@MyLittleGhostieModElements.ModElement.Tag
public class MyLittleGhostieOnEntityTickUpdateProcedure extends MyLittleGhostieModElements.ModElement {
	public MyLittleGhostieOnEntityTickUpdateProcedure(MyLittleGhostieModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency x for procedure MyLittleGhostieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency y for procedure MyLittleGhostieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency z for procedure MyLittleGhostieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency world for procedure MyLittleGhostieOnEntityTickUpdate!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((Math.round((Math.random() * 10000)) <= 1)) {
			if (world instanceof World && !world.isRemote()) {
				((World) world)
						.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("my_little_ghostie:ghostie_living")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("my_little_ghostie:ghostie_living")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
		}
	}
}
