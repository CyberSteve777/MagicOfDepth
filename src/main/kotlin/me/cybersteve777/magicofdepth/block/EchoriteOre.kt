package me.cybersteve777.magicofdepth.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


class EchoriteOreBlock (settings: Settings) : Block(settings) {
    override fun afterBreak(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        tool: ItemStack
    ) {
        player.addStatusEffect(StatusEffectInstance(StatusEffects.DARKNESS, 200))
        super.afterBreak(world, player, pos, state, blockEntity, tool)
    }
}