package RecipeAppRunner.Services;

import RecipeAppRunner.Entities.Profile;
import RecipeAppRunner.Repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServices {

    private final ProfileRepo profileRepo;

    @Autowired
    public ProfileServices(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public Profile readProfileById(long id){
        return profileRepo.findProfileById(id);
    }

    public void deleteProfileById(Long id) {
        boolean profileExists = profileRepo.existsById(id);
        if (!profileExists){
            throw new IllegalStateException(
                    "Profile with id " + id + " does not exist."
            );
        }
        profileRepo.deleteById(id);
    }
}
