package com.algaworks.algafoodapi.domain.dto;

import com.algaworks.algafoodapi.domain.model.StateModel;
import lombok.Data;

@Data
public class CityDto {

    private String name;
    private StateModel state;
}
