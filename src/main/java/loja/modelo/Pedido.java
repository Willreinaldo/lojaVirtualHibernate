package loja.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {
    private Long id;
    private BigDecimal valorTotal;
    private LocalDate data = LocalDate.now();

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    private List<ItemPedido> itens;

    public Pedido( Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
