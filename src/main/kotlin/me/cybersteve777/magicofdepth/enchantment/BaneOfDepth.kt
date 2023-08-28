package me.cybersteve777.magicofdepth.enchantment


import net.minecraft.enchantment.DamageEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity



class BaneOfDepth (weight: Rarity, vararg slot: EquipmentSlot): DamageEnchantment(weight, 3, *slot) {
    override fun isAvailableForEnchantedBookOffer(): Boolean {
        return false
    }

    override fun isAvailableForRandomSelection(): Boolean {
        return false
    }

    override fun canAccept(other: Enchantment): Boolean {
        return other !is BaneOfDepth
    }

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (target is LivingEntity) {
            if (target.type.equals(EntityType.WARDEN)) {
                target.damage(user.damageSources.generic(), 10f + (level * 6))
            }
        }
    }

    override fun getMaxLevel(): Int {
        return 5
    }
}