package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class KitchenConsult {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegistration bean= applicationContext.getBean(KitchenRegistration.class);
        List<KitchenModel> kitchens = bean.listAll();
        for (KitchenModel kitchen : kitchens) {
            System.out.println("nome " + kitchen.getName());
        }
    }
}
