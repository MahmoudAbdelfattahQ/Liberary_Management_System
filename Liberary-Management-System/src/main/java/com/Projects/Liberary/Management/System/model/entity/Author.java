package com.Projects.Liberary.Management.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id ;

    @Column(length = 50)
    private String name ;

    @ManyToMany(mappedBy = "authors" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Books> books ;



}
