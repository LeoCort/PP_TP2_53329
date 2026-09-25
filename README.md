Alumno: Leonel Cortez (Legajo: 53329) TP2

Sistema de gestión de eventos universitarios desarrollado en Java. Este proyecto permite la administración de actividades académicas, inscripciones de estudiantes, emisión de certificados y cálculo de costos, aplicando conceptos avanzados de Programación Orientada a Objetos.

## Características y Requisitos Cumplidos

Este proyecto evidencia la resolución de los requerimientos establecidos para el TP1 y TP2, abarcando las siguientes funcionalidades:

* **Herencia y Polimorfismo:** Jerarquía de clases con `Actividad` como clase abstracta base y `Taller`, `Charla` y `Curso` como clases derivadas.
* **Manejo de Excepciones:** Implementación de la excepción personalizada `CupoExcedidoException` para gestionar la sobrecapacidad en las inscripciones mediante bloques `try-catch-finally`.
* **Persistencia de Datos:** Serialización de objetos para guardar y recuperar de forma segura el estado de los eventos en el disco local (archivos `.dat`).
* **Interfaces:** Creación e implementación de la interfaz `Certificable` para gestionar la emisión de diplomas exclusivamente en las actividades que lo requieran (Talleres y Cursos).
* **Genéricos (Generics) y Comodines (Wildcards):**
  * Filtrado dinámico y tipado seguro de listas de actividades mediante métodos parametrizados acotados (`<T extends Actividad>`).
  * Procesamiento y cálculo de costos utilizando wildcards (`List<? extends Actividad>`).

## Estructura del Proyecto

El código fuente está organizado en los siguientes paquetes principales:
* `modelo`: Contiene las entidades base del dominio (`EventoUniversitario`, `Inscripcion`, `Sala`, `Estudiante`).
* `modelo.actividades`: Contiene la jerarquía y tipología de las actividades (`Actividad`, `Taller`, `Charla`, `Curso`).
* `excepciones`: Aloja el control de errores y validaciones de negocio del sistema.
* `certificacion`: Define los contratos de comportamiento de las clases.

## Tecnologías Utilizadas
* **Lenguaje:** Java
* **Conceptos clave:** POO, Interfaces, Serialización, Try-Catch, Casteo Seguro.
