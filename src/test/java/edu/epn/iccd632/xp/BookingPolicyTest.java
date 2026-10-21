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
}

