package me.cybersteve777.magicofdepth.registry

import me.fzzyhmstrs.fzzy_core.coding_util.AbstractConfigDisableEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.entity.EquipmentSlot
import me.cybersteve777.magicofdepth.*
import me.cybersteve777.magicofdepth.spells.SonicBoomAugment
import me.cybersteve777.magicofdepth.enchantment.BaneOfDepth

object RegisterEnchantments {
    private fun <T: Enchantment> register(e: T, name: String): T{
        if (e is AbstractConfigDisableEnchantment){
            if (!e.isEnabled()){
                LOGGER.info("Augment ${e.translationKey} is set as disabled in the configs!")
            }
        }
        return Registry.register(Registries.ENCHANTMENT, MagicOfDepth.identity(name), e)
    }
    //vanilla enchantments

    val BANE_OF_DEPTH = register(BaneOfDepth(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND), "bane_of_depth")
    // spells
    val SONIC_BOOM = register(SonicBoomAugment(), "sonic_boom")

    fun registerAll() {
        LOGGER.info("Magic of depth: Registering enchantments")
    }
}