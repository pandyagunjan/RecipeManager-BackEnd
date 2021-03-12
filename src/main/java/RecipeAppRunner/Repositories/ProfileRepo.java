package RecipeAppRunner.Repositories;

import RecipeAppRunner.Entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends CrudRepository<Profile, Long> {

    Profile findProfileById(long id);
    Profile save(Profile profile);
}
