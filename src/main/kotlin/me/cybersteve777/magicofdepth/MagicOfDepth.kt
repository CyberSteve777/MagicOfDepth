package me.cybersteve777.magicofdepth

import net.fabricmc.api.ModInitializer
import me.cybersteve777.magicofdepth.util.LoggerUtil
import net.minecraft.util.Identifier
import net.minecraft.util.math.random.Random
import me.cybersteve777.magicofdepth.registry.*
val LOGGER = LoggerUtil.getLogger()


object MagicOfDepth : ModInitializer {
    const val MOD_ID = "magicofdepth"
	private val random = Random.createThreadSafe();
	private val kotlinRandom = kotlin.random.Random(System.currentTimeMillis())

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Magic of Depth: adding stuff...")
		RegisterItem.registerAll()
		RegisterEnchantments.registerAll()
	}

	fun aiRandom(): Random{
		return random
	}
	fun aiKotlinRandom(): kotlin.random.Random{
		return kotlinRandom
	}

	fun identity(path: String): Identifier {
		return Identifier(MOD_ID, path)
	}
}