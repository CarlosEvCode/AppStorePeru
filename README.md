# AppStorePeru (Android Client)

Aplicación Android para gestionar los productos de una tienda consumiendo el backend `storeperuWS` (Express/MySQL).

## Requisitos Previos

1. Tener corriendo el backend `storeperuWS` en tu máquina local (`node index.js`).
2. Asegurarte de que ambos dispositivos (tu PC donde corre la API y tu celular/emulador Android) estén en la **misma red Wi-Fi**.

## Instalación y Ejecución

1. Abrir este proyecto (`AppStorePeru`) en **Android Studio**.
2. Esperar a que Gradle sincronice las dependencias (Volley, etc.).
3. **Paso Crítico:** Actualizar la IP del backend.
   - Abre la terminal de tu PC (CMD/Powershell en Windows) y ejecuta `ipconfig` (o `ifconfig` en Linux/Mac).
   - Busca tu dirección IPv4 (Ej: `192.168.1.XX`).
   - Ve a los archivos `Listar.java` y `Registrar.java` y reemplaza la IP `192.168.1.XX` por tu IP actual en las variables `URL` / `URL_PRODUCTOS` / `URL_MARCAS`.
4. Conectar tu celular por USB (o usar un emulador) y darle al botón verde de "Run" (Shift + F10) en Android Studio.

## Funcionalidades Actuales
- **Listar:** Muestra todos los productos y el nombre de su marca vinculada usando `GET`.
- **Registrar:** Permite agregar un nuevo producto, seleccionando la Marca desde un Spinner (llenado por el backend con `GET`) y enviando los datos mediante un `POST` en formato JSON.
