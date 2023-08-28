package me.cybersteve777.magicofdepth.registry

import me.cybersteve777.magicofdepth.registry.RegisterItem.DEPTH_EDGE
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text


object RegisterItemGroup {
    val MoDGroup = FabricItemGroup.builder().icon { ItemStack(DEPTH_EDGE) }.displayName(Text.translatable(
            "magic_of_depth.")).build()
}