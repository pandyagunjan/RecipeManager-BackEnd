package RecipeAppRunner.Controllers;


import RecipeAppRunner.Entities.Profile;
import RecipeAppRunner.Services.ProfileServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j // for logging
public class ProfileController {

    private final ProfileServices profileServices;

    public ProfileController(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }

    public ResponseEntity<Profile> readById(@PathVariable("id") Long id) throws Exception{
        if (new ResponseEntity<>(profileServices.readProfileById(id), HttpStatus.OK) == null) throw new Exception("Error, Invalid Profile Id");
        else
            return new ResponseEntity<>(profileServices.readProfileById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProfile(@PathVariable("id") Long id){
        profileServices.deleteProfileById(id);
    }
}
