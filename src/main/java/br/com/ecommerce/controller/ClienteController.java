package br.com.ecommerce.controller;

import br.com.ecommerce.dto.ClienteFielDTO;
import br.com.ecommerce.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteFielDTO>> getClientesFieis() {
        List<ClienteFielDTO> clientesFieis = clienteService.buscarTopClientesFieis();

        if (clientesFieis.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(clientesFieis);
    }

}
