package Accessories.accessservice.resources;

import Accessories.accessservice.models.Accessory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accessories")
public class AccessoriesResourse {

    @RequestMapping("/{accessoryId}")
    public Accessory getAccessoryInfo(@PathVariable("accessoryId") String accessoryId){
        return new Accessory(accessoryId,"samsung");
    }
}
