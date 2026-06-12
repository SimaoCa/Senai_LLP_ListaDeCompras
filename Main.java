import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<ListaCompras> listas = new ArrayList<>();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println(".-------------------.");
            System.out.println("| Gestão de compras |");
            System.out.println("'-------------------'");
            System.out.println("1. Nova lista");
            System.out.println("2. Fazer compras");
            System.out.println("3. Relatório");
            System.out.println("0. Sair");

            System.out.print("\n>> Opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    novaLista();
                    break;
                case 2:
                    fazerCompras();
                    break;
                case 3:
                    relatorio();
                    break;
            }

        } while (opcao != 0);
    }

    public static void novaLista() {

        String nomePadrao =
                "lista_" +
                        LocalDate.now()
                                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        System.out.print(
                "\n>> Nova lista, informe o nome [" +
                        nomePadrao + "]: "
        );

        String nome = sc.nextLine();

        if (nome.trim().isEmpty()) {
            nome = nomePadrao;
        }

        ListaCompras lista = new ListaCompras(nome);

        while (true) {

            System.out.println("\n>> ---Informe o item---------");

            System.out.print("Descrição: ");
            String descricao = sc.nextLine();

            if (descricao.trim().isEmpty()) {
                break;
            }

            System.out.print("Unidade (UN, KG, LT): ");
            String unidade = sc.nextLine();

            System.out.print("Quantidade: ");
            double quantidade =
                    Double.parseDouble(sc.nextLine());

            lista.adicionarItem(
                    new Item(
                            descricao,
                            unidade,
                            quantidade
                    )
            );
        }

        listas.add(lista);

        System.out.println(">> ---Lista salva!---------");
    }

    public static void fazerCompras() {

        if (listas.isEmpty()) {
            System.out.println("Nenhuma lista cadastrada.");
            return;
        }

        ListaCompras lista = selecionarLista();

        double total = 0;

        System.out.println(
                "\n>> ---Fazer compras [" +
                        lista.getNome() +
                        "]---"
        );

        int totalItens = lista.getItens().size();

        for (int i = 0; i < totalItens; i++) {

            Item item = lista.getItens().get(i);

            System.out.println(
                    "(" + (i + 1) + "/" + totalItens + ") " +
                            item.getDescricao() +
                            " " +
                            item.getQuantidade() +
                            " " +
                            item.getUnidade()
            );

            System.out.print("Preço: ");
            double preco =
                    Double.parseDouble(sc.nextLine());

            if (preco == 0) {
                System.out.println("Item em falta, indo para o próximo...");
                continue;
            }

            System.out.print(
                    "Quantidade [" +
                            item.getQuantidade() +
                            "]: "
            );

            String qtd = sc.nextLine();

            if (!qtd.trim().isEmpty()) {
                item.setQuantidade(
                        Double.parseDouble(qtd)
                );
            }

            item.setPreco(preco);

            total += item.getTotal();
        }

        System.out.println("\n>> ---Total------------------");
        System.out.printf("R$: %.2f%n", total);
    }

    public static void relatorio() {

        if (listas.isEmpty()) {
            System.out.println("Nenhuma lista cadastrada.");
            return;
        }

        ListaCompras lista = selecionarLista();

        double total = 0;

        System.out.println(
                "\n>> ---Relatório [" +
                        lista.getNome() +
                        "]---"
        );

        System.out.println(
                "Item, Descrição, Qtd, UN, Preço, Total"
        );

        int contador = 1;

        for (Item item : lista.getItens()) {

            System.out.printf(
                    "%d, %s, %.2f, %s, %.2f, %.2f%n",
                    contador++,
                    item.getDescricao(),
                    item.getQuantidade(),
                    item.getUnidade(),
                    item.getPreco(),
                    item.getTotal()
            );

            total += item.getTotal();
        }

        System.out.printf(
                "0, TOTAL, -, -, -, %.2f%n",
                total
        );
    }

    public static ListaCompras selecionarLista() {

        System.out.println("\nListas:");

        for (int i = 0; i < listas.size(); i++) {
            System.out.println(
                    (i + 1) +
                            " - " +
                            listas.get(i).getNome()
            );
        }

        int opcao;

        while (true) {
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                if (opcao >= 1 && opcao <= listas.size()) {
                    break;
                }
            } catch (NumberFormatException e) {
                // entrada não numérica, solicita novamente
            }

            System.out.println("Opção inválida, tente novamente.");
        }

        return listas.get(opcao - 1);
    }
}
