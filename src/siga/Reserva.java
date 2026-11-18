package siga;

/**
 * PROBLEMA 2 - o ciclo de vida resolvido com condicionais sobre um campo
 * de texto.
 *
 * Tres sintomas: (a) as regras de transicao estao espalhadas pelos metodos
 * publicos, e ninguem le o ciclo de vida inteiro num lugar so; (b) um
 * estado novo exige alterar todos eles; e (c) a situacao e texto, entao um
 * erro de digitacao so aparece em execucao.
 *
 * Ciclo de vida vigente - E ELE QUE DEVE SER PRESERVADO:
 *   SOLICITADA  --retirar-->  EM_USO
 *   SOLICITADA  --cancelar-->  ENCERRADA
 *   EM_USO  --devolver-->  ENCERRADA
 *   ENCERRADA e final: recusa as tres operacoes.
 */
public class Reserva {

    private final Usuario solicitante;
    private final Equipamento item;

    public Reserva(Usuario solicitante, Equipamento item) {
        this.solicitante = solicitante;
        this.item = item;
        this.situacao = "SOLICITADA";
    }

    private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void retirar() {
        if ("SOLICITADA".equals(situacao)) {
            situacao = "EM_USO";
        } else {
            throw new IllegalStateException(
                    "Nao e possivel retirar em " + situacao + ".");
        }
    }

    public void devolver() {
        if ("EM_USO".equals(situacao)) {
            situacao = "ENCERRADA";
        } else {
            throw new IllegalStateException(
                    "Nao e possivel devolver em " + situacao + ".");
        }
    }

    public void cancelar() {
        if ("SOLICITADA".equals(situacao)) {
            situacao = "ENCERRADA";
        } else {
            throw new IllegalStateException(
                    "Nao e possivel cancelar em " + situacao + ".");
        }
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Equipamento getEquipamento() {
        return item;
    }
}
