package me.cybersteve777.magicofdepth.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import java.util.function.Consumer
import me.cybersteve777.magicofdepth.registry.RegisterItem.ECHORITE_INGOT
import me.cybersteve777.magicofdepth.registry.RegisterItem.WARDEN_HEART
import me.cybersteve777.magicofdepth.registry.RegisterItem.ECHO_SMITHING_TEMPLATE

class MagicOfDepthRecipeGenerator(output: FabricDataOutput) : FabricRecipeProvider(output) {

    fun offerEchoUpgradeRecipe(
        exporter: Consumer<RecipeJsonProvider?>?,
        input: Item,
        category: RecipeCategory?,
        result: Item?
    ) {
        SmithingTransformRecipeJsonBuilder.create(
            Ingredient.ofItems(*arrayOf<ItemConvertible>(ECHO_SMITHING_TEMPLATE)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>(input)),
            Ingredient.ofItems(
                *arrayOf<ItemConvertible>(
                    ECHORITE_INGOT
                )
            ),
            category,
            result
        ).criterion("has_echorite_ingot", conditionsFromItem(ECHORITE_INGOT))
            .offerTo(exporter, getItemPath(result) + "_smithing")
    }


    fun offerFinalUpgradeRecipe(
        exporter: Consumer<RecipeJsonProvider?>?,
        category: RecipeCategory?,
        result: Item?
    ) {
        SmithingTransformRecipeJsonBuilder.create(
            Ingredient.ofItems(*arrayOf<ItemConvertible>(ECHO_SMITHING_TEMPLATE)),
            Ingredient.ofItems(*arrayOf<ItemConvertible>()),
            Ingredient.ofItems(
                *arrayOf<ItemConvertible>(
                    WARDEN_HEART
                )
            ),
            category,
            result
        ).criterion("has_warden_heart", conditionsFromItem(WARDEN_HEART))
            .offerTo(exporter, getItemPath(result) + "_smithing")
    }

    override fun generate(exporter: Consumer<RecipeJsonProvider>?) {

    }

}