package com.example.springsqlwrest.model;

import lombok.*;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.springsqlwrest.audit.Auditable;

@Entity
@NamedNativeQuery(name= "Tutorial.findByIdIs",
    query="SELECT * FROM testdb.tutorials",resultClass=Tutorial.class)
@Getter @Setter
@NoArgsConstructor
@Table(name = "tutorials")
public class Tutorial extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public boolean isPublished() {
        return published;
    }
}
