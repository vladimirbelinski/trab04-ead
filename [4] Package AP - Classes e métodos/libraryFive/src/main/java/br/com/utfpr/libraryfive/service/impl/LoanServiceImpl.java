package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

    @Override
    public void makeLoan(String collectionTitle, Integer quantity) {

    }

    @Override
    public LoanModel findById(Integer id) {
        return null;
    }

    @Override
    public List<LoanModel> listAll() {
        return null;
    }

    @Override
    public List<LoanModel> listAllByUserEmail(String userEmail) {
        return null;
    }

    @Override
    public void renewLoan() {

    }
}
