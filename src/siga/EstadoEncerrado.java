package siga;

// Estado de reserva encerrado
public class EstadoEncerrada implements EstadoReserva {

    @Override
    public String getNome() {
        return "ENCERRADA";
    }
}
