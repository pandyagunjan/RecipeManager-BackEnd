package RecipeAppRunner.Services;



import RecipeAppRunner.Entities.RecipePost;
import lombok.extern.slf4j.Slf4j;
import RecipeAppRunner.Repositories.RecipePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j // for logging
public class RecipePostServices{

  //  @Autowired
  //  private RecipePostRepo recipeRepo ;
   private final RecipePostRepo recipePostRepo;

    @Autowired
    public RecipePostServices(RecipePostRepo recipePostRepo) {
        this.recipePostRepo = recipePostRepo;
    }

    public RecipePost createRecipePost(RecipePost recipe){

        return recipePostRepo.save(recipe);

    }

    public RecipePost readRecipePost(Long id){
        log.info("The recipe is being read");
        RecipePost recipePost = recipePostRepo.findRecipePostById(id);
        if (recipePost != null){
            log.info("The recipe post exists and is being read");
            return recipePost;
        } else {
            log.warn("The recipe post could not be found, returning null");
            return null;
        }
    }

  public RecipePost updateRating(Long id , Double newRating)
    {
        log.info("Looking for recipe with id " + id + "in the db" );
        RecipePost recipe= recipePostRepo.findRecipePostById(id);
        log.info("Recipe with id found " + id + "in the db" );
        if(recipe!=null) {
                     recipe.setRating(newRating);
                     recipePostRepo.save(recipe);
            return recipe;
        }
        else {
            log.warn("The customer could not be found, returned null - Make sure to handle this null");
            return null;
        }
    }


 
    public void deleteRecipeById(Long id) {
        boolean recipeExists = recipePostRepo.existsById(id);
        if (!recipeExists){
            throw new IllegalStateException(
                    "Recipe with id " + id + " does not exist."
            );
        }
        recipePostRepo.deleteById(id);
    }

}
