package kz.aitu.onlineshopspring.service.interfaces;

import kz.aitu.onlineshopspring.models.products.Accessories;

import java.util.List;

public interface AccessoriesServiceInterface {
    boolean addAccessory(Accessories accessory);
    boolean updateAccessory(Accessories accessory);
    boolean deleteAccessory(int id);
    Accessories getAccessory(int id);
    List<Accessories> getAccessoriesByGender(int gender);
    List<Accessories> getAccessoriesByPrice(double max);
    List<Accessories> getAccessoriesByPrice(double min, double max);
    List<Accessories> getAllAccessories();
}
