package br.com.animati.springbootapi.repository;

import br.com.animati.springbootapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Spring Jpa já implementa na herança os métodos básicos de CRUD

}
