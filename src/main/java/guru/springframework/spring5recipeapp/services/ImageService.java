package guru.springframework.spring5recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImage(final String recipeId, final MultipartFile file);
}
