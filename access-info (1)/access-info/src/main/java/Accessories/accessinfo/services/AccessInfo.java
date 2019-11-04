package Accessories.accessinfo.services;

import Accessories.accessinfo.models.Accessory;
import Accessories.accessinfo.models.CatalogItem;
import Accessories.accessinfo.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccessInfo {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Accessory accessory = restTemplate.getForObject("http://localhost:8082/accessories/" + rating.getAccessoryId(), Accessory.class);
        return new CatalogItem(accessory.getName(), "Samsung", rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Accessory name not found", "", rating.getRating());
    }
}
