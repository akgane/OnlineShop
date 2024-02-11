package kz.aitu.onlineshopspring.models.products._abstract;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Product {
    static int idGen = 0;
    private int id;
    private int gender; //0 - male, 1 - female, 2 - genderless
    private double price;
    private String title;
    private String description;
    private String[] images;
}
