package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEMA 1 (parte B) - a MESMA conversao, escrita de novo, em outro lugar.
 *
 * Aqui alguem percebeu que a situacao as vezes vem em minuscula e corrigiu.
 * A correcao nao chegou ao CatalogoEquipamentos: as duas versoes ja divergem, e o
 * mesmo Equipamento aparece indisponivel em um lugar e disponivel no outro.
 */
public class RelatorioAcervo {

    private final InventarioLegadoWS legado;

    public RelatorioAcervo(InventarioLegadoWS legado) {
        this.legado = legado;
    }

    public List<Equipamento> listarTodos() {
        List<Equipamento> todos = new ArrayList<>();
        for (String[] linha : legado.consultarEquipamentos()) {
            Equipamento item = new Equipamento(linha[0], linha[1]);
            // aqui a minuscula foi tratada
            item.setDisponivel("D".equalsIgnoreCase(linha[2]));
            todos.add(item);
        }
        return todos;
    }

    public void imprimir() {
        System.out.println("   Situacao segundo o relatorio:");
        for (Equipamento item : listarTodos()) {
            System.out.println("     " + item + " - "
                    + (item.isDisponivel() ? "disponivel" : "baixado"));
        }
    }
}
