package com.crud.financeiro.repository;

import com.crud.financeiro.dto.EntidadeDTO;
import com.crud.financeiro.model.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Entidades extends JpaRepository<Entidade, Long>, QueryByExampleExecutor<Entidade> {

    List<Entidade> findByNomeContainingIgnoreCase(String nome);

    @Query("select e from Entidade e where lower(e.nome) like %?1% order by e.nome")
    Page<Entidade> porNome(String nome, Pageable pageable);

    @Query("select new com.crud.financeiro.dto.EntidadeDTO(codigo, nome) from Entidade where lower(nome) like %?1%")
    List<EntidadeDTO> filtradas(String nome);

}
