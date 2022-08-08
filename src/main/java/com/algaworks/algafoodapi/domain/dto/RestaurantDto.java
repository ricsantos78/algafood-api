package com.algaworks.algafoodapi.domain.dto;

import com.algaworks.algafoodapi.domain.model.KitchenModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantDto {
    private String name;
    private BigDecimal freight;
    private KitchenModel kitchen;
}
