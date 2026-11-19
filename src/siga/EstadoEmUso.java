package siga;

// Classe de estado em uso, pode ser alterada
public class EstadoEmUso implements EstadoReserva {

    @Override
    public String getNome() {
        return "EM_USO";
    }

    @Override
    public void devolver(Reserva reserva) {
        reserva.setEstado(new EstadoEncerrada());
    }
}
