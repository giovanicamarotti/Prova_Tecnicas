package siga;

/**
 * COMPONENTE EXTERNO - simula o inventario patrimonial da instituicao, mantido por
 * outra equipe.
 *
 * NAO ALTERE ESTA CLASSE. Ela representa uma dependencia de terceiros cujo
 * codigo-fonte voce nao controla.
 *
 * Formato devolvido: cada linha e {codigo, descricao, situacao}, em que a
 * situacao vem como "D" (disponivel) ou "B" (baixado) - e, por descuido do
 * sistema de origem, as vezes em minuscula.
 */
public class InventarioLegadoWS {

    public String[][] consultarEquipamentos() {
        return new String[][] {
            {"PAT-1001", "Notebook Dell i5", "D"},
            {"PAT-1002", "Projetor Epson", "B"},
            {"PAT-1003", "Osciloscopio Tektronix", "d"},
            {"PAT-1004", "Impressora 3D", "D"}
        };
    }
}
