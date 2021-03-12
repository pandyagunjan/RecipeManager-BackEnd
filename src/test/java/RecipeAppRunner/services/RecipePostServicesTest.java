package RecipeAppRunner.services;

import RecipeAppRunner.Entities.RecipePost;
import RecipeAppRunner.Enums.Categories;
import RecipeAppRunner.Repositories.*;
import RecipeAppRunner.Services.RecipePostServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import RecipeAppRunner.RecipeManagerAppApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


//https://howtodoinjava.com/spring-boot2/testing/springboottest-annotation/


@RunWith(SpringJUnit4ClassRunner.class) //Indicates that the class should use Spring’s JUnit facilities
@SpringBootTest(classes = RecipeManagerAppApplication.class) //The classes attribute specifies the annotated classes to use for loading an ApplicationContext
public class RecipePostServicesTest {

    @InjectMocks  //creates objects and inject mocked dependencies
    RecipePostServices recipePostServices;

    @Mock (name = "mockRepo")  //creates mocks - Below error if we try to use InjectMocks here!
    //Cannot instantiate @InjectMocks field named 'recipePostRepo'! Cause: the type 'RecipePostRepo' is an interface.
    RecipePostRepo recipePostRepo;

   //Dummy data for performing the test
    RecipePost recipe ;

    @Before
    public void setup()
    {
        //Setting up the data using builder pattern
         recipe = RecipePost.builder()
                .id(1L)
                .author(null)
                .category(Categories.VEGETARIAN)
                .name("VEGGIEE ENCHILLIDAS")
                 .rating(2.0)
                .build();

    }

//    @Test
//    public void readRecipeById() {
//
//        String expectedName= "VEGGIEE ENCHILLIDAS";
//        Mockito.when(recipePostRepo.findRecipePostById(1L)).thenReturn(recipe);
//        String testName = recipePostServices.readRecipePost(1L).getName();
//        Assert.assertEquals(expectedName, testName);
//    }
//    @Test
//    public void createRecipe() {
//
//        String expectedName= "VEGGIEE ENCHILLIDAS";
//        RecipePost expectedRecipe = recipe;
//        Mockito.when(recipePostRepo.save(recipe)).thenReturn(expectedRecipe);
//        recipePostServices.createRecipePost(recipe);
//        Assert.assertEquals(expectedName, expectedRecipe.getName());
//    }

    @Test
    public void updateRecipeRating() {

        RecipePost expectedRecipe = recipe;
        //Stubbing Methods - Usually, we want to configure the mock and define what to do when specific methods of the mock are called.
        //This is called stubbing.
        //There are 2 ways as seen below , but in doReturn.when - Actual call is made to the method so currently Failing.
        // Mockito.doReturn(recipe).when(recipePostRepo.findRecipePostById(1L));
        Mockito.when(recipePostRepo.findRecipePostById(1L)).thenReturn(expectedRecipe);
        Mockito.when(recipePostRepo.save(any())).thenReturn(expectedRecipe);
        recipePostServices.updateRating(1L , 3.5);

        Assert.assertEquals(java.util.Optional.of(3.5), Optional.of(expectedRecipe.getRating()) );
    }
}
