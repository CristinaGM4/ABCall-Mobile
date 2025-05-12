package com.example.abcallmobile

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.abcallmobile.ChatbotActivity

@RunWith(AndroidJUnit4::class)
class ChatbotFlowTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ChatbotActivity::class.java)

    @Test
    fun crearIncidenteDesdeChatbot() {
        // 1. Escribe un mensaje en el input
        onView(withId(R.id.inputMensaje)).perform(typeText("No carga la página"))
        closeSoftKeyboard()

        // 2. Presiona el botón de enviar
        onView(withId(R.id.btnEnviar)).perform(click())

        // 3. Espera (opcional para que cargue respuesta del bot)
        Thread.sleep(1000)

        // 4. Clic en el botón "Crear incidente"
        onView(withId(R.id.btnCrearIncidente)).perform(click())

        // 5. Verifica que la descripción se haya pasado correctamente
        onView(withId(R.id.inputDescripcion))
            .check(matches(withText("No carga la página")))
    }
}