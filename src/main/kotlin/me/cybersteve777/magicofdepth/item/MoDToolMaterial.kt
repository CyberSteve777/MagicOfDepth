package me.cybersteve777.magicofdepth.item

import net.minecraft.recipe.Ingredient
import me.cybersteve777.magicofdepth.registry.RegisterItem.ECHORITE_INGOT
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial


class MoDToolMaterial(
    private val miningLevel: Int, private val itemDurability: Int, private val miningSpeed: Float,
    private val attackDamage: Float, private val enchantability: Int, private val attackSpeed: Double,
    private val scepterTier: Int, private val healCooldown: Long, private val repairIngredient: Ingredient,
) : ScepterToolMaterial() {


    //    static {}
    override fun getAttackSpeed(): Double {
        return attackSpeed
    }

    override fun scepterTier(): Int {
        return scepterTier
    }

    override fun healCooldown(): Long {
        return healCooldown
    }

    override fun getDurability(): Int {
        return itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return miningSpeed
    }

    override fun getAttackDamage(): Float {
        return attackDamage
    }

    override fun getMiningLevel(): Int {
        return miningLevel
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient
    }
}


val ECHORITE = MoDToolMaterial(4, 2150, 10.5f, 5.0f, 18, -3.0,
    3, 80, Ingredient.ofItems(*arrayOf(ECHORITE_INGOT)));
