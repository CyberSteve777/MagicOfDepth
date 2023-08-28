package me.cybersteve777.magicofdepth.registry

import me.cybersteve777.magicofdepth.LOGGER
import me.cybersteve777.magicofdepth.MagicOfDepth
import me.cybersteve777.magicofdepth.item.DepthEdge
import me.cybersteve777.magicofdepth.item.ECHORITE
import me.cybersteve777.magicofdepth.item.EchoScepter
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text

object RegisterItem {
    private val regItem: MutableList<Item> = mutableListOf()

    private fun <T: Item> register(item: T, name: String): T{
        regItem.add(item)
        return Registry.register(Registries.ITEM, MagicOfDepth.identity(name), item)
    }

    val ECHORITE_INGOT = register(Item(FabricItemSettings()), "echorite_ingot")
    val DEPTH_EDGE = register(DepthEdge(
        ECHORITE, 7, -2.4f,
        FabricItemSettings().fireproof()), "depth_edge")
    val ECHO_SCEPTER = register(EchoScepter(ECHORITE, FabricItemSettings()), "echo_scepter")
    val WARDEN_HEART = register(Item(FabricItemSettings()), "warden_heart")
    val ECHO_SMITHING_TEMPLATE = register(Item(FabricItemSettings()), "echo_smithing_template")


    fun registerAll() {
        registerItemGroup()
        LOGGER.info("Magic of depth: Registering items")
    }

    private fun registerItemGroup() {
        val MagicOfDepthGroup = FabricItemGroup.builder().icon { ItemStack(DEPTH_EDGE) }.displayName(
            Text.translatable(
            "magicofdepth.itemgroup")).entries { _, entries ->
                entries.addAll()
        }.build()
        Registry.register(Registries.ITEM_GROUP, MagicOfDepth.identity("item_group"), MagicOfDepthGroup)
    }
}