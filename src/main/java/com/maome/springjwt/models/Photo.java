package com.maome.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(nullable = true)
    private String data;


    @Column(name = "owner")
    private Long owner;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "note_id")
    private Note note;

    public Photo() {}

    public Photo(String name){this.name = name;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {this.data = data;}

    public Long getOwner() {return owner;}

    public void setOwner(Long owner) {this.owner = owner;}

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setNoteNull(Note note) {this.note = null;}






}
