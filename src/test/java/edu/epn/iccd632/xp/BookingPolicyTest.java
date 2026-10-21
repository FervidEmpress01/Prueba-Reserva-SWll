package edu.epn.iccd632.xp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para HU-01 (Reservar un bloque libre de sala).
 * Criterios de aceptación:
 * - Una reserva válida devuelve true; el bloque deja de estar libre.
 * - Un segundo intento sobre el mismo bloque devuelve false y conserva al primer titular.
 * - Bloque inválido, estudiante nulo o blanco: false.
 */
class BookingPolicyTest {

    private Reservations reservations;
    private BookingPolicy bookingPolicy;
    private Map<String, String> ownerBySlot;

    @BeforeEach
    void setUp() {
        reservations = new Reservations();
        bookingPolicy = new BookingPolicy();
        ownerBySlot = new HashMap<>();
    }

    @Test
    void reservaValidaDevuelveTrueYBloqueDejaDeEstarLibre() {
        assertTrue(reservations.isAvailable("A"), "El bloque A debería estar disponible inicialmente");
        boolean exito = reservations.book("EST-01", "A");
        assertTrue(exito, "Una reserva válida de un bloque libre debe devolver true");
        assertFalse(reservations.isAvailable("A"), "Tras reservar, el bloque A ya no debe estar disponible");
    }

    @Test
    void segundoIntentoMismoBloqueDevuelveFalseYConservaPrimerTitular() {
        // Probamos con BookingPolicy directamente para verificar que se conserva el primer titular
        boolean primeraReserva = bookingPolicy.book(ownerBySlot, "EST-01", "B");
        assertTrue(primeraReserva, "La primera reserva debe ser exitosa");
        assertEquals("EST-01", ownerBySlot.get("B"), "El titular del bloque B debe ser EST-01");

        boolean segundaReserva = bookingPolicy.book(ownerBySlot, "EST-02", "B");
        assertFalse(segundaReserva, "Un segundo intento sobre el mismo bloque debe devolver false");
        assertEquals("EST-01", ownerBySlot.get("B"), "Se debe conservar al primer titular tras un intento duplicado");
    }

    @Test
    void reservaRechazaEstudianteNuloOBlanco() {
        assertFalse(reservations.book(null, "C"), "Estudiante null debe ser rechazado");
        assertFalse(reservations.book("", "C"), "Estudiante vacío debe ser rechazado");
        assertFalse(reservations.book("   ", "C"), "Estudiante en blanco debe ser rechazado");
        assertTrue(reservations.isAvailable("C"), "El bloque C debe permanecer libre si los datos son inválidos");
    }

    @Test
    void reservaRechazaBloqueInvalido() {
        assertFalse(reservations.book("EST-01", "X"), "Bloque 'X' no es válido");
        assertFalse(reservations.book("EST-01", "a"), "Bloque en minúscula 'a' no es válido");
        assertFalse(reservations.book("EST-01", null), "Bloque null no es válido");
        assertFalse(reservations.book("EST-01", ""), "Bloque vacío no es válido");
    }

    // ==========================================
    // Pruebas para HU-03 / Solicitud de Cambio CR-01:
    // Criterio: Un estudiante no puede mantener más de UNA reserva activa
    // ==========================================

    @Test
    void estudianteConReservaActivaNoPuedeReservarOtroBloque() {
        assertTrue(reservations.book("E01", "A"), "E01 debe poder reservar el bloque A");
        assertFalse(reservations.book("E01", "B"), "E01 no debe poder reservar un segundo bloque B si ya tiene A");
    }

    @Test
    void estudianteDistintoPuedeReservarBloqueLibre() {
        assertTrue(reservations.book("E01", "A"), "E01 debe poder reservar el bloque A");
        assertTrue(reservations.book("E02", "B"), "E02, al no tener reservas previas, sí puede reservar el bloque B");
    }

    @Test
    void estudiantePuedeReservarNuevoBloqueTrasCancelarElPrevio() {
        assertTrue(reservations.book("E01", "A"), "E01 reserva inicialmente el bloque A");
        assertTrue(reservations.cancel("E01", "A"), "E01 cancela correctamente su reserva del bloque A");
        assertTrue(reservations.book("E01", "B"), "Tras cancelar su reserva previa, E01 debe poder reservar el bloque B");
    }
}

