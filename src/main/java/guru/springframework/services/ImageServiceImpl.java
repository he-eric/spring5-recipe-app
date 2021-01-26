package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository repository;

    public ImageServiceImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        log.debug("received a file");
        try {
            Recipe recipe = repository.findById(id).get();

            int i = 0;
            Byte[] byteObject = new Byte[(file.getBytes().length)];
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            recipe.setImage(byteObject);
            repository.save(recipe);
        }
        catch (IOException e) {
            log.debug("Failed to save image");
            e.printStackTrace();
        }
    }
}
