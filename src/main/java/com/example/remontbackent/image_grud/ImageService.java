package com.example.remontbackent.image_grud;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public interface ImageService {
    Image saveImg(

                                   MultipartFile file
    ) throws IOException;

    List<Path> getImg(
    );
    List<Path> getAllImg(
    );

    Image getById(Long id);

    Image updateImages(Image images);

    void deleteById(Long id);

    Resource loadFile(String imgfullname);
    Image loadData(String imgfullname);


}
