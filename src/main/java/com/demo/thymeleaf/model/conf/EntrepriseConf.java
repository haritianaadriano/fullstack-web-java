package com.demo.thymeleaf.model.conf;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseConf {
    @Id
    private int id;
    private String name;
    private String description;
    private String slogan;
    private String adress;
    private String email;
    private String telephones;
}
