package com.maome.springjwt.service;

import com.maome.springjwt.models.Note;


import java.util.List;

public interface NoteService {

    Note add(Note note);

    List<Note> getAll();

    List<Note> getAllByOwner(Long ID);

    Note getById(long id);

    Note update(Note note);

    Note addPhotoToNote(String token, Long note_id, Long photo_id);

    Note deletePhotoFromNote(String token, Long note_id, Long photo_id);

    Note updateSavedNot(String token, Note note);

    Note createNewNote(String token, Note note);

    Note getNoteByID(String token, Long id);

    Note deleteNoteByID(String token, Long id);

    void deleteById(long id);

}
