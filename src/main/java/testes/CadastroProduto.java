package testes;

import dao.CategoriaDao;
import dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {

    public static void main(String[] args) {
      cadastrarProduto();

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());
        List<Produto> todos = produtoDao.buscarTodos();
        List<Produto> produtoPorCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");

        todos.forEach(prod -> System.out.println("PRODUTO: "+prod.getNome()));

        produtoPorCategoria.forEach(prod2 -> System.out.println("PRODUTO POR CATEGORIA: "+prod2.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi 5S plus");
        System.out.println("PRECO DO PRODUTO"+ precoDoProduto);


    }
    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria eletrodomesticos = new Categoria("ELETRODOMESTICOS");

        Produto celular = new Produto("Xiaomi Redmi 5S plus","Muito rapido e duradouro",new BigDecimal("800"), celulares);
        Produto geladeira = new Produto("Geladeira Consul","Muito eficiente e economica",new BigDecimal("1200"), eletrodomesticos);
        Produto novaGeladeira = new Produto("Geladeira Brastemp Inverse Smart Bar","Top de linha do mercado, melhor modelo da marca",new BigDecimal("3100"), eletrodomesticos);


        EntityManager em = JPAutil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        categoriaDao.cadastrar(eletrodomesticos);
        produtoDao.cadastrar(geladeira);
        produtoDao.atualizar(novaGeladeira);
        produtoDao.remover(geladeira);

        em.getTransaction().commit();
        em.close();
    }


}