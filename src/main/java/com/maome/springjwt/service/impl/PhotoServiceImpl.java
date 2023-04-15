package com.maome.springjwt.service.impl;

import com.maome.springjwt.models.Photo;
import com.maome.springjwt.repository.PhotoRepository;
import com.maome.springjwt.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(String url, String name, Long owner) {
        Photo photo = new Photo();

        photo.setName(name);
        photo.setData(url);
        photo.setOwner(owner);

        photoRepository.save(photo);
        return photo;
    }


    @Override
    public Photo update(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo getPhoto(Long id) {
        return photoRepository.findById(id).orElse(null);
    }
}