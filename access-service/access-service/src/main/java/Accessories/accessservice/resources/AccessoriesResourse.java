package Accessories.accessservice.resources;

import Accessories.accessservice.models.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/accessories")
public class AccessoriesResourse {



@Autowired
    WebClient.Builder webClientBuilder;


    @RequestMapping("/{accessoryId}")

    public Accessory getAccessoryInfo(@PathVariable("accessoryId") String accessoryId){
        return new Accessory(accessoryId,"samsung");

    }
}
