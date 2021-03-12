package RecipeAppRunner.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToMany
    private List<RecipePost> userRecipes;

    @OneToMany
    private List<RecipePost> favoriteRecipes;

    private Double userRating;
    // until we figure out how to make different profiles have different roles
    private boolean isAdmin;

}
