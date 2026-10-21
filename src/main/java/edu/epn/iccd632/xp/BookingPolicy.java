package edu.epn.iccd632.xp;

import java.util.Map;

/** HU-01. PUNTO DE TRABAJO PARA LA PAREJA 1. */
final class BookingPolicy {
    boolean book(Map<String, String> ownerBySlot, String studentId, String slot) {
        if (!Reservations.validStudent(studentId) || !Reservations.validSlot(slot)) {
            return false;
        }
        if (ownerBySlot.containsKey(slot)) {
            return false;
        }
        // Regla CR-01 / HU-03: un mismo estudiante no puede mantener más de una reserva activa
        if (ownerBySlot.containsValue(studentId)) {
            return false;
        }
        ownerBySlot.put(slot, studentId);
        return true;
    }
}
