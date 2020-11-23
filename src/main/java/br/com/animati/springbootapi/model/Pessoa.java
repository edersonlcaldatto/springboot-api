package br.com.animati.springbootapi.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o banco que incrementar o id
    private long id;

    @Column(nullable = false)
    private String nome;



}
