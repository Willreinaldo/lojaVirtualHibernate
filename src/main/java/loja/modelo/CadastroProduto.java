package loja.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroProduto {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi 5S plus");
        celular.setDescricao("Muito rapido e duradouro");
        celular.setPreco(new BigDecimal("800"));

        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("loja");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }
}
