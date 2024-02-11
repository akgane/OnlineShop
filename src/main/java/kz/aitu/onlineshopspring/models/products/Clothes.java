package kz.aitu.onlineshopspring.models.products;

import kz.aitu.onlineshopspring.models.products._abstract.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Clothes extends Product {
    private String fabricType;
    private String[] sizeAndFit;
}
