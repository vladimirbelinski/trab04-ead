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
import java.time.LocalDateTime;
import java.util.List;

@Repository("loanDao")
@Transactional
public class LoanDaoImpl implements LoanDao {

    static final Logger LOG = LoggerFactory.getLogger(LoanDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void makeLoan(LocalDateTime actualDate, LocalDateTime dateToReturn, String collectionTitle, Integer quantity) {

    }

    @Override
    public LoanModel findById(Integer id) {
        return null;
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
                    " WHERE u.email = :email").
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
