package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.ReturnDao;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.ReturnService;
import br.com.utfpr.libraryfive.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("returnService")
@Transactional
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnDao returnDao;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Autowired
    private DateUtils dateUtils;

    @Override
    public void makeReturn(LoanModel loan) {
        ReturnModel returnModel = new ReturnModel();
        returnModel.setLoan(loan);
        returnModel.setReturnDate(dateUtils.convertLocalDateTimeToDate(dateUtils.getActualDate()));


        returnDao.makeReturn(returnModel);
        collectionCopyService.editCollectionCopySituation(loan.getCollectionCopy(), "Disponível");
    }
}
