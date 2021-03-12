package RecipeAppRunner.Repositories;

import RecipeAppRunner.Entities.RecipePost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipePostRepo extends CrudRepository<RecipePost, Long> {

  RecipePost findRecipePostById(Long id);
}
