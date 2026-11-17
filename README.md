# 🧩 Compilador para ENS2001 | Procesadores de Lenguajes II

**Proyecto académico · Grado en Ingeniería Informática (UNED) · Curso 2023–2024**

![Java](https://img.shields.io/badge/Java-compiler-blue) ![JFlex](https://img.shields.io/badge/JFlex-análisis%20léxico-informational) ![Java CUP](https://img.shields.io/badge/Java%20CUP-análisis%20sintáctico-informational) ![UNED](https://img.shields.io/badge/UNED-proyecto%20académico-orange)

## 📖 Descripción

Implementación de un **compilador en Java** como práctica de la asignatura **[Procesadores de Lenguajes II — UNED](https://www.uned.es/universidad/inicio/estudios/grados/grado-en-ingenieria-informatica/asignaturas.html?codTitulacion=7101&codAsignatura=71013118&idContenido=1)** del Grado en Ingeniería Informática de la **Universidad Nacional de Educación a Distancia (UNED)**, durante el curso 2023–2024.

El proyecto procesa programas escritos en un lenguaje de enseñanza con extensión `.ces`, incorpora fases de análisis léxico, sintáctico y semántico, construye representaciones de código intermedio y genera **código ensamblador para la arquitectura ENS2001** (`.ens`). Utiliza el entorno de compiladores proporcionado para la asignatura (`compiler-api.jar`).

Se publica como **proyecto formativo y muestra de competencias técnicas**, no como compilador de uso industrial.

## ✨ Funcionalidades y conceptos implementados

- **Análisis léxico:** especificación de tokens en `scanner.flex`, procesamiento mediante **JFlex** y seguimiento de línea y columna; incluye tratamiento de comentarios y errores léxicos.
- **Análisis sintáctico:** gramática y acciones semánticas descritas en `parser.cup`, con generación del parser mediante **Java CUP**.
- **Análisis semántico:** comprobaciones de tipos, declaraciones, símbolos y ámbitos utilizando la infraestructura de la asignatura.
- **Código intermedio:** representación mediante cuádruplas, temporales, variables, etiquetas y procedimientos.
- **Generación de código final:** traducción de operaciones intermedias a instrucciones **ENS2001**, con gestión de registros, memoria y llamadas a subprogramas.
- **Construcciones del lenguaje:** funciones, parámetros, expresiones, asignaciones, condicionales, bucles, alternativas y operaciones de entrada/salida definidas en la gramática.
- **Pruebas:** programas `.ces` y salidas `.ens` almacenados en `doc/test/`, incluidos ejemplos de factorial y Fibonacci.

> La presencia de estas construcciones en el código no implica que todos los casos posibles estén completamente soportados o libres de errores.

## 🏗️ Arquitectura

```text
Programa fuente (.ces)
         │
         ▼
  Análisis léxico           JFlex · scanner.flex
         │ tokens
         ▼
  Análisis sintáctico       Java CUP · parser.cup
         │
         ▼
  Análisis semántico        Tipos · símbolos · ámbitos
         │
         ▼
  Código intermedio        Cuádruplas · temporales · etiquetas
         │
         ▼
  Generación de código     ExecutionEnvironmentEns2001
         │
         ▼
 Código ENS2001 (.ens)
```

El componente `CompilerContext` centraliza el acceso a los gestores de errores, ámbitos, fábrica de código final y entorno de ejecución.

## 📁 Estructura del repositorio

```text
.
├── src/compiler/
│   ├── code/               # Generación de código ENS2001 y descriptores
│   ├── intermediate/       # Variables, temporales, etiquetas y procedimientos
│   ├── lexical/            # Scanner y tokens
│   ├── semantic/           # Representaciones de símbolos y tipos
│   ├── syntax/             # Parser y clases de producciones gramaticales
│   ├── test/               # Ejecutables de pruebas por fase
│   └── CompilerContext.java
├── doc/
│   ├── config/build.xml    # Construcción y pruebas con Apache Ant
│   ├── specs/              # Especificaciones de JFlex y CUP
│   ├── test/               # Programas .ces y salidas .ens
│   └── api/                # Documentación Javadoc
├── lib/                    # JFlex, CUP y compiler-api.jar
└── classes/                # Clases compiladas incluidas en el repositorio
```

## 🛠️ Tecnologías

| Área | Tecnología |
|---|---|
| Implementación | Java |
| Analizador léxico | JFlex |
| Analizador sintáctico | Java CUP |
| Construcción y tareas | Apache Ant (`doc/config/build.xml`) |
| API de apoyo | Biblioteca de compiladores de la UNED (`compiler-api.jar`) |
| Código de destino | Ensamblador ENS2001 |
| Documentación | Javadoc |
| Entorno de desarrollo | Proyecto configurado para Eclipse; incluye configuración de VS Code |

## 🚀 Compilación y uso

**Requisitos:** JDK y Apache Ant instalados. Las bibliotecas `.jar` necesarias están incluidas en `lib/`. El repositorio no fija una versión concreta de JDK, por lo que puede requerir ajustes según el entorno.

Desde la raíz del repositorio:

```bash
# Regenerar el scanner y el parser, y compilar el proyecto
ant -f doc/config/build.xml build

# Ejecutar el análisis léxico sobre uno de los programas incluidos
ant -f doc/config/build.xml -Dtest-file-name=fibonacci.ces flexTest

# Ejecutar el análisis sintáctico
ant -f doc/config/build.xml -Dtest-file-name=fibonacci.ces cupTest

# Ejecutar la prueba final (semántica y generación de código)
ant -f doc/config/build.xml -Dtest-file-name=fibonacci.ces finalTest
```

El objetivo `build` ejecuta las tareas de generación de JFlex y CUP antes de compilar. `finalTest` utiliza los ficheros de `doc/test/` y configura una salida `.ens` con el mismo nombre base.

**Importante:** estos comandos se han extraído de `doc/config/build.xml`, pero no se ha verificado su ejecución en un entorno limpio. El proyecto conserva su configuración académica original y podría requerir adaptaciones de versiones o dependencias.

## 🧪 Ejemplo: Fibonacci

El repositorio incluye el siguiente programa en [`doc/test/fibonacci.ces`](doc/test/fibonacci.ces):

```c
vacio printfibo(entero i, entero endi, entero val1, entero val2)
{
    escribeEnt(val2);
    si (i < endi)
        printfibo(i + 1, endi, val2, val1 + val2);

    devuelve;
}

vacio principal()
{
    printfibo(1, 10, 0, 1);
    devuelve;
}
```

Este caso ilustra el análisis y la traducción de **funciones, parámetros, recursividad, condicionales y expresiones aritméticas**. En [`doc/test/fibonacci.ens`](doc/test/fibonacci.ens) se conserva el código ENS2001 asociado.

También hay un ejemplo de factorial en [`doc/test/factorial.ces`](doc/test/factorial.ces) y otros casos de prueba en `doc/test/`.

## 🎓 Aprendizajes y competencias

Este proyecto documenta el trabajo con conceptos fundamentales de la construcción de compiladores:

- Especificación léxica y diseño de gramáticas formales.
- Integración de herramientas generadoras de analizadores.
- Representación de símbolos, tipos y ámbitos de ejecución.
- Generación y traducción de código intermedio.
- Gestión de registros, memoria y convenciones de llamada en una arquitectura de destino.
- Análisis, depuración y pruebas de componentes de un compilador.

## 📌 Contexto académico y alcance

**Ficha oficial de la asignatura:** [Procesadores de Lenguajes II — UNED](https://www.uned.es/universidad/inicio/estudios/grados/grado-en-ingenieria-informatica/asignaturas.html?codTitulacion=7101&codAsignatura=71013118&idContenido=1).

Este repositorio recoge una **práctica universitaria de Procesadores de Lenguajes II (UNED, 2023–2024)**. Se conserva con su estructura de trabajo original para mostrar el desarrollo académico y técnico realizado durante el grado.

Parte de la infraestructura y las bibliotecas utilizadas corresponden al material de apoyo de la asignatura. Por tanto, este repositorio **no debe interpretarse como una implementación desde cero de todas las capas de un compilador** ni como un producto mantenido para producción.

## 👤 Autoría y aclaración sobre la cuenta `lucidtyper`

Proyecto realizado por **Guillermo Fernández Quesada**.

**Nota de autoría:** la cuenta de GitHub [`lucidtyper`](https://github.com/lucidtyper), que aparece en el historial o como colaboradora del repositorio, es una **cuenta anterior utilizada por el mismo autor** para proyectos académicos y personales. **No representa a un segundo desarrollador ni a un colaborador independiente.** La cuenta actual de referencia para el portfolio es [`guillefdezq`](https://github.com/guillefdezq), que es el perfil profesional.

---

*Proyecto académico realizado en el marco del Grado en Ingeniería Informática de la UNED. Repositorio personal, no oficial de la universidad.*
