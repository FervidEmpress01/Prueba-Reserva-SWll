package edu.epn.iccd632.xp;

/** Demostración sencilla; el comportamiento real se verifica con JUnit. */
public final class Main {
    public static void main(String[] args) {
        Reservations r = new Reservations();

        System.out.println("=== DEMOSTRACIÓN SISTEMA DE RESERVAS (XP) ===");
        System.out.println("--- HU-01: Reserva básica ---");
        System.out.println("Bloque A disponible inicialmente: " + r.isAvailable("A")); // true
        System.out.println("E01 reserva bloque A: " + r.book("E01", "A"));             // true
        System.out.println("Bloque A disponible tras reserva: " + r.isAvailable("A"));// false

        System.out.println("\n--- HU-03 (CR-01): Regla de reserva única ---");
        System.out.println("E01 intenta reservar bloque B (teniendo A activo): " + r.book("E01", "B")); // false
        System.out.println("E02 (distinto estudiante) reserva bloque B: " + r.book("E02", "B"));       // true

        System.out.println("\n--- HU-02 y HU-03: Cancelación y nueva reserva ---");
        System.out.println("E01 cancela su reserva en bloque A: " + r.cancel("E01", "A"));              // true
        System.out.println("Bloque A disponible tras cancelacion: " + r.isAvailable("A"));            // true
        System.out.println("E01 ahora intenta reservar bloque C: " + r.book("E01", "C"));              // true
        System.out.println("Bloque C disponible tras reserva de E01: " + r.isAvailable("C"));         // false
    }
}
