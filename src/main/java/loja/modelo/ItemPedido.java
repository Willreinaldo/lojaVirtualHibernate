package loja.modelo;


import javax.persistence.*;


@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String precoUnitario;
    private int quantidade;

    public ItemPedido(){
    }
    public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
