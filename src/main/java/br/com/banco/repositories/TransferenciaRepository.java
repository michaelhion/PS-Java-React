package br.com.banco.repositories;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>, JpaSpecificationExecutor<Transferencia> {

    List<Transferencia> findAll(Specification<Transferencia> spec);

    @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta = :conta")
    BigDecimal getSaldoByConta(@Param("conta") Conta conta);
}