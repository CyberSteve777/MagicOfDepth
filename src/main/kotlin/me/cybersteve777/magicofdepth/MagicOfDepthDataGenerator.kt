package me.cybersteve777.magicofdepth

import me.cybersteve777.magicofdepth.data.MagicOfDepthLootTableGenerator
import me.cybersteve777.magicofdepth.data.MagicOfDepthModelProvider
import me.cybersteve777.magicofdepth.data.MagicOfDepthRecipeGenerator
import me.cybersteve777.magicofdepth.data.MagicOfDepthWorldGenerator
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature

import java.util.concurrent.CompletableFuture

object MagicOfDepthDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()
		pack.addProvider { dataOutput: FabricDataOutput? ->
			MagicOfDepthLootTableGenerator(
				dataOutput
			)
		}
		pack.addProvider { output: FabricDataOutput? ->
			MagicOfDepthRecipeGenerator(
				output
			)
		}
		pack.addProvider { output: FabricDataOutput? ->
			MagicOfDepthModelProvider(
				output
			)
		}
		pack.addProvider { output: FabricDataOutput?, registriesFuture: CompletableFuture<WrapperLookup?>? ->
			MagicOfDepthWorldGenerator(
				output,
				registriesFuture
			)
		}
	}

	override fun buildRegistry(registryBuilder: RegistryBuilder) {
		registryBuilder.addRegistry(
			RegistryKeys.CONFIGURED_FEATURE
		) { context: Registerable<ConfiguredFeature<*, *>?>? ->
			MythicConfiguredFeatures.bootstrap(
				context
			)
		}
		registryBuilder.addRegistry(
			RegistryKeys.PLACED_FEATURE
		) { context: Registerable<PlacedFeature?>? ->
			MythicPlacedFeatures.bootstrap(
				context
			)
		}
	}
}