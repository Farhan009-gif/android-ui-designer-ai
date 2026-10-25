package com.example.uidesignerai

object AISuggestionEngine {
    fun suggestImprovement(components: List<UIComponent>): String {
        return when {
            components.isEmpty() -> "Add some components to start designing"
            components.count { it.type == "Button" } > 3 -> "Too many buttons, consider grouping actions"
            else -> "Layout looks good! Consider using more whitespace"
        }
    }
}