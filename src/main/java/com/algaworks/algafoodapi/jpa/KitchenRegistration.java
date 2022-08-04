package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Component
public class KitchenRegistration {

    @PersistenceContext
    private EntityManager entityManager;

    public List<KitchenModel> listAll() {
        return entityManager.createQuery("from KitchenModel", KitchenModel.class).getResultList(); // Nome da tabela no banco de dados

    }

    @Transactional // Transação de escrita
    public KitchenModel add(KitchenModel kitchen) {
        return entityManager.merge(kitchen); // Atualiza ou insere a cozinha
    }

    public KitchenModel findById(UUID id) {
        return entityManager.find(KitchenModel.class, id);
    }

    @Transactional
    public void delete(KitchenModel kitchen) {
        kitchen = findById(kitchen.getId());
        entityManager.remove(kitchen);
    }
}
