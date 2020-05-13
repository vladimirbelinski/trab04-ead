package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.LoanDao;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private Session session;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Override
    public void makeLoan(String collectionTitle, Integer quantity) {

        // ID_USUARIO
        session.getCurrentUser().getId();

        // ID_EMPRESTIMO - gerado automaticamente

        // ID_EXEMPLAR
        String collectionCopy = collectionCopyService.findCollectionCopyByCollectionTitle(collectionTitle);
        if (!collectionCopy.isEmpty()) {

            LocalDateTime actualDate = dateUtils.getActualDate();
            LocalDateTime dateToReturn = dateUtils.calculateDateToReturn(7); // Por padrão, o cliente poderá ficar com o livro apenas 7 dias

            loanDao.makeLoan(actualDate, dateToReturn,collectionTitle, quantity);
        }
        // retorna erro
    }

    @Override
    public LoanModel findById(Integer id) {
        return null;
    }

    @Override
    public List<LoanModel> listAll() {
        return loanDao.listAll();
    }

    @Override
    public List<LoanModel> listAllByEmail(String userEmail) {
        return loanDao.listAllByEmail(userEmail);
    }

    @Override
    public void renewLoan() {

    }

    @Override
    public Boolean isLoanLate(LocalDateTime expectedReturnDate) {
        return dateUtils.getActualDate().isAfter(expectedReturnDate);
    }
}
