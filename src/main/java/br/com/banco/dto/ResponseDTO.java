package br.com.banco.dto;

import java.math.BigDecimal;
import java.util.List;

public class ResponseDTO {
    private List<FiltrosDTO> filtrosDTOList;
    private BigDecimal saldo;
    private BigDecimal saldoPeriodo;

    public List<FiltrosDTO> getFiltrosDTOList() {
        return filtrosDTOList;
    }

    public void setFiltrosDTOList(List<FiltrosDTO> filtrosDTOList) {
        this.filtrosDTOList = filtrosDTOList;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldoPeriodo() {
        return saldoPeriodo;
    }

    public void setSaldoPeriodo(BigDecimal saldoPeriodo) {
        this.saldoPeriodo = saldoPeriodo;
    }
}
