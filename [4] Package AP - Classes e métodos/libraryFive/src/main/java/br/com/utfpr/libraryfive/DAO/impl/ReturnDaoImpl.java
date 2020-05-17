package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.ReturnDao;
import br.com.utfpr.libraryfive.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("returnDao")
@Transactional
public class ReturnDaoImpl implements ReturnDao {

    static final Logger LOG = LoggerFactory.getLogger(ReturnDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void makeReturn(ReturnModel returnModel) {
        entityManager.persist(returnModel);
    }
}
