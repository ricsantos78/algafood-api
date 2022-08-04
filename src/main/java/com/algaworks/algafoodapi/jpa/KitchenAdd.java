package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

public class KitchenAdd {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegistration bean = applicationContext.getBean(KitchenRegistration.class);
        KitchenModel kitchen1 = new KitchenModel();
        //kitchen1.setId(UUID.randomUUID());
        kitchen1.setName("Brasileira");
        bean.add(kitchen1);

        KitchenModel kitchen2 = new KitchenModel();
        //kitchen2.setId(UUID.randomUUID());
        kitchen2.setName("Japonesa");
        bean.add(kitchen2);
    }
}
