package me.cybersteve777.magicofdepth.item


import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.amethyst_core.item_util.DefaultScepterItem
import net.minecraft.util.Identifier

class EchoScepter (
    material: ScepterToolMaterial,
    settings: Settings
) : DefaultScepterItem (material, settings ){
    override val fallbackId: Identifier
        get() = Identifier("magicofdepth", "echo_scepter")
}