package accounts.sheet.tally.service;

import accounts.sheet.tally.model.AccountEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class EntityComponent {


        @PersistenceContext
        private EntityManager entityManager;

        public List<AccountEntity> findAllEntities() {
            Query query = entityManager.createQuery("SELECT a FROM ACCOUNT_LIST a", AccountEntity.class);
            return query.getResultList();
        }
    }

