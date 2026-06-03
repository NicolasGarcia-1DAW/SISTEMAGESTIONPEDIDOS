# Paquete `excluidosonar`

## ¿Por qué existe este paquete?

Este paquete contiene clases proporcionadas por nuestra profesora como material de apoyo
para probar y evaluar el estado del proyecto durante su desarrollo.

Las clases aquí incluidas **no forman parte del flujo principal** del Sistema de
Gestión de Pedidos (no están conectadas a `Tienda`, `Pedido`, `Cliente` ni `Factura`),
por lo que se han movido a este paquete separado por las siguientes razones:

- **Evitar falsos positivos en SonarQube**: al no estar integradas en el sistema,
  SonarQube las detectaría como código muerto, generando code smells y fallos técnicos
  que no son representativos de la calidad real del proyecto.
- **Mantener la cobertura de código limpia**: no tiene sentido exigir tests sobre
  clases que no pertenecen al diseño propio del proyecto.
- **Preservar el código original**: se ha optado por aislar en lugar de eliminar,
  para conservar el trabajo proporcionado como referencia y que se pueda ver que
  ha sido utilizado y probado.

## Clases incluidas

| Clase | Descripción |
|---|---|
| `CalculadoraFinanciera` | Utilidad financiera con cálculos de IVA, descuentos y comisiones |
| `ServicioFacturacion` | Servicio de facturación que coordina inventario y cálculos financieros |
| `GestorInventario` | Gestor de stock con reservas y confirmaciones de venta |

## Exclusión en SonarQube

Este paquete está excluido del análisis de SonarQube mediante la siguiente
configuración en `pom.xml`:

```xml
<properties>
    <sonar.exclusions>**/excluidosonar/**</sonar.exclusions>
</properties>
```

Esto significa que SonarQube **no analiza** estas clases: no generan code smells,
no afectan al porcentaje de cobertura ni a la deuda técnica del proyecto.