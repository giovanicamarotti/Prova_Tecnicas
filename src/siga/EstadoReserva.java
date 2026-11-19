package siga;

// Regras de transição de uma reserva.
public interface EstadoReserva {

    String getNome();

    default void retirar(Reserva reserva) {
        throw new IllegalStateException("Nao e possivel retirar em " + getNome() + ".");
    }

    default void devolver(Reserva reserva) {
        throw new IllegalStateException("Nao e possivel devolver em " + getNome() + ".");
    }

    default void cancelar(Reserva reserva) {
        throw new IllegalStateException("Nao e possivel cancelar em " + getNome() + ".");
    }
}
