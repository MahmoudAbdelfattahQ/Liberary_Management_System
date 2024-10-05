package com.Projects.Liberary.Management.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String category_id;

    @Column(length = 50)
    private String categoryName;

    @ManyToMany(mappedBy = "categories" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Books> books;


}
