package com.maome.springjwt.controllers;

import com.maome.springjwt.models.Note;
import com.maome.springjwt.models.Photo;
import com.maome.springjwt.repository.NoteRepository;
import com.maome.springjwt.repository.PhotoRepository;
import com.maome.springjwt.security.services.UserDetailsImpl;
import com.maome.springjwt.service.NoteService;
import com.maome.springjwt.service.PhotoService;
import com.maome.springjwt.service.TokenService;
import com.sun.xml.bind.util.AttributesImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ContentDisposition;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/notes")
public class NoteControler {


    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private NoteRepository noteRepository;


    private final NoteService noteService;
    private final TokenService tokenService;
    private final PhotoService photoService;

    @Autowired
    public NoteControler(NoteService noteService, TokenService tokenService, PhotoService photoService) {
        this.noteService = noteService;
        this.tokenService = tokenService;
        this.photoService = photoService;
    }


    // Get all notes (filter by owner)
    @GetMapping("/all")
    public List<Note> getUserIdFromToken(@RequestHeader("Authorization") String token) {
        return noteService.getAllByOwner(tokenService.tokenToID(token));
    }

    // Create new note (auto add owner ID + add server date)
    @PostMapping("/create")
    public Note CreateNewNote(@RequestHeader("Authorization") String token, @Validated @RequestBody Note note) {
        return noteService.createNewNote(token, note);
    }

    // Edit note
    @PostMapping("/edit")
    public Note UpdateNewNote(@RequestHeader("Authorization") String token, @Validated @RequestBody Note note) {
        return noteService.updateSavedNot(token, note);
    }

    // Get all notes (filter by owner)
    @GetMapping("/read/{id}")
    public Note getNoteByID(@RequestHeader("Authorization") String token, @PathVariable long id) throws AccessDeniedException {
        return noteService.getNoteByID(token, id);
    }

    // Get all notes (filter by owner)
    @DeleteMapping("/delete/{id}")
    public Note deleteNoteByID(@RequestHeader("Authorization") String token, @PathVariable long id) {
        return noteService.deleteNoteByID(token, id);
    }


}
