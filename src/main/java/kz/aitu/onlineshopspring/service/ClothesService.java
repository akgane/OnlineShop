package kz.aitu.onlineshopspring.service;

import kz.aitu.onlineshopspring.models.products.Clothes;
import kz.aitu.onlineshopspring.repositories.ClothesRepository;
import kz.aitu.onlineshopspring.service.interfaces.ClothesServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClothesService implements ClothesServiceInterface {

    private final ClothesRepository repository;

    @Override
    public boolean addClothing(Clothes clothing) {
        return repository.addClothing(clothing);
    }

    @Override
    public boolean updateClothing(Clothes clothing) {
        return repository.updateClothing(clothing);
    }

    @Override
    public boolean deleteClothing(int id) {
        return repository.deleteClothing(id);
    }

    @Override
    public Clothes getClothing(int id) {
        return repository.getClothing(id);
    }

    @Override
    public List<Clothes> getClothesByGender(int gender) {
        return repository.getClothesByGender(gender);
    }

    @Override
    public List<Clothes> getClothesByPrice(double max) {
        return repository.getClothesByPrice(max);
    }

    @Override
    public List<Clothes> getClothesByPrice(double min, double max) {
        return repository.getClothesByPrice(min, max);
    }

    @Override
    public List<Clothes> getAllClothes() {
        return repository.getAllClothes();
    }
}
