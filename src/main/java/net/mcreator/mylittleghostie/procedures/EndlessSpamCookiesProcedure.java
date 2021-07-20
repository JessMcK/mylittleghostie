package net.mcreator.mylittleghostie.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.item.ItemEntity;

import net.mcreator.mylittleghostie.MyLittleGhostieModVariables;
import net.mcreator.mylittleghostie.MyLittleGhostieModElements;
import net.mcreator.mylittleghostie.MyLittleGhostieMod;

import java.util.Map;
import java.util.HashMap;

@MyLittleGhostieModElements.ModElement.Tag
public class EndlessSpamCookiesProcedure extends MyLittleGhostieModElements.ModElement {
	public EndlessSpamCookiesProcedure(MyLittleGhostieModElements instance) {
		super(instance, 11);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency x for procedure EndlessSpamCookies!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency y for procedure EndlessSpamCookies!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency z for procedure EndlessSpamCookies!");
			return;
		}
		if (dependencies.get("text") == null) {
			if (!dependencies.containsKey("text"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency text for procedure EndlessSpamCookies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency world for procedure EndlessSpamCookies!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		String text = (String) dependencies.get("text");
		IWorld world = (IWorld) dependencies.get("world");
		if (((text).contains("corncob"))) {
			for (int index0 = 0; index0 < (int) (500); index0++) {
				if ((Math.random() >= 0.7)) {
					if ((Math.random() >= 0.5)) {
						MyLittleGhostieModVariables.rand_increment_x = (double) (Math.random() * 10);
					} else {
						MyLittleGhostieModVariables.rand_increment_x = (double) (Math.random() * (-10));
					}
					if ((Math.random() >= 0.5)) {
						MyLittleGhostieModVariables.rand_increment_z = (double) (Math.random() * 10);
					} else {
						MyLittleGhostieModVariables.rand_increment_z = (double) (Math.random() * (-10));
					}
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, (x + (MyLittleGhostieModVariables.rand_increment_x)), (y + 2),
							(z + (MyLittleGhostieModVariables.rand_increment_z)), new ItemStack(Items.COOKIE, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			}
		}
	}

	@SubscribeEvent
	public void onChat(ServerChatEvent event) {
		ServerPlayerEntity entity = event.getPlayer();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("text", event.getMessage());
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
