import java.util.ArrayList;

public class ListaCompras {

    private String nome;
    private ArrayList<Item> itens;

    public ListaCompras(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }
}