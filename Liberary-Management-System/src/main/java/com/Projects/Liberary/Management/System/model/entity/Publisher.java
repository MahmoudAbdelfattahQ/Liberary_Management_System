package com.Projects.Liberary.Management.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(length = 50)
    private String name;

    @ManyToMany(mappedBy = "publishers" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Books> books;


}
