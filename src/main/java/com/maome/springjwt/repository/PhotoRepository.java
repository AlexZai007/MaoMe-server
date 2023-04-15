package com.maome.springjwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.maome.springjwt.models.Photo;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {


}
