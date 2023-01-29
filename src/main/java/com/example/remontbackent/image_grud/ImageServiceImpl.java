package com.example.remontbackent.image_grud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder");
        }
    }

    @Override
    public Image saveImg(MultipartFile file) throws IOException {
        try {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            String fullPath = uploadPath + file.getOriginalFilename();
            Image img = new Image();

            img.setImgurl(fullPath);
            img.setImgfullname(file.getOriginalFilename());
            img.setImgtype(file.getContentType());


            return imageRepository.save(img);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file,Error: " + e.getMessage());
        }
    }

    public Resource loadFile(String imgfullname) {
        try {
            Path file = Paths.get(uploadPath)
                    .resolve(imgfullname);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Image loadData(String imgfullname) {
        try {
            return imageRepository.getByImgfullname(imgfullname);
        }catch (Exception e){
            throw new RuntimeException("Error: "+e.getMessage());
        }
    }

    @Override
    public List<Path> getImg() {
        try {
            Path root = Paths.get(uploadPath);
            if (Files.exists(root)) {
                return Files.walk(root, 1)
                        .filter(path -> !path.equals(root))
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException("Could not list the files");
        }
    }

    @Override
    public List<Path> getAllImg() {
        try {
            Path root = Paths.get(uploadPath);
            if (Files.exists(root)){
                return Files.walk(root,1)
                        .filter(path -> !path.equals(root))
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }catch (Exception e){
            throw new RuntimeException("Could not list the files");
        }
    }

    @Override
    public Image getById(Long id) {
        return null;
    }

    @Override
    public Image updateImages(Image images) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


}
