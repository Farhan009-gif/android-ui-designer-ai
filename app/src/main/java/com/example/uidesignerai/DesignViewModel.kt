package com.example.uidesignerai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DesignViewModel : ViewModel() {
    private val _components = MutableStateFlow<List<UIComponent>>(emptyList())
    val components: StateFlow<List<UIComponent>> = _components

    fun addComponent(type: String) {
        val newId = (_components.value.maxOfOrNull { it.id } ?: 0) + 1
        val comp = UIComponent(newId, type, "New $type")
        _components.value = _components.value + comp
    }

    fun suggestLayout() {
        viewModelScope.launch {
            // Simulate AI suggestion
            // In real app, call ML model or API
        }
    }

    fun exportDesign(): String {
        return _components.value.joinToString("\n") { "${it.type}: ${it.text}" }
    }
}