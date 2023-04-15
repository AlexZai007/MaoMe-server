package com.maome.springjwt.service.impl;

import com.maome.springjwt.models.Note;
import com.maome.springjwt.models.Photo;
import com.maome.springjwt.repository.NoteRepository;
import com.maome.springjwt.repository.PhotoRepository;
import com.maome.springjwt.security.services.UserDetailsImpl;
import com.maome.springjwt.service.NoteService;
import com.maome.springjwt.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final PhotoRepository photorepository;
    private final TokenService tokenService;

    public NoteServiceImpl(NoteRepository repository, PhotoRepository photorepository, TokenService tokenService) {
        this.repository = repository;
        this.photorepository = photorepository;
        this.tokenService = tokenService;
    }




    @Override
    @Transactional
    public Note addPhotoToNote(String token, Long note_id, Long photo_id){

        Note note = repository.getById(note_id);
        Photo photo = photorepository.findById(photo_id).get();

        List<Photo> photosLocal = note.getPhotos();
        photosLocal.add(photo);

        photo.setNote(note);
        photorepository.save(photo);

        note.setPhotos(photosLocal);
        repository.save(note);

        return note;

    }


    @Override
    @Transactional
    public Note deletePhotoFromNote(String token, Long note_id, Long photo_id){

        Note note = repository.getById(note_id);
        Photo photo = photorepository.findById(photo_id).get();

        List<Photo> photosLocal = note.getPhotos();
        photosLocal.remove(photo);

        photo.setNoteNull(note);
        photorepository.save(photo);

        note.setPhotos(photosLocal);
        repository.save(note);

        return note;

    }

    @Override
    public Note updateSavedNot(String token, Note note) {

        List<Note> ownedNotes = getAllByOwner(tokenService.tokenToID(token)); // получаем все заметки в собственности
        List<Long> idList = ownedNotes.stream()//получаем все ID заметок в собственности
                .map(Note::getId)
                .collect(Collectors.toList());



        Note note_outdate = getById(note.getId()); //получаем старую версию заметки

        //проверяем мы владелец
        if (idList.contains(note.getId())) {

            //добавляем не изменные параметры
            note.setOwner(note_outdate.getOwner());
            note.setLongitude(note_outdate.getLongitude());
            note.setLatitude(note_outdate.getLatitude());
            note.setCreatedAt(note_outdate.getCreatedAt());

            return update(note);

        } else {
            return null;
        }
    }

    @Override
    public Note createNewNote(String token, Note note) {

        note.setOwner(tokenService.tokenToID(token));
        note.setCreatedAt(new Date());
        return add(note);
    }

    @Override
    public Note getNoteByID(String token, Long id) {
        List<Note> ownedNotes = getAllByOwner(tokenService.tokenToID(token));
        Note note = getById(id);

        if (ownedNotes.contains(note)) {
            note.getPhotos().size(); // загрузка фотографий из базы данных
            return note;
        } else {
            return null;
        }
    }

    @Override
    public Note deleteNoteByID(String token, Long id) {
        List<Note> ownedNotes = getAllByOwner(tokenService.tokenToID(token));
        Note note = getById(id);

        if (ownedNotes.contains(note)) {

            deleteById(note.getId());
            return null;

        } else {
            return null;
        }
    }


    @Override
    public Note add(Note note) {
        return repository.save(note);
    }

    @Override
    @Transactional
    public List<Note> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public List<Note> getAllByOwner(Long ID) {
        return repository.findAllByOwner(ID);
    }

    @Override
    public Note getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Note update(Note note) {
        return repository.save(note);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }



}
