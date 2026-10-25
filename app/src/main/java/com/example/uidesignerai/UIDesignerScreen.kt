package com.example.uidesignerai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class UIComponent(val id: Int, val type: String, var text: String)

@Composable
fun UIDesignerScreen() {
    var components by remember { mutableStateOf(listOf(
        UIComponent(1, "Button", "Click Me"),
        UIComponent(2, "Card", "Sample Card")
    )) }
    var selectedId by remember { mutableStateOf<Int?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Palette
        Column(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(Color(0xFFF5F5F5))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Components", fontSize = 20.sp)
            ComponentItem("Button") { components = components + UIComponent(components.size + 1, "Button", "New Button") }
            ComponentItem("Card") { components = components + UIComponent(components.size + 1, "Card", "New Card") }
            ComponentItem("Text") { components = components + UIComponent(components.size + 1, "Text", "New Text") }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /* AI Suggest */ }, modifier = Modifier.fillMaxWidth()) {
                Text("AI Suggest Layout")
            }
        }

        // Canvas
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White)
                .padding(16.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(components) { comp ->
                    ComponentPreview(comp, selectedId == comp.id) {
                        selectedId = comp.id
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentItem(name: String, onAdd: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAdd() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(name, modifier = Modifier.padding(12.dp))
    }
}

@Composable
fun ComponentPreview(comp: UIComponent, selected: Boolean, onClick: () -> Unit) {
    val borderColor = if (selected) Color.Blue else Color.LightGray
    Column(
        modifier = Modifier
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(comp.type, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        when (comp.type) {
            "Button" -> Button(onClick = {}) { Text(comp.text) }
            "Card" -> Card { Text(comp.text, modifier = Modifier.padding(16.dp)) }
            else -> Text(comp.text, fontSize = 16.sp)
        }
    }
}