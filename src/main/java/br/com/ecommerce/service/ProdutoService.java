package br.com.ecommerce.service;

import br.com.ecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final RestTemplate restTemplate;
    private List<Produto> produtosCache;
    private final String PRODUTOS_URL = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";

    @Autowired
    public ProdutoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        carregarProdutos();
    }


    public List<Produto> getProdutos() {
        ResponseEntity<Produto[]> response = restTemplate.getForEntity(PRODUTOS_URL, Produto[].class);
        return Arrays.asList(response.getBody());
    }

    public Optional<Produto> buscarProdutoPorCodigoEAno(List<Produto> produtos, Integer codigo, Integer ano) {
        if (codigo == null || ano == null) {
            return Optional.empty();
        }

        return produtos.stream()
                .filter(p -> p.getCodigo() != null && p.getCodigo().equals(codigo))
                .filter(p -> p.getAnoCompra() != null && p.getAnoCompra().equals(ano))
                .findFirst();
    }

    public Optional<Produto> buscarProdutoPorCodigo(Integer codigo) {
        if (produtosCache == null) {
            carregarProdutos();  // garante que a lista esteja carregada
        }
        return produtosCache.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();
    }

    private void carregarProdutos() {
        ResponseEntity<Produto[]> response = restTemplate.getForEntity(PRODUTOS_URL, Produto[].class);
        produtosCache = Arrays.asList(response.getBody());
    }

}
