package com.example.remontbackent.image_grud;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select img from Image img where img.imgfullname=?1")
    Image getByImgfullname(String imgfullname);
}
