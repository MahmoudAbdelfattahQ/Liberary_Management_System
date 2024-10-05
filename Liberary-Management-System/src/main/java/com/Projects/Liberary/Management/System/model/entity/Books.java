package com.Projects.Liberary.Management.System.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Books {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @Column
    private String title;

    @ManyToMany
    @JoinTable(name = "books_authors"
            ,joinColumns = {@JoinColumn(name = "book_id")}
            ,inverseJoinColumns = {@JoinColumn(name="author_id")})
    private List<Author> authors;


    @ManyToMany
    @JoinTable(name = "books_categories"
            ,joinColumns = {@JoinColumn(name = "book_id")}
            ,inverseJoinColumns = {@JoinColumn(name="category_id")})
    private List<Category> categories;


    @ManyToMany
    @JoinTable(name = "books_publishers"
            ,joinColumns = {@JoinColumn(name = "book_id")}
            ,inverseJoinColumns = {@JoinColumn(name="publisher_id")})
    private List<Publisher> publishers;





}
