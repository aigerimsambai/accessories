package Accessories.accessinfo.resources;

import Accessories.accessinfo.models.Accessory;
import Accessories.accessinfo.models.CatalogItem;
import Accessories.accessinfo.models.Rating;
import Accessories.accessinfo.models.UserRating;
import Accessories.accessinfo.services.AccessInfo;
import Accessories.accessinfo.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class AccessoriesCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    AccessInfo accessInfo;
    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating ratings = userRatingInfo.getUserRating(userId);


        return ratings.getUserRating().stream()
                .map(rating -> accessInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

    }
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    private CatalogItem getCatalogItem(Rating rating) {
        Accessory accessory = restTemplate.getForObject("http://localhost:8082/accessories/" + rating.getAccessoryId(), Accessory.class);
        return new CatalogItem(accessory.getName(), "Samsung", rating.getRating());
    }
    private CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Accessory name not found", "", rating.getRating());
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    private UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating( @PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(
                new Rating("0",0)
        ));

        return userRating;
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId)
    {
        return Arrays.asList(new CatalogItem("No accessories","",0));
    }
    }


/* Accessory accessory = webClientBuilder.build()
                   .get()
                   .uri("http://localhost:8082/accessories/" + rating.getAccessoryId())
                   .retrieve()
                   .bodyToMono(Accessory.class)
                   .block();*/