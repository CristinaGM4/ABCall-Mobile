# 📱 ABCall Mobile

Aplicación móvil Android del sistema **ABCall**, desarrollada como parte del proyecto de maestría en Ingeniería de Software. Esta aplicación permite registrar, consultar y gestionar incidentes en un sistema distribuido orientado al soporte de call centers.

---

## 📦 Características Principales

- Registro de incidentes asociados a usuarios y clientes.
- Consulta de lista de incidentes filtrados por cliente.
- Visualización de detalle de incidente.
- Integración con chatbot para sugerencia de soluciones.
- Configuración de notificaciones personalizadas.
- Pantalla de bienvenida y selección de cliente.
- Conexión con backend real vía API Gateway.

---

## ⚙️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin  
- **Framework:** Android SDK  
- **Arquitectura:** MVVM  
- **Dependencias:** Retrofit, Gson, Material Components  
- **CI/CD:** GitHub Actions para build, test y generación de APK  
- **Backend:** Conexión vía REST con [API Gateway ABCall](https://abcall-gateway-bwh34xmh.uc.gateway.dev/service/abcall/)

---

## 🚀 Instalación y Uso

### 1. Clonar el repositorio
```bash
git clone https://github.com/CristinaGM4/ABCall-Mobile.git
cd ABCall-Mobile
```

### 2. Abrir en Android Studio
- Requiere Android Studio **Hedgehog (2023.1)** o superior.
- Importa el proyecto y sincroniza dependencias.

### 3. Ejecutar la aplicación
- Conecta un dispositivo Android o emulador.
- Selecciona `app` como módulo de ejecución.
- Ejecuta con el botón ▶️ **Run**.

---

## 📲 APK de Release

El archivo `.apk` se genera automáticamente mediante GitHub Actions y se publica en la sección [Releases](https://github.com/CristinaGM4/ABCall-Mobile/releases) del repositorio.

También puedes compilarlo manualmente:
```bash
./gradlew assembleRelease
```

El APK se encontrará en:
```
app/build/outputs/apk/release/app-release.apk
```

---

## 🧪 Pruebas

- Pruebas unitarias ejecutadas automáticamente con cada push:
```bash
./gradlew testDebugUnitTest
```

- Reporte de cobertura disponible en:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

---
