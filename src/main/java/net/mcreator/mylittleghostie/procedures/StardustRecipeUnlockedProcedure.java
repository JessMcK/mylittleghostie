package net.mcreator.mylittleghostie.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.mylittleghostie.MyLittleGhostieModElements;
import net.mcreator.mylittleghostie.MyLittleGhostieMod;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

@MyLittleGhostieModElements.ModElement.Tag
public class StardustRecipeUnlockedProcedure extends MyLittleGhostieModElements.ModElement {
	public StardustRecipeUnlockedProcedure(MyLittleGhostieModElements instance) {
		super(instance, 9);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency entity for procedure StardustRecipeUnlocked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
				? ((ServerPlayerEntity) entity).getAdvancements()
						.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
								.getAdvancement(new ResourceLocation("my_little_ghostie:stardust_unlocked")))
						.isDone()
				: false))) {
			if ((((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.BLAZE_POWDER, (int) (1)))
					: false)
					|| (((entity instanceof PlayerEntity)
							? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.FIREWORK_STAR, (int) (1)))
							: false)
							|| ((entity instanceof PlayerEntity)
									? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.GLOWSTONE_DUST, (int) (1)))
									: false)))) {
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("my_little_ghostie:stardust_unlocked"));
					AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onAdvancement(AdvancementEvent event) {
		PlayerEntity entity = event.getPlayer();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		Advancement advancement = event.getAdvancement();
		World world = entity.world;
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("advancement", advancement);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
