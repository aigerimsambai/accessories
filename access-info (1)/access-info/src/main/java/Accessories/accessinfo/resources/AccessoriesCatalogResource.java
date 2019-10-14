package Accessories.accessinfo.resources;

import Accessories.accessinfo.models.Accessory;
import Accessories.accessinfo.models.CatalogItem;
import Accessories.accessinfo.models.Rating;
import Accessories.accessinfo.models.UserRating;
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

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);


        return ratings.getUserRating().stream().map(rating -> {
            Accessory accessory = restTemplate.getForObject("http://localhost:8082/accessories/" + rating.getAccessoryId(), Accessory.class);
           /* Accessory accessory = webClientBuilder.build()
                   .get()
                   .uri("http://localhost:8082/accessories/" + rating.getAccessoryId())
                   .retrieve()
                   .bodyToMono(Accessory.class)
                   .block();*/

            return new CatalogItem(accessory.getName(), "Samsung", rating.getRating());
        })
                .collect(Collectors.toList());

    }

    }


/* Accessory accessory = webClientBuilder.build()
                   .get()
                   .uri("http://localhost:8082/accessories/" + rating.getAccessoryId())
                   .retrieve()
                   .bodyToMono(Accessory.class)
                   .block();*/