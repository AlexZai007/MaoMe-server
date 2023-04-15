package com.maome.springjwt.controllers;


import com.maome.springjwt.models.Note;
import com.maome.springjwt.models.Photo;
import com.maome.springjwt.repository.NoteRepository;


import com.maome.springjwt.service.NoteService;
import com.maome.springjwt.service.PhotoService;
import com.maome.springjwt.service.TokenService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/photo")
public class PhotoControler {

    private final NoteService noteService;
    private final TokenService tokenService;
    private final PhotoService photoService;

    @Autowired
    public PhotoControler(NoteService noteService, TokenService tokenService , PhotoService photoService){
        this.noteService = noteService;
        this.tokenService = tokenService;
        this.photoService = photoService;
    }


    //AddNewPhoto
    @PostMapping("upload")
    public Photo uploadPhoto(@RequestHeader("Authorization") String token, @Validated @RequestBody Photo photo){
        return photoService.savePhoto(photo.getData(), photo.getName(), tokenService.tokenToID(token));
    }

    @GetMapping("get/{id}")
    public Photo getPhoto(@RequestHeader("Authorization") String token, @PathVariable Long id){
        return photoService.getPhoto(id);
    }

    //Delete Photo
    @DeleteMapping("removeAdd/note_id/{note_id}/photo_id/{photo_id}")
    public Note removePhotoFromNote(@RequestHeader("Authorization") String token, @PathVariable Long note_id, @PathVariable Long photo_id) throws IOException {
        return noteService.deletePhotoFromNote(token, note_id, photo_id);
    }

    //Add Photo
    @PostMapping("addPhotoToNote/note_id/{note_id}/photo_id/{photo_id}")
    public Note addPhotoToNote(@RequestHeader("Authorization") String token, @PathVariable Long note_id, @PathVariable Long photo_id) throws IOException {
        return noteService.addPhotoToNote(token, note_id, photo_id);
    }


}
