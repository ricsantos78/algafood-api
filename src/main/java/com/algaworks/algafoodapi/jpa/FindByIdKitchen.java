package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

public class FindByIdKitchen {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegistration bean= applicationContext.getBean(KitchenRegistration.class);
        KitchenModel kitchen = bean.findById(UUID.fromString("5bb7a28c-667e-423b-af5c-0062661a484b"));
        System.out.println(kitchen.getName());
    }
}
