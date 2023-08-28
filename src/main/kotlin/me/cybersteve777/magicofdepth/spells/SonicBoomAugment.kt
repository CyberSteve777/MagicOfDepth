package me.cybersteve777.magicofdepth.spells

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.MinorSupportAugment
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Items
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class SonicBoomAugment: MinorSupportAugment(ScepterTier.THREE,1) {

    override val baseEffect: AugmentEffect
        get() = super.baseEffect
            .withDamage(16F)
            .withRange(10.0)

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,100,50,
            1, imbueLevel,1, LoreTier.NO_TIER, Items.ECHO_SHARD)
    }

    override fun supportEffect(
        world: World,
        target: Entity?,
        user: LivingEntity,
        level: Int,
        effects: AugmentEffect
    ): Boolean {
        return if(target != null) {
            if (target is LivingEntity) {

                val bl = target.damage(world.damageSources.sonicBoom(user),effects.damage(level))


                if (bl) {
                    effects.accept(target,AugmentConsumer.Type.HARMFUL)
                    effects.accept(user, AugmentConsumer.Type.BENEFICIAL)
                    if (world is ServerWorld){
                        val vec3d: Vec3d = user.pos.add(0.0, user.height/2.0, 0.0)
                        val vec3d2 = target.getEyePos().subtract(vec3d)
                        val vec3d3 = vec3d2.normalize()
                        for (i in 1 until MathHelper.floor(vec3d2.length()) + 7) {
                            val vec3d4 = vec3d.add(vec3d3.multiply(i.toDouble()))
                            world.spawnParticles(
                                ParticleTypes.SONIC_BOOM,
                                vec3d4.x,
                                vec3d4.y,
                                vec3d4.z,
                                1,
                                0.0,
                                0.0,
                                0.0,
                                0.0
                            )
                        }
                    }
                    world.playSound(null, target.blockPos, soundEvent(), SoundCategory.PLAYERS, 0.8F, 1.3F)
                }
                bl
            } else {
                false
            }
        } else {
            false
        }
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ENTITY_WARDEN_SONIC_BOOM
    }
}