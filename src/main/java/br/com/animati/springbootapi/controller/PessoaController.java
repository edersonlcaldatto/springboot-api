package br.com.animati.springbootapi.controller;

import br.com.animati.springbootapi.model.Pessoa;
import br.com.animati.springbootapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.PercentEscaper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired // Injeta uma instancia
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoaFind = pessoaRepository.findById(id);
        if (pessoaFind.isPresent())
            return new ResponseEntity<Pessoa>(pessoaFind.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable(value = "id") long id){
        Optional<Pessoa> pessoaDelete = pessoaRepository.findById(id);
        if(pessoaDelete.isPresent()){
            pessoaRepository.delete(pessoaDelete.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable(value = "id") Long id, @RequestBody Pessoa newPessoa) {
        Optional<Pessoa> pessoaOld = pessoaRepository.findById(id);
        if(pessoaOld.isPresent()){
            Pessoa pessoa = pessoaOld.get();
            pessoa.setNome(newPessoa.getNome());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa,HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
