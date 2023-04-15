package com.maome.springjwt.service;


import com.maome.springjwt.models.Note;
import com.maome.springjwt.models.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface PhotoService {


    Photo savePhoto(String url, String name, Long owner);
    Photo update(Photo photo);
    Photo getPhoto(Long id);




}