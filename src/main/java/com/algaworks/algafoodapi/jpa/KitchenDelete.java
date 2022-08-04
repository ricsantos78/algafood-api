package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

public class KitchenDelete {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegistration bean= applicationContext.getBean(KitchenRegistration.class);
        bean.delete(bean.findById(UUID.fromString("0279c1c0-5ea7-4641-a984-9e03d2a1a0ee")));

    }
}
