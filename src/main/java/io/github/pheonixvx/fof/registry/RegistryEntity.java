package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.entity.*;
import io.github.pheonixvx.fof.entity.projectiles.AbominationSkeletonProjectileEntity;
import io.github.pheonixvx.fof.entity.projectiles.BombEntity;
import io.github.pheonixvx.fof.entity.projectiles.BoomerangEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;

import java.util.Random;

@SuppressWarnings("deprecation")
public class RegistryEntity {

	// Projectiles
	public static final EntityType<BoomerangEntity> BOOMERANG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "boomerang_entity"),
		FabricEntityTypeBuilder.<BoomerangEntity>create(SpawnGroup.MISC, BoomerangEntity::new)
			.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	public static final EntityType<BombEntity> BOMB_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "bomb_entity"),
		FabricEntityTypeBuilder.<BombEntity>create(SpawnGroup.MISC, BombEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);
	
	public static final EntityType<AbominationSkeletonProjectileEntity> NETHER_ABOMINATION_SKELETON_PROJECTILE_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_nether_abomination_skeleton_arm"),
		FabricEntityTypeBuilder.create(SpawnGroup.MISC, AbominationSkeletonProjectileEntity::new)
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	/// Mobs
	public static final EntityType<DwellerBugEntity> DWELLER_BUG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_dweller_bug"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DwellerBugEntity::new)
			.dimensions(EntityDimensions.fixed(2.0f, 2.0f))
			.specificSpawnBlocks(Blocks.STONE)
			.build()
	);

	public static final EntityType<AbominationSkeletonEntity> ABOMINATION_SKELETON_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_abomination_skeleton"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AbominationSkeletonEntity::new)
			.dimensions(EntityDimensions.fixed(1.25f, 2.75f))
			.build()
	);

	public static final EntityType<NetherAbominationSkeletonEntity> NETHER_ABOMINATION_SKELETON_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_nether_abomination_skeleton"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NetherAbominationSkeletonEntity::new)
			.dimensions(EntityDimensions.fixed(1.25f, 2.75f))
			.build()
	);

	public static final EntityType<GoliathWolfEntity> GOLIATH_WOLF_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_goliath_wolf"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GoliathWolfEntity::new)
			.dimensions(EntityDimensions.fixed(1.75f, 2.50f))
			.build()
	);

	public static final EntityType<EldritchGownEntity> ELDRITCH_GOWN_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_eldritch_gown"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EldritchGownEntity::new)
			.dimensions(EntityDimensions.fixed(1.40F, 2.75F))
			.build()
	);

	public static void initializeEntities () {
		FabricDefaultAttributeRegistry.register(
			DWELLER_BUG_ENTITY_TYPE,
			DwellerBugEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.425F)
		);

		FabricDefaultAttributeRegistry.register(
			ABOMINATION_SKELETON_ENTITY_TYPE,
			AbominationSkeletonEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2625F)
		);

		FabricDefaultAttributeRegistry.register(
			NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			NetherAbominationSkeletonEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2625F)
		);

		FabricDefaultAttributeRegistry.register(
			GOLIATH_WOLF_ENTITY_TYPE,
			GoliathWolfEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 50)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35F)
		);

		FabricDefaultAttributeRegistry.register(
			ELDRITCH_GOWN_ENTITY_TYPE,
			EldritchGownEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
		);

		// Spawning Restrictions
		SpawnRestrictionAccessor.callRegister(
			DWELLER_BUG_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canDwellerBugSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			ABOMINATION_SKELETON_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			GOLIATH_WOLF_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			ELDRITCH_GOWN_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			DWELLER_BUG_ENTITY_TYPE,
			10,
			2,
			5
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			ABOMINATION_SKELETON_ENTITY_TYPE,
			15,
			4,
			6
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			15,
			4,
			6
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			GOLIATH_WOLF_ENTITY_TYPE,
			1,
			2,
			4
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			ELDRITCH_GOWN_ENTITY_TYPE,
			1,
			3,
			4
		);
	}

	public static boolean canDwellerBugSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		BlockPos blockPos = pos.down();
		if (world.getBlockState(blockPos).equals(Blocks.CAVE_AIR.getDefaultState())) {
			return spawnReason == SpawnReason.SPAWNER || world.getBlockState(blockPos).allowsSpawning(world, blockPos, type);
		} else {
			return false;
		}
	}
}
