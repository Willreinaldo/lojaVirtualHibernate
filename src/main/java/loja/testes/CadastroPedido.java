package loja.testes;

import loja.dao.*;
import loja.modelo.*;
import loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class CadastroPedido {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAutil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);


        List<Produto> buscaProdutos = produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
        buscaProdutos.forEach(p -> System.out.println(p.getNome()));

        Cliente cliente = clienteDao.buscarPorId(1L);
        Produto produto = produtoDao.buscarPorId(1L);

        em.getTransaction().begin();
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
      Pedido pedidoComCliente =  pedidoDao.buscarPedidoComCliente(1L);

        em.getTransaction().commit();
        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);
        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);

        Pedido pedidofind = em.find(Pedido.class, 1L);
        System.out.println(pedidofind.getItens().size());

        em.close();
        System.out.println(pedidoComCliente.getCliente().getNome());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria eletrodomesticos = new Categoria("ELETRODOMESTICOS");

        Produto celular = new Produto("Xiaomi Redmi 5S plus","Muito rapido e duradouro",new BigDecimal("800"), celulares);
        Produto geladeira = new Produto("Geladeira Consul","Muito eficiente e economica",new BigDecimal("1200"), eletrodomesticos);
        Produto novaGeladeira = new Produto("Geladeira Brastemp Inverse Smart Bar","Top de linha do mercado, melhor modelo da marca",new BigDecimal("3100"), eletrodomesticos);
        Cliente cliente = new Cliente("Rodrigo", "123456");


        EntityManager em = JPAutil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        clienteDao.cadastrar(cliente);
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        categoriaDao.cadastrar(eletrodomesticos);
        produtoDao.cadastrar(geladeira);
        produtoDao.atualizar(novaGeladeira);
        produtoDao.remover(geladeira);

        em.getTransaction().commit();
    }

}
