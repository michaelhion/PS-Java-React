package br.com.banco.specifications;

import java.time.LocalDateTime;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

public class TransferenciaSpecification {

    public static Specification<Transferencia> byPeriodo(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return (Root<Transferencia> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.between(root.get("dataTransferencia"), startDateTime, endDateTime);
            return predicate;
        };
    }

    public static Specification<Transferencia> byNomeOperadorTransacao(String nomeOperadorTransacao) {
        return (Root<Transferencia> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("nomeOperadorTransacao"), nomeOperadorTransacao);
            return predicate;
        };
    }

    public static Specification<Transferencia> byConta(Conta conta) {
        return (Root<Transferencia> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<Transferencia, Conta> joinConta = root.join("conta");
            Predicate predicate = criteriaBuilder.equal(joinConta.get("id"), conta.getId());
            return predicate;
        };
    }
}
