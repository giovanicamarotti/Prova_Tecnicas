package siga;

import java.util.ArrayList;
import java.util.List;


public class InventarioLegadoAdapter implements InventarioEquipamentos {

    private final InventarioLegadoWS legado;

    public InventarioLegadoAdapter(InventarioLegadoWS legado) {
        this.legado = legado;
    }

    @Override
    public List<Equipamento> consultarEquipamentos() {
        List<Equipamento> itens = new ArrayList<>();
        for (String[] linha : legado.consultarEquipamentos()) {
            Equipamento item = new Equipamento(linha[0], linha[1]);
            // Normaliza a situacao considerando maiusculas e minusculas em um unico ponto
            item.setDisponivel("D".equalsIgnoreCase(linha[2]));
            itens.add(item);
        }
        return itens;
    }
}
