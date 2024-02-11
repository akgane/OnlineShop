package kz.aitu.onlineshopspring.service.interfaces;

import kz.aitu.onlineshopspring.models.products.Clothes;

import java.util.List;
public interface ClothesServiceInterface {
    boolean addClothing(Clothes clothing);
    boolean updateClothing(Clothes clothing);
    boolean deleteClothing(int id);
    Clothes getClothing(int id);
    List<Clothes> getClothesByGender(int gender);
    List<Clothes> getClothesByPrice(double max);
    List<Clothes> getClothesByPrice(double min, double max);
    List<Clothes> getAllClothes();
}
