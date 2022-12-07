package loja.dao;

import loja.modelo.Pedido;
import loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDao {
    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    public BigDecimal valorTotalVendido(){
         String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
         return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas(){

        String jpql = "SELECT new loja.dao.RelatorioDeVendasVo("
                + "produto.nome, "
                + "SUM(item.quantidade), "
                + "MAX(pedido.data))"
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }
    public Pedido buscarPedidoComCliente(Long id){
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id",Pedido.class)
                .setParameter("id",id)
                .getSingleResult();
    }
}


