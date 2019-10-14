package Accessories.accessratingservice.resource;

import Accessories.accessratingservice.models.Rating;
import Accessories.accessratingservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingAccessoriesResource {

    @RequestMapping("/{accessoryId}")
    public Rating getRating( @PathVariable("accessoryId") String accessoryId){
        return new Rating(accessoryId,4);
    }
    @RequestMapping("users/{userId}")
    public UserRating getUserRating( @PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
