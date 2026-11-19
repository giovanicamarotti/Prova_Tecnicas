package siga;

import java.util.List;

// Define o dominio de consultas do inventário patrimonial
public interface InventarioEquipamentos {

    List<Equipamento> consultarEquipamentos();
}
