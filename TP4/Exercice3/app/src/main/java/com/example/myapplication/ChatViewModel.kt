package com.example.myapplication


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val messages = mutableStateListOf<Message>()

    fun sendMessage(text: String, isUser: Boolean = true) {
        messages.add(Message(text, "user"))
        if (isUser) {
            viewModelScope.launch {
                val response = ApiService.openAIApi.generateResponse(OpenAIRequestBody(messages = messages))
                // Vérifiez si la réponse est disponible et si elle contient des choix
                if (response.choices.isNotEmpty()) {
                    // Ajoutez le message de la première réponse à la liste des messages
                    messages.add(response.choices.first().message)
                } else {
                }
            }
        }
    }
}



data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}