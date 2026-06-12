public class Item {

    private String descricao;
    private String unidade;
    private double quantidade;
    private double preco;

    public Item(String descricao, String unidade, double quantidade) {
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.preco = 0;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTotal() {
        return quantidade * preco;
    }
}
