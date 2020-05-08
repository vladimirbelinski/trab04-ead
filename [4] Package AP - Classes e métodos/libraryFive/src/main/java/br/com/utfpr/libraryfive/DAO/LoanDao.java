package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.LoanModel;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanDao {

    void makeLoan(LocalDateTime actualDate, LocalDateTime dateToReturn, String collectionTitle, Integer quantity);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByUserEmail(String userEmail);

    void renewLoan();
}
