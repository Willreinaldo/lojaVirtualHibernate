package loja.modelo;

import dao.CategoriaDao;
import dao.ProdutoDao;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroProduto {

    public static void main(String[] args) {
      cadastrarProduto();

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());
    }
    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi 5S plus","Muito rapido e duradouro",new BigDecimal("800"), celulares);

        EntityManager em = JPAutil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }


}