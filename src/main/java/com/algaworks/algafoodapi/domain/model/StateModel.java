package com.algaworks.algafoodapi.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_STATE") // Nome da tabela no banco de dados
public class StateModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) // Gerador de chave primária
    @Column(name = "ID_STATE") // Nome da coluna no banco de dados
    private Long id;

    @Column(name = "NM_STATE", nullable = false) // Nome da coluna no banco de dados
    private String name; // Nome do estado
}
