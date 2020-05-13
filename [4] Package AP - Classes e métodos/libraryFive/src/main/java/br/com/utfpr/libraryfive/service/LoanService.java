package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LoanService {

    void makeLoan(String collectionTitle, Integer quantity);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByEmail(String userEmail);

    void renewLoan();

    Boolean isLoanLate(LocalDateTime expectedReturnDate);
}
