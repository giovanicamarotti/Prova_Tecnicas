package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalogo de equipamentos disponiveis para reserva.
 *
 * Refeito para depender da abstracao InventarioEquipamentos ao invés
 * conhecer a implementacao concreta.
 */
public class CatalogoEquipamentos {

    private final InventarioEquipamentos inventario;

    public CatalogoEquipamentos(InventarioEquipamentos inventario) {
        this.inventario = inventario;
    }

    public List<Equipamento> listarDisponiveis() {
        List<Equipamento> disponiveis = new ArrayList<>();
        for (Equipamento item : inventario.consultarEquipamentos()) {
            if (item.isDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }
}
