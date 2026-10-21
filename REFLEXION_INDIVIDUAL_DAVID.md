
1) Escriba una contribución técnica propia y la evidencia que permite verificarla (archivo, prueba, PR o revisión).
Trabaje junto a Anthony en HU-02 (cancelacion de reserva), revisando los casos de aceptacion y validando la logica de CancellationPolicy.cancel antes de integrarla. Evidencia verificable en los commits 702323d (RED) y b1d2cd6 (GREEN) sobre CancellationPolicy.java y CancellationPolicyTest.java.


2) Indique una decisión del equipo que refleje un valor de XP (comunicación, simplicidad, retroalimentación, coraje o respeto). Justifique con un hecho observado.
Respeto y comunicacion: antes de tocar CancellationPolicy revisamos con la pareja de HU-01 que no se rompiera el contrato publico de Reservations, ya que ambas historias comparten esa misma clase.


3) Explique en dos líneas qué hace GitHub Actions en este proyecto y qué significa que un pipeline falle.
El workflow configurado en .github/workflows/ci.yml compila el proyecto con Java 17 y ejecuta las pruebas con Maven en cada push. Un pipeline en rojo indica que algo quedo roto (compilacion o prueba fallida) y bloquea la confianza para integrar ese cambio a main.


4) ¿Qué mejorarían del proceso XP en una siguiente iteración?
Definir desde el inicio quien hace push primero en la misma rama, para evitar los rechazos por fetch first que tuvimos varias veces durante el examen.

