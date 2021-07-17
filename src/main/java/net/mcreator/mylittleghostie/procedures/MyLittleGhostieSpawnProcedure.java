package net.mcreator.mylittleghostie.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Hand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.mylittleghostie.item.StardustItem;
import net.mcreator.mylittleghostie.entity.MyLittleGhostieEntity;
import net.mcreator.mylittleghostie.MyLittleGhostieModElements;
import net.mcreator.mylittleghostie.MyLittleGhostieMod;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@MyLittleGhostieModElements.ModElement.Tag
public class MyLittleGhostieSpawnProcedure extends MyLittleGhostieModElements.ModElement {
	public MyLittleGhostieSpawnProcedure(MyLittleGhostieModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency entity for procedure MyLittleGhostieSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency x for procedure MyLittleGhostieSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency y for procedure MyLittleGhostieSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency z for procedure MyLittleGhostieSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MyLittleGhostieMod.LOGGER.warn("Failed to load dependency world for procedure MyLittleGhostieSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(StardustItem.block, (int) (1))) : false)
				&& (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount()) >= 3))) {
			if (world instanceof ServerWorld) {
				Entity entityToSpawn = new MyLittleGhostieEntity.CustomEntity(MyLittleGhostieEntity.entity, (World) world);
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if ((((Entity) world
					.getEntitiesWithinAABB(MyLittleGhostieEntity.CustomEntity.class,
							new AxisAlignedBB(x - (16 / 2d), y - (16 / 2d), z - (16 / 2d), x + (16 / 2d), y + (16 / 2d), z + (16 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof TameableEntity) && (entity instanceof PlayerEntity)) {
				((TameableEntity) ((Entity) world
						.getEntitiesWithinAABB(MyLittleGhostieEntity.CustomEntity.class,
								new AxisAlignedBB(x - (16 / 2d), y - (16 / 2d), z - (16 / 2d), x + (16 / 2d), y + (16 / 2d), z + (16 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null))).setTamed(true);
				((TameableEntity) ((Entity) world
						.getEntitiesWithinAABB(MyLittleGhostieEntity.CustomEntity.class,
								new AxisAlignedBB(x - (16 / 2d), y - (16 / 2d), z - (16 / 2d), x + (16 / 2d), y + (16 / 2d), z + (16 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null))).setTamedBy((PlayerEntity) entity);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).giveExperiencePoints((int) 5);
			if ((!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity)))) {
				if (entity instanceof LivingEntity) {
					ItemStack _setstack = new ItemStack(StardustItem.block, (int) (1));
					_setstack.setCount(
							(int) (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount())
									- 3));
					((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You'll need 3 stardust to summon a ghostie."), (true));
			}
		}
	}
}
