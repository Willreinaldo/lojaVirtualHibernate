package loja.dao;
import loja.modelo.Cliente;
import loja.modelo.Pedido;
import loja.modelo.Produto;

import javax.persistence.EntityManager;

public class ClienteDao {
    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente pedido) {
        this.em.persist(pedido);
    }

    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }

}


