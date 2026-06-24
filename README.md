# Proyecto de Automatización de Pruebas - DemoBlaze

Proyecto de automatización de QA utilizando **Selenium WebDriver**, **Java** y **JUnit 5**, aplicando el patrón de diseño **Page Object Model (POM)**.

## Repositorio del Proyecto

Puedes encontrar el código fuente y la documentación completa en:
https://github.com/pvbn11/DemoBlazeAutomation

## Estructura del Proyecto
- `src/main/java/org/paolovalladaresbazalar/`: Contiene las clases de las páginas (POM) que definen la estructura y acciones de la web.
- `src/test/java/`: Contiene las clases de prueba (`PurchaseFlowTest`) y configuración base (`BaseTest`).
- `screenshots/`: Directorio donde se guardan las evidencias de las pruebas.

## Requisitos
- Java JDK 21 o superior.
- Maven para la gestión de dependencias.
- Google Chrome instalado.
- IntelliJ IDEA u otro IDE compatible con Java.
- Selenium WebDriver para la automatización de la UI.
- JUnit 5 para la ejecución de pruebas.
- WebDriverManager para la gestión automática de los drivers del navegador.

## Patrón de Diseño: Page Object Model (POM)
Este proyecto utiliza POM para asegurar:
1. **Separación de responsabilidades**: La lógica de navegación de la UI está desacoplada de la lógica de los casos de prueba.
2. **Mantenibilidad**: Los cambios en la interfaz de usuario solo requieren actualizaciones en las clases de página.
3. **Legibilidad**: Los tests son descriptivos y fáciles de entender.

## Escenarios Automatizados
1. **testCompletePurchase**: Flujo completo de compra (selección de productos, validación de total y confirmación).
2. **testPurchaseWithEmptyFields**: Validación de campos obligatorios en el formulario de compra.
3. **testPurchaseWithInvalidData**: Caso negativo donde se ingresan caracteres especiales y letras para validar la integridad del formulario (identificación de bugs).

## Cómo ejecutar
Ejecuta el siguiente comando en la terminal desde la raíz del proyecto:
```bash
mvn test
