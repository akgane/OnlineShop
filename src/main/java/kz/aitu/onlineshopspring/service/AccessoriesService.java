package kz.aitu.onlineshopspring.service;

import kz.aitu.onlineshopspring.models.products.Accessories;
import kz.aitu.onlineshopspring.repositories.AccessoriesRepository;
import kz.aitu.onlineshopspring.service.interfaces.AccessoriesServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccessoriesService implements AccessoriesServiceInterface {
    private final AccessoriesRepository repository;
    @Override
    public boolean addAccessory(Accessories accessory) {
        return repository.addAccessory(accessory);
    }

    @Override
    public boolean updateAccessory(Accessories accessory) {
        return repository.updateAccessory(accessory);
    }

    @Override
    public boolean deleteAccessory(int id) {
        return repository.deleteAccessory(id);
    }

    @Override
    public Accessories getAccessory(int id) {
        return repository.getAccessory(id);
    }

    @Override
    public List<Accessories> getAccessoriesByGender(int gender) {
        return repository.getAccessoriesByGender(gender);
    }

    @Override
    public List<Accessories> getAccessoriesByPrice(double max) {
        return repository.getAccessoriesByPrice(max);
    }

    @Override
    public List<Accessories> getAccessoriesByPrice(double min, double max) {
        return repository.getAccessoriesByPrice(min, max);
    }

    @Override
    public List<Accessories> getAllAccessories() {
        return repository.getAllAccessories();
    }
}
