package br.com.banco.controllers;

import br.com.banco.dto.FiltrosDTO;
import br.com.banco.dto.ResponseDTO;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/findAll")
    public ResponseEntity<ResponseDTO> buscarTransferencias(
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim,
            @RequestParam(required = false) String nomeOperadorTransacao,
            @RequestParam(required = false) Long contaId
    ) {
        ResponseDTO transferencias = transferenciaService.findTransferencias(dataInicio, dataFim, nomeOperadorTransacao, contaId);
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transferencia> criarTransferencia(@RequestBody Transferencia transferencia) {
        // Aqui você pode chamar o serviço ou o repositório para salvar a transferência
        // Transferencia transferenciaSalva = transferenciaService.salvarTransferencia(transferencia);
        // return new ResponseEntity<>(transferenciaSalva, HttpStatus.CREATED);

        // Neste exemplo, estamos apenas retornando a transferência recebida no corpo da requisição
        return new ResponseEntity<>(transferencia, HttpStatus.CREATED);
    }

    @GetMapping("/string")
    public String ola(){
        return "ola";
    }
}
