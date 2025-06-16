package br.com.ecommerce.service;

import br.com.ecommerce.dto.CompraDTO;
import br.com.ecommerce.dto.CompraProdutoDTO;
import br.com.ecommerce.dto.CompraResponseDTO;
import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.model.Compra;
import br.com.ecommerce.model.Produto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public CompraService(ClienteService clienteService, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public List<CompraResponseDTO> listarComprasOrdenadasPorValor() {
        List<Cliente> clientes = clienteService.getClientes();
        List<Produto> produtos = produtoService.getProdutos();

        List<CompraResponseDTO> resultado = new ArrayList<>();

        for (Cliente cliente : clientes) {
            double valorTotal = 0;
            List<CompraProdutoDTO> produtosComprados = new ArrayList<>();

            for (Compra compra : cliente.getCompras()) {
                Produto produto = produtos.stream()
                        .filter(p -> p.getCodigo() == compra.getCodigo())
                        .findFirst()
                        .orElse(null);

                if (produto != null) {
                    CompraProdutoDTO dto = new CompraProdutoDTO();
                    dto.setCodigo(produto.getCodigo());
                    dto.setTipoProduto(produto.getTipoVinho());
                    dto.setSafra(produto.getSafra());
                    dto.setAnoCompra(produto.getAnoCompra());
                    dto.setPrecoUnitario(produto.getPreco());
                    dto.setQuantidade(compra.getQuantidade());
                    dto.setSubtotal(produto.getPreco() * compra.getQuantidade());

                    valorTotal += dto.getSubtotal();
                    produtosComprados.add(dto);
                }
            }

            CompraResponseDTO response = new CompraResponseDTO();
            response.setNomeCliente(cliente.getNome());
            response.setCpfCliente(cliente.getCpf());
            response.setProdutos(produtosComprados);
            response.setValorTotal(valorTotal);

            resultado.add(response);
        }

        return resultado.stream()
                .sorted(Comparator.comparingDouble(CompraResponseDTO::getValorTotal))
                .collect(Collectors.toList());
    }

    public Optional<CompraDTO> buscarMaiorCompraPorAno(Integer ano) {
        List<Cliente> clientes = clienteService.getClientes();
        List<Produto> produtos = produtoService.getProdutos();

        return clientes.stream()
                .flatMap(cliente -> cliente.getCompras().stream()
                        .map(compra -> {
                            Optional<Produto> produtoOpt = produtoService.buscarProdutoPorCodigoEAno(produtos, compra.getCodigo(), ano);
                            return produtoOpt.map(produto -> new CompraDTO(
                                    cliente.getNome(),
                                    cliente.getCpf(),
                                    produto.getCodigo(),
                                    produto.getTipoVinho(),
                                    produto.getPreco(),
                                    produto.getSafra(),
                                    produto.getAnoCompra(),
                                    compra.getQuantidade(),
                                    produto.getPreco() * compra.getQuantidade()
                            ));
                        })
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                )
                .max(Comparator.comparing(CompraDTO::getValorTotal));
    }


}
