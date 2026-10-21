# Registro XP - Equipo Alpha
Repositorio: https://github.com/FervidEmpress01/Prueba-Reserva-SWll
Integrantes y usuarios GitHub: 
- Mauricio Carrion (@FervidEmpress01) 
- Anthony Contreras (@7heAnsw3r) 
- Kevin García (@NattyrdGT) 
- David Guachamín (@Davandres)

## Cliente y objetivo de valor
¿Qué necesidad de negocio resuelve este incremento? (máx. 2 líneas)
Permitir a los usuarios gestionar sus reservas de mesa de manera eficiente y automatizada.

## Valores XP observables
1. Simplicidad: mantenemos el estado en memoria usando el `Map<String,String>` ya provisto en `Reservations`; no agregamos clases, frameworks ni persistencia adicional para HU-02.
2. Comunicación: la pareja HU-02 revisa con la pareja HU-01 el contrato de `Reservations` antes de tocar `CancellationPolicy`, para no romper las firmas compartidas.

## Responsabilidades flexibles
Pareja 1 (HU-01): Kevin/Mauricio | Pareja 2 (HU-02): David / Anthony
Facilitación: Mauricio | Seguimiento: Anthony | Consulta al cliente: Kevin
Las responsabilidades pueden cambiar durante el examen.

## Historias, aceptación y estimaciones
| Historia | Prioridad | Estimación relativa | Criterios de aceptación | Tareas |
|---|---|---|---|---|
| HU-01 | Alta | 12 | Una reserva válida devuelve true; el bloque deja de estar libre. Un segundo intento sobre el mismo bloque devuelve false y conserva al primer titular. Bloque inválido, estudiante nulo o blanco: false. | |
| HU-02 | Alta | 11 | Cancelar con titular y bloque correctos devuelve true y lo deja disponible; cancelar un bloque libre o por otro estudiante devuelve false y no modifica el estado.  | Escribir prueba de cancelación autorizada (RED); implementar `CancellationPolicy.cancel`; prueba de cancelación por estudiante distinto; prueba de bloque libre; ejecutar `mvn test` (GREEN) |
| HU-03 | Muy Alta | 4 | Un estudiante solo puede reservar un unico salon, mientras que otro diferente que no tenga reserva si la puede realizar.| |

## Tablero mínimo
| Por hacer | En curso | Terminado |
|---|---|---|
|  | | HU-01 |
|  | |  HU-02 |
|  | HU-03 |  |

## Prácticas técnicas
Evidencia RED (prueba inicial que falla, momento o commit):
"Prueba unitaria de UH-01, estado actual: RED"
Evidencia RED HU-01 (commit `32bedd976cb9454cb2206a7a6cf7719327d8b0ab`)
Evidencia GREEN (prueba que pasa después, momento o commit):


Evidencia RED HU-02 (commit `702323d`): `CancellationPolicyTest.cancelarConTitularYBloqueCorrectosLiberaElBloque` falló con `expected: <true> but was: <false>` (mvn test, `CancellationPolicy.cancel` aún sin implementar).
Evidencia GREEN HU-02 (commit `b1d2cd6`): tras implementar `CancellationPolicy.cancel`, `mvn test` reportó `Tests run: 3, Failures: 0, Errors: 0, BUILD SUCCESS`.
Refactorización o decisión de simplicidad: HU-02 reutiliza `Reservations.validSlot` y `Reservations.validStudent` ya existentes en el contrato, en vez de duplicar validaciones en `CancellationPolicy`.

## Git / revisión / CI
PR HU-01: Anthony Contreras | Revisor distinto del autor: Mauricio Carrion 
PR HU-02: Kevin García | Revisor distinto del autor: David Guachamin
Resultado Actions (éxito/fallo/no disponible): ... | URL de ejecución: ...

## Cambio del cliente (completar SOLO cuando se anuncie)
ID: ... | Nueva regla: ...
Impacto en pruebas/código: ... | Prioridad revisada: ...
Decisión del equipo y estado final: ...

## Cierre
Historias aceptadas, pendientes conocidos y siguiente mejora concreta (máx. 3 líneas):
