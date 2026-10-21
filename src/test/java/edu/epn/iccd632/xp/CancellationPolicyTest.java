package edu.epn.iccd632.xp;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * HU-02: prueben primero CancellationPolicy directamente con un Map
 * precargado; así no dependen de que HU-01 ya esté terminada.
 */
class CancellationPolicyTest {

    private final CancellationPolicy policy = new CancellationPolicy();

    @Test
    void cancelarConTitularYBloqueCorrectosLiberaElBloque() {
        Map<String, String> ownerBySlot = new HashMap<>();
        ownerBySlot.put("A", "E01");

        boolean result = policy.cancel(ownerBySlot, "E01", "A");

        assertTrue(result, "cancelar con el titular correcto debe devolver true");
        assertFalse(ownerBySlot.containsKey("A"), "el bloque debe quedar libre tras cancelar");
    }

    @Test
    void cancelarUnBloqueLibreDevuelveFalseYNoModificaElEstado() {
        Map<String, String> ownerBySlot = new HashMap<>();

        boolean result = policy.cancel(ownerBySlot, "E01", "A");

        assertFalse(result, "cancelar un bloque libre debe devolver false");
        assertTrue(ownerBySlot.isEmpty(), "el estado no debe modificarse");
    }

    @Test
    void cancelarPorUnEstudianteDistintoDevuelveFalseYConservaAlTitular() {
        Map<String, String> ownerBySlot = new HashMap<>();
        ownerBySlot.put("A", "E01");

        boolean result = policy.cancel(ownerBySlot, "E02", "A");

        assertFalse(result, "cancelar con un estudiante distinto al titular debe devolver false");
        assertEquals("E01", ownerBySlot.get("A"), "el titular original debe conservarse");
    }
}
