package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.LoanModel;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanDao {

    void makeLoan(LoanModel loan);

    void deleteLoan(LoanModel loan);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByEmail(String userEmail);

    void renewLoan();
}
