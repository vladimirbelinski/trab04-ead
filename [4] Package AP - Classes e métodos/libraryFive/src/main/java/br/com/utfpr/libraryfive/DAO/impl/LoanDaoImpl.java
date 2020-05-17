package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.LoanDao;
import br.com.utfpr.libraryfive.model.LoanModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("loanDao")
@Transactional
public class LoanDaoImpl implements LoanDao {

    static final Logger LOG = LoggerFactory.getLogger(LoanDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void makeLoan(LoanModel loan) {
        entityManager.persist(loan);
    }

    @Override
    public void deleteLoan(LoanModel loan) {
        LOG.info("deleteLoan started!");
        try {
            entityManager.remove(loan);
            LOG.info("Loan success deleted!");
        } catch (NoResultException e) {
            LOG.info("Loan delete fail, because " + e.getMessage());
        }
    }

    @Override
    public LoanModel findById(Integer id) {
        LOG.info("findById started!");

        List<LoanModel> loans = entityManager.createQuery("select a from LoanModel a where a.id = :id", LoanModel.class)
                .setParameter("id", id)
                .getResultList();

        if (loans.isEmpty()) {
            LOG.info("The loan " + id + " doesn't exist!");

            return null;
        }

        LOG.info("Success! Loan with id " + loans.get(0).getId() + " found!");
        return loans.get(0);
    }

    @Override
    public List<LoanModel> listAll() {
        LOG.info("listAll loans started!");
        List<LoanModel> loans;

        try {
            loans = entityManager. createQuery("select l from LoanModel l", LoanModel.class).getResultList();
            LOG.info("Loans found!");
        } catch (NoResultException e) {
            loans = null;
        }
        return loans;
    }

    @Override
    public List<LoanModel> listAllByEmail(String userEmail) {
        LOG.info("listAll loans ByEmail started!");
        List<LoanModel> loans;

        try {
            loans = entityManager. createQuery("select l from LoanModel l" +
                    " INNER JOIN l.user u" +
                    " WHERE u.email = :email AND l.returnModel.size = 0").
                    setParameter("email", userEmail).
                    getResultList();
            LOG.info("Loans found!");
        } catch (NoResultException e) {
            loans = null;
        }
        return loans;
    }

    @Override
    public void renewLoan() {

    }
}
