package edu.epn.iccd632.xp;

import java.util.Map;

/** HU-02. PUNTO DE TRABAJO PARA LA PAREJA 2. */
final class CancellationPolicy {
    boolean cancel(Map<String, String> ownerBySlot, String studentId, String slot) {
        if (!Reservations.validSlot(slot) || !Reservations.validStudent(studentId)) {
            return false;
        }
        String owner = ownerBySlot.get(slot);
        if (owner == null || !owner.equals(studentId)) {
            return false;
        }
        ownerBySlot.remove(slot);
        return true;
    }
}
