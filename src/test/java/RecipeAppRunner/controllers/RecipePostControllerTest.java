package RecipeAppRunner.controllers;

import RecipeAppRunner.Entities.RecipePost;
import RecipeAppRunner.Enums.Categories;
import RecipeAppRunner.RecipeManagerAppApplication;
import RecipeAppRunner.Services.RecipePostServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) //Indicates that the class should use Spring’s JUnit facilities
@SpringBootTest(classes = RecipeManagerAppApplication.class) //The classes attribute specifies the annotated classes to use for loading an ApplicationContext
@AutoConfigureMockMvc //need this in Spring Boot test ,Add this @AutoConfigureMockMvc to enable and configure auto-configuration of MockMvc.Else throws error
public class RecipePostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipePostServices recipePostServices;

    ObjectMapper objectMapper = new ObjectMapper();
    RecipePost recipe;
    RecipePost updatedRecipe;

    @Before
    public void setup() {

//Setting up the data using builder pattern
        recipe = RecipePost.builder()
                .id(1L)
                .author(null)
                .category(Categories.VEGETARIAN)
                .name("VEGGIEE ENCHILLIDAS")
                .rating(2.0)
                .build();


        updatedRecipe = RecipePost.builder()
                .id(1L)
                .author(null)
                .category(Categories.VEGETARIAN)
                .name("VEGGIEE ENCHILLIDAS")
                .rating(5.6)
                .build();
    }


    @Test
    public void updateRecipeRating() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(updatedRecipe);

        Mockito.when(recipePostServices.updateRating(any(),any())).thenReturn(updatedRecipe);

        mockMvc.perform(put("/api/recipeposts/{id}/{rating}", 1L , 5.5)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}
