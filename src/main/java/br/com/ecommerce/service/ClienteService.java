package br.com.ecommerce.service;

import br.com.ecommerce.dto.ClienteFielDTO;
import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final RestTemplate restTemplate;
    private final ProdutoService produtoService;
    private final String CLIENTES_URL = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";

    @Autowired
    public ClienteService(RestTemplate restTemplate, ProdutoService produtoService) {
        this.restTemplate = restTemplate;
        this.produtoService = produtoService;
    }

    public List<Cliente> getClientes() {
        ResponseEntity<Cliente[]> response = restTemplate.getForEntity(CLIENTES_URL, Cliente[].class);
        Cliente[] clientesArray = response.getBody();
        if (clientesArray == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(clientesArray);
    }

    public List<ClienteFielDTO> buscarTopClientesFieis() {
        List<Cliente> clientes = getClientes();

        return clientes.stream()
                .map(cliente -> {
                    int totalCompras = cliente.getCompras().size();

                    double valorTotal = cliente.getCompras().stream()
                            .mapToDouble(compra -> {
                                // Aqui chama o método que só recebe o código
                                Produto produto = produtoService.buscarProdutoPorCodigo(compra.getCodigo()).orElse(null);
                                return (produto != null) ? produto.getPreco() * compra.getQuantidade() : 0.0;
                            })
                            .sum();

                    return new ClienteFielDTO(
                            cliente.getNome(),
                            cliente.getCpf(),
                            totalCompras,
                            valorTotal
                    );
                })
                .sorted(Comparator
                        .comparing(ClienteFielDTO::getTotalCompras).reversed()
                        .thenComparing(ClienteFielDTO::getValorTotalGasto).reversed()
                )
                .limit(3)
                .collect(Collectors.toList());
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return getClientes().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst();
    }

}
