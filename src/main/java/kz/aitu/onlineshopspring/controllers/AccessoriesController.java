package kz.aitu.onlineshopspring.controllers;

import kz.aitu.onlineshopspring.models.products.Accessories;
import kz.aitu.onlineshopspring.service.interfaces.AccessoriesServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/accessories")
public class AccessoriesController {

    private final AccessoriesServiceInterface service;

    @PostMapping("/add_accessory")
    public String addAccessory(@RequestBody Accessories accessory){
        return (service.addAccessory(accessory) ? "Accessory was added." : "Accessory adding was failed!");
    }

    @PutMapping("/update_accessory")
    public String updateAccessory(@RequestBody Accessories accessory){
        return (service.updateAccessory(accessory) ? "Accessory was updated." : "Accessory updating was failed!");
    }

    @DeleteMapping("/delete_accessory")
    public String deleteAccessory(@RequestBody int id){
        return (service.deleteAccessory(id) ? ("Accessory of id " + id + " was deleted.") : ("Deleting accessory of id " + id + " was failed!"));
    }

    @GetMapping("id/{id}")
    public Accessories getAccessory(@PathVariable int id){
        return service.getAccessory(id);
    }

    @GetMapping("max_price/{max_price}")
    public List<Accessories> getAccessoriesByPrice(@PathVariable("max_price") double max){
        return service.getAccessoriesByPrice(max);
    }

    @GetMapping("min-max_price/{min_price}-{max_price}")
    public List<Accessories> getAccessoriesByPrice(@PathVariable("min_price") double min, @PathVariable("max_price") double max){
        return service.getAccessoriesByPrice(min, max);
    }

    @GetMapping
    public List<Accessories> getAllAccessories(){
        return service.getAllAccessories();
    }
}
