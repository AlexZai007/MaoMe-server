package com.maome.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.maome.springjwt.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    //поиск по владельцу
    List<Note> findAllByOwner(Long owner);

}
