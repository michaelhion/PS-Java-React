package br.com.banco.services;

import br.com.banco.dto.FiltrosDTO;
import br.com.banco.dto.ResponseDTO;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.specifications.TransferenciaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository repository;
    @Autowired
    ContaRepository contaRepository;

    public ResponseDTO findTransferencias(String startDateTime, String endDateTime, String nomeOperadorTransacao, Long contaId) {
        Specification<Transferencia> spec = Specification.where(null);

        if (startDateTime != null && endDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDateTime dataInicioParsed = null;
            LocalDateTime dataFimParsed = null;

            if (startDateTime != null && !startDateTime.isEmpty()) {
                LocalDate dataInicioLocalDate = LocalDate.parse(startDateTime, formatter);
                dataInicioParsed = LocalDateTime.of(dataInicioLocalDate, LocalTime.MIN);
            }

            if (endDateTime != null && !endDateTime.isEmpty()) {
                LocalDate dataFimLocalDate = LocalDate.parse(endDateTime, formatter);
                dataFimParsed = LocalDateTime.of(dataFimLocalDate, LocalTime.MAX);
            }
            System.out.println(dataInicioParsed+"\n"+dataFimParsed);
            spec = spec.and(TransferenciaSpecification.byPeriodo(dataInicioParsed, dataFimParsed));
        }

        if (nomeOperadorTransacao != null) {
            spec = spec.and(TransferenciaSpecification.byNomeOperadorTransacao(nomeOperadorTransacao));
        }
        Conta conta = new Conta();
        if (contaId != null) {
            conta = contaRepository.getById(contaId);
            spec = spec.and(TransferenciaSpecification.byConta(conta));
        }
        List<Transferencia> transferencia = repository.findAll(spec);
        List<FiltrosDTO> filtrosDTOS = new ArrayList<>();
        ResponseDTO retorno = new ResponseDTO();
        BigDecimal saldoTotal = BigDecimal.ZERO;
        for (Transferencia t : transferencia) {
            FiltrosDTO dto = new FiltrosDTO();
            dto = dto.convertToDTO(t);
            filtrosDTOS.add(dto);

            saldoTotal = saldoTotal.add(t.getValor());
            dto.setSaldo(saldoTotal);
            dto.setSaldoPeriodo(saldoTotal);
        }
        retorno.setFiltrosDTOList(filtrosDTOS);
        retorno.setSaldo(saldoTotal);
        retorno.setSaldoPeriodo(saldoTotal);
        return retorno;
    }
}
