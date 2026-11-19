package siga;


public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Reserva de Laboratorio - Prova Pratica ===");
        System.out.println();

        InventarioLegadoWS legado = new InventarioLegadoWS();
        InventarioEquipamentos inventario = new InventarioLegadoAdapter(legado);
        CatalogoEquipamentos catalogo = new CatalogoEquipamentos(inventario);
        RelatorioAcervo relatorio = new RelatorioAcervo(inventario);

        System.out.println("1) O mesmo dado, convertido em dois lugares");
        System.out.println("   Disponiveis segundo o catalogo:");
        for (Equipamento item : catalogo.listarDisponiveis()) {
            System.out.println("     " + item);
        }
        relatorio.imprimir();

        System.out.println();
        System.out.println("2) O ciclo de vida em condicionais");
        Reserva registro = new Reserva(
                new Usuario("Ana Souza", "ALUNO"),
                new Equipamento("PAT-1001", "Notebook Dell i5"));
        System.out.println("   situacao inicial: " + registro.getSituacao());
        registro.retirar();
        System.out.println("   apos retirar: " + registro.getSituacao());
        registro.devolver();
        System.out.println("   apos devolver: " + registro.getSituacao());
        try {
            registro.cancelar();
        } catch (IllegalStateException erro) {
            System.out.println("   [erro] " + erro.getMessage());
        }

        VerificacaoRapida.verificar(catalogo, relatorio);
    }
}
