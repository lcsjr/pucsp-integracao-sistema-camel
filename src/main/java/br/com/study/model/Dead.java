package br.com.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Dead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String file;

    private String error;

    public Dead() {

    }
}
