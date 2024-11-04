package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public static ProductDTO of(Product product){
        ProductDTO dto = new ProductDTO();
        dto.setId(Long.parseLong(product.getId()));
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(new BigDecimal(product.getPrice()));
        return dto;
    }
}
