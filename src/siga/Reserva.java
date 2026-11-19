package siga;

// Agora a Reserva aplica polimorfismo

// Além de ser vinculada à um Equipamento e um usuário. 
public class Reserva {

    private final Usuario solicitante;
    private final Equipamento item;
    private EstadoReserva estado;

    public Reserva(Usuario solicitante, Equipamento item) {
        this.solicitante = solicitante;
        this.item = item;
        this.estado = new EstadoSolicitada();
    }

    public String getSituacao() {
        return estado.getNome();
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void retirar() {
        estado.retirar(this);
    }

    public void devolver() {
        estado.devolver(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Equipamento getEquipamento() {
        return item;
    }
}
