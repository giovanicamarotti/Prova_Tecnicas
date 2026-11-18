package siga;

import java.util.List;

/**
 * BRINDE - nao vale nota e nao precisa ser alterado.
 *
 * Confere, executando, duas coisas que o enunciado exige:
 *   (A) o PAT-1003 tem a MESMA situacao no catalogo e no relatorio;
 *   (B) o ciclo de vida de Reserva continua exatamente o de hoje.
 *
 * Antes de voce refatorar, (A) ja esta VERMELHO - e a prova de que o
 * Problema 1 existe. Os seis casos de (B) ja estao VERDES e devem
 * CONTINUAR verdes: se um deles virar vermelho, a sua refatoracao mudou o
 * comportamento, e o enunciado pede que ele seja preservado.
 *
 * Nao depende de biblioteca nem de internet. Roda junto com o Main.
 */
public final class VerificacaoRapida {

    private static int verdes = 0;
    private static int vermelhos = 0;

    private VerificacaoRapida() {
    }

    public static void verificar(CatalogoEquipamentos catalogo, RelatorioAcervo relatorio) {
        verdes = 0;
        vermelhos = 0;
        System.out.println();
        System.out.println("=== VERIFICACAO RAPIDA (brinde - nao vale nota) ===");

        System.out.println("  (A) conversao em um lugar so");
        conferirDivergencia(catalogo, relatorio);

        System.out.println("  (B) ciclo de vida preservado");
        conferirCiclo();

        System.out.println();
        System.out.println("  " + verdes + " verde(s), " + vermelhos + " vermelho(s)");
        if (vermelhos == 0) {
            System.out.println("  Tudo verde.");
        } else {
            System.out.println("  Leia os vermelhos acima antes de entregar.");
        }
    }

    // ---------------------------------------------------------- (A)
    private static void conferirDivergencia(CatalogoEquipamentos catalogo,
                                            RelatorioAcervo relatorio) {
        Boolean noCatalogo = null;
        for (Equipamento item : catalogo.listarDisponiveis()) {
            if ("PAT-1003".equals(item.getCodigo())) {
                noCatalogo = Boolean.TRUE;
            }
        }
        if (noCatalogo == null) {
            noCatalogo = Boolean.FALSE;
        }

        Boolean noRelatorio = null;
        List<Equipamento> todos = relatorio.listarTodos();
        for (Equipamento item : todos) {
            if ("PAT-1003".equals(item.getCodigo())) {
                noRelatorio = Boolean.valueOf(item.isDisponivel());
            }
        }
        if (noRelatorio == null) {
            registrar(false, "PAT-1003 nem aparece no relatorio");
            return;
        }
        registrar(noCatalogo.equals(noRelatorio),
                "PAT-1003 disponivel no catalogo=" + noCatalogo
                        + ", no relatorio=" + noRelatorio);
    }

    // ---------------------------------------------------------- (B)
    private static Reserva novo() {
        return new Reserva(new Usuario("Ana Souza", "ALUNO"),
                new Equipamento("PAT-1001", "Notebook Dell i5"));
    }

    private static void conferirCiclo() {
        registrar("SOLICITADA".equals(novo().getSituacao()),
                "um Reserva novo nasce em SOLICITADA");

        Reserva a = novo();
        a.retirar();
        registrar("EM_USO".equals(a.getSituacao()),
                "retirar leva de SOLICITADA para EM_USO");

        Reserva b = novo();
        b.retirar();
        b.devolver();
        registrar("ENCERRADA".equals(b.getSituacao()),
                "devolver leva de EM_USO para ENCERRADA");

        Reserva c = novo();
        c.cancelar();
        registrar("ENCERRADA".equals(c.getSituacao()),
                "cancelar leva de SOLICITADA para ENCERRADA");

        registrar(recusa(novo(), "devolver"),
                "devolver em SOLICITADA e recusado");

        Reserva d = novo();
        d.retirar();
        registrar(recusa(d, "retirar"),
                "retirar em EM_USO e recusado");

        Reserva e = novo();
        e.cancelar();
        registrar(recusa(e, "retirar") && recusa(e, "devolver")
                        && recusa(e, "cancelar"),
                "ENCERRADA e final: recusa as tres operacoes");
    }

    private static boolean recusa(Reserva alvo, String operacao) {
        try {
            if ("retirar".equals(operacao)) {
                alvo.retirar();
            } else if ("devolver".equals(operacao)) {
                alvo.devolver();
            } else {
                alvo.cancelar();
            }
            return false;
        } catch (IllegalStateException esperado) {
            return true;
        }
    }

    private static void registrar(boolean passou, String descricao) {
        if (passou) {
            verdes++;
            System.out.println("      [verde]    " + descricao);
        } else {
            vermelhos++;
            System.out.println("      [VERMELHO] " + descricao);
        }
    }
}
