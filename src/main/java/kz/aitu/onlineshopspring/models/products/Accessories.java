package kz.aitu.onlineshopspring.models.products;

import kz.aitu.onlineshopspring.models.products._abstract.Product;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Accessories extends Product {
    private int categoryId;
    private String color;
    private String size;
    private boolean hasSize;
}
