package siga;

//Estado inicial da reserva
public class EstadoSolicitada implements EstadoReserva {

    @Override
    public String getNome() {
        return "SOLICITADA";
    }

    @Override
    public void retirar(Reserva reserva) {
        reserva.setEstado(new EstadoEmUso());
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new EstadoEncerrada());
    }
}
