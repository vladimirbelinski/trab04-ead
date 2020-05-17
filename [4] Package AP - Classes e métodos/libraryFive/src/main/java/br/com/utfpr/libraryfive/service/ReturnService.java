package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.LoanModel;

public interface ReturnService {

    void makeReturn(LoanModel loan);
}
