package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.ReturnModel;

import java.util.List;

public interface ReturnDao {

    void makeReturn(ReturnModel returnModel);

    List<ReturnModel> findAllReturnedLoansByEmail(String userEmail);
}
