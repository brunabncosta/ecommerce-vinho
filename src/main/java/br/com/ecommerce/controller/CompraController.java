package br.com.ecommerce.controller;

import br.com.ecommerce.dto.CompraDTO;
import br.com.ecommerce.dto.CompraResponseDTO;
import br.com.ecommerce.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/compras")
    public List<CompraResponseDTO> listarComprasPorValor() {
        return compraService.listarComprasOrdenadasPorValor();
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<?> getMaiorCompra(@PathVariable Integer ano) {
        Optional<CompraDTO> maiorCompra = compraService.buscarMaiorCompraPorAno(ano);

        if (maiorCompra.isPresent()) {
            return ResponseEntity.ok(maiorCompra.get());
        } else {
            return ResponseEntity.ok("Nenhuma compra encontrada para o ano " + ano);
        }
    }

}
