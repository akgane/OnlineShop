package kz.aitu.onlineshopspring.controllers;

import kz.aitu.onlineshopspring.models.products.Clothes;
import kz.aitu.onlineshopspring.service.interfaces.ClothesServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/clothes")
public class ClothesController {

    private final ClothesServiceInterface service;

    @PostMapping("/add_clothing")
    public String addClothing(@RequestBody Clothes clothing){
        return (service.addClothing(clothing) ? "Clothing was added." : "Clothing adding was failed!");
    }

    @PutMapping("/update_clothing")
    public String updateClothing(@RequestBody Clothes clothing){
        return (service.updateClothing(clothing) ? "Clothing was updated." : "Clothing updating was failed!");
    }

    @DeleteMapping("/delete_clothing")
    public String deleteClothing(@RequestBody int id){
        return (service.deleteClothing(id) ? ("Clothing of id " + id + " was deleted.") : ("Deleting clothing of id " + id + " was failed!"));
    }

    @GetMapping("id/{id}")
    public Clothes getClothing(@PathVariable int id, Model model){
        return service.getClothing(id);
    }

    @GetMapping("gender/{gender}")
    public List<Clothes> getClothesByGender(@PathVariable int gender){
        return service.getClothesByGender(gender);
    }

    @GetMapping("max_price/{max_price}")
    public List<Clothes> getClothesByPrice(@PathVariable("max_price") double max){
        return service.getClothesByPrice(max);
    }

    @GetMapping("min-max_price/{min_price}-{max_price}")
    public List<Clothes> getClothesByPrice(@PathVariable("min_price") double min, @PathVariable("max_price") double max){
        return service.getClothesByPrice(min, max);
    }

    @GetMapping
    public List<Clothes> getAllClothes(){
        return service.getAllClothes();
    }


}
