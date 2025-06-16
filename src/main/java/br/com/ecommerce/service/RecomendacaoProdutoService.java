package br.com.ecommerce.service;

import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.model.Compra;
import br.com.ecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecomendacaoProdutoService {

    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    @Autowired
    public RecomendacaoProdutoService(ProdutoService produtoService, ClienteService clienteService) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    public Optional<String> recomendarTipoVinhoPorCliente(String cpf) {
        // Busca cliente por cpf
        Optional<Cliente> clienteOpt = clienteService.buscarClientePorCpf(cpf);
        if (clienteOpt.isEmpty()) {
            return Optional.empty();
        }
        Cliente cliente = clienteOpt.get();

        // Busca lista de Produtos
        List<Produto> produtos = produtoService.getProdutos();

        // Tipo de vinho por codigo
        Map<Integer, String> codigoParaTipo = produtos.stream()
                .collect(Collectors.toMap(Produto::getCodigo, Produto::getTipoVinho));

        // Soma quantidades por tipo de vinho
        Map<String, Integer> quantidadePorTipo = new HashMap<>();
        for (Compra compra : cliente.getCompras()) {
            String tipo = codigoParaTipo.get(compra.getCodigo());
            if (tipo != null) {
                quantidadePorTipo.put(tipo,
                        quantidadePorTipo.getOrDefault(tipo, 0) + compra.getQuantidade());
            }
        }

        // Encontra tipo com maior quantidade
        return quantidadePorTipo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

}
