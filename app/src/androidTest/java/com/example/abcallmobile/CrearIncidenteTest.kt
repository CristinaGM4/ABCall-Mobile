package com.example.abcallmobile

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrearIncidenteTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(ListaIncidentesActivity::class.java)

    @Test
    fun crearIncidenteExitosamente() {
        // Ir a pantalla de nuevo incidente
        onView(withId(R.id.btnNuevoIncidente)).perform(click())

        // Escribir asunto y descripción
        // onView(withId(R.id.inputAsunto)).perform(typeText("Problema de red"), closeSoftKeyboard())
        // onView(withId(R.id.inputDescripcion)).perform(typeText("No hay conexión en el módulo 3"), closeSoftKeyboard())

        // Presionar crear
        onView(withId(R.id.btnCrearIncidente)).perform(click())

        // Confirmar que volvemos al listado (puedes verificar por el botón de "Nuevo Incidente")
        onView(withId(R.id.btnNuevoIncidente)).check(matches(isDisplayed()))
    }
}