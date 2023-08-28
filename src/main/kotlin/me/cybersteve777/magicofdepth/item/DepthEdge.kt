package me.cybersteve777.magicofdepth.item


import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World


class DepthEdge (toolMaterial: ToolMaterial, attackDamage: Int, attackSpeed: Float, setting: Settings) : SwordItem (
    toolMaterial, attackDamage, attackSpeed, setting) {


    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.BLOCK
    }


    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        if (!world.isClient) {
            spawnSonicBoom(world, user)
            if (user is PlayerEntity) {
                user.itemCooldownManager[this] = 100
                stack.damage(1, user) { x -> x.sendToolBreakStatus(Hand.MAIN_HAND) }
            }
        }

        return super.finishUsing(stack, world, user)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 30
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        user.setCurrentHand(hand)
        return super.use(world, user, hand)
    }

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        super.usageTick(world, user, stack, remainingUseTicks)
        if (getMaxUseTime(stack) - remainingUseTicks == 1) {
            world.playSound(
                null,
                user.x,
                user.y,
                user.z,
                SoundEvents.ENTITY_WARDEN_SONIC_CHARGE,
                SoundCategory.BLOCKS,
                4.0f,
                1.0f
            )
        }
    }

    private fun spawnSonicBoom(world: World, user: LivingEntity) {
        world.playSound(
            null,
            user.x,
            user.y,
            user.z,
            SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
            SoundCategory.BLOCKS,
            8.0f,
            1.0f
        )
        val heightOffset = 1.6f
        val distance = 16
        val target = user.pos.add(user.rotationVector.multiply(distance.toDouble()))
        val source = user.pos.add(0.0, heightOffset.toDouble(), 0.0)
        val offsetToTarget = target.subtract(source)
        val normalized = offsetToTarget.normalize()
        val hit: MutableSet<Entity> = HashSet()
        for (particleIndex in 1 until MathHelper.floor(offsetToTarget.length()) + 7) {
            val particlePos = source.add(normalized.multiply(particleIndex.toDouble()))
            (world as ServerWorld).spawnParticles(
                ParticleTypes.SONIC_BOOM,
                particlePos.x,
                particlePos.y,
                particlePos.z,
                1,
                0.0,
                0.0,
                0.0,
                0.0
            )
            hit.addAll(
                world.getEntitiesByClass(
                    LivingEntity::class.java,
                    Box(
                        BlockPos(
                            particlePos.getX().toInt(),
                            particlePos.getY().toInt(),
                            particlePos.getZ().toInt()
                        )
                    ).expand(1.0)
                ) { it !is WolfEntity }
            )
        }
        hit.remove(user)
        for (hitTarget in hit) {
            if (hitTarget is LivingEntity) {
                hitTarget.damage(world.damageSources.sonicBoom(user), 16.0f)
                val vertical: Double =
                    0.5 * (1.0 - hitTarget.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE))
                val horizontal: Double =
                    2.5 * (1.0 - hitTarget.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE))
                hitTarget.addVelocity(
                    normalized.getX() * horizontal,
                    normalized.getY() * vertical,
                    normalized.getZ() * horizontal
                )
            }
        }
    }

}