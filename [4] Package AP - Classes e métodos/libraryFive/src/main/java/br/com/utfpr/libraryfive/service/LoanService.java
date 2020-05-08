package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.LoanModel;

import java.util.List;

public interface LoanService {

    void makeLoan(String collectionTitle, Integer quantity);

    LoanModel findById(Integer id);

    List<LoanModel> listAll();

    List<LoanModel> listAllByUserEmail(String userEmail);

    void renewLoan();
}
