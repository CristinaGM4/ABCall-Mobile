package com.example.abcallmobile

object IncidentData {
    fun getIncidents(): List<Incidente> = listOf(
        Incidente(1, "Servidor caído, necesito ayuda", "Cerrado", "27-sep-2024", "Knorr", "Alice Brown", "Lorem ipsum...", "28-sep-2024"),
        Incidente(2, "Consulta de cliente: Problemas de pago", "En curso", "27-sep-2024", "EmpresaX", "Jane Smith", "Lorem ipsum...", "28-sep-2024"),
        Incidente(3, "Fallo en los servicios", "En curso", "27-sep-2024", "EmpresaY", "Lina Gómez", "Lorem ipsum...", "28-sep-2024"),
        Incidente(4, "Revisar los comentarios de los clientes", "Registrado", "27-sep-2024", "EmpresaZ", "John Doe", "Lorem ipsum...", "28-sep-2024")
    )
}
