# Generador de ficheros RETO

## Antes de ejecutar la aplicación

Deposite los ficheros de **Excel (.xls)** dentro de la carpeta:

```text
Ficheros
```

### Importante

Los nombres de los ficheros deben contener la palabra:

* **Compras** → para movimientos de compras.
* **Ventas** → para movimientos de ventas.

Ejemplos válidos:

```text
250416 Movimientos de Compras por Propiedad.xls
250416 Movimientos de Ventas por Propiedad.xls
```

La aplicación **solo procesa ficheros Excel (.xls)**. No utilice ficheros CSV como entrada.

Puede depositar:

* Un fichero de Compras.
* Un fichero de Ventas.
* Ambos ficheros.

## Ejecución

1. Copie los ficheros Excel en la carpeta **Ficheros**.
2. Ejecute la aplicación.
3. Espere a que aparezca el mensaje de finalización.

## Resultado

Al finalizar:

* Se abrirá automáticamente la carpeta:

```text
CSVs
```

* En ella encontrará los ficheros generados para importar en RETO.

Ejemplos:

```text
CSV Compras 20260416 220759.csv
CSV Ventas 20260416 220845.csv
```

## Ficheros originales

Una vez procesados, los ficheros Excel originales serán movidos automáticamente a la carpeta:

```text
Historico
```

No es necesario moverlos ni eliminarlos manualmente.
