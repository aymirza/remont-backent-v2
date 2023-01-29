package com.example.remontbackent.image_grud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/img")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadEmplNar(@RequestParam(value = "file") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(imageService.saveImg(file));
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        List<ImageDTO> fileInfos = imageService.getAllImg()
                .stream()
                .map(this::pathToFileData2)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);

    }




    private ImageDTO pathToFileData2(Path path) {
        Image image = new Image();
        String filename = path.getFileName().toString();

        image.setImgurl(MvcUriComponentsBuilder.fromMethodName(
                        ImageController.class,
                        "getAllData",
                        filename)
                .build()
                .toString());
        Image image1 = imageService.loadData(filename);
        String imgfullname = image1.getImgfullname();
        String imgtype = image1.getImgtype();


        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setImgurl(MvcUriComponentsBuilder.fromMethodName(
                ImageController.class, "getImage", imgfullname).build().toString());
        imageDTO.setImgfullname(imgfullname);
        imageDTO.setImgtype(imgtype);


        return imageDTO;
    }
    @GetMapping(value = "{imgfullname:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable String imgfullname) {
        Resource file = imageService.loadFile(imgfullname);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    @GetMapping(value = "/file/{imgfullname:.+}")
    @ResponseBody
    public ResponseEntity<?> getAllData(@PathVariable String imgfullname) {
        Image file = imageService.loadData(imgfullname);
        Image image = new Image();
        image.setImgfullname(file.getImgfullname());

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }




}
