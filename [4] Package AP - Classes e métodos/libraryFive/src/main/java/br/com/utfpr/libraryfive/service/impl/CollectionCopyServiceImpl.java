package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.CollectionCopyDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("collectionCopyService")
@Transactional
public class CollectionCopyServiceImpl implements CollectionCopyService {

    @Autowired
    private CollectionCopyDao collectionCopyDao;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    DateUtils dateUtils;

    @Autowired
    private FormatUtils formatUtils;

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {
        collectionCopyDao.createCollectionCopy(collectionCopy);
    }

    @Override
    public List<CollectionCopyModel> listAllCollectionCopy() {
        return collectionCopyDao.listAllCollectionCopy();
    }

    @Override
    public String findCollectionCopyByCollectionTitle(String collectionTitle) {
        collectionCopyDao.findCollectionCopyByCollectionTitle(collectionTitle);
        return collectionTitle;
    }

    @Override
    public CollectionCopyModel findById(Integer id) {
        return null;
    }

    @Override
    public CollectionCopyModel getCollectionCopyByRegisterForm(HttpServletRequest request, Boolean isNewCollectionCopy) {

        CollectionCopyModel collectionCopyModel = new CollectionCopyModel();
        CollectionModel collection = collectionService.findById(formatUtils.getIntegerValue(request.getParameter("collectionId")));

        if (isNewCollectionCopy) {
            collectionCopyModel.setCollection(collection);
            collectionCopyModel.setAcquisitionDate(dateUtils.convertDate(request.getParameter("acquisitionDate")));
            collectionCopyModel.setCollectionCopySituation(getCollectionCopySituation(request.getParameter("collectionCopySituation")));
        } else {
            collectionCopyModel = findById(formatUtils.getIntegerValue(request.getParameter("collectionCopyToEditId")));

            collectionCopyModel.setAcquisitionDate(dateUtils.convertDate(request.getParameter("acquisitionDate")));
            collectionCopyModel.setCollectionCopySituation(getCollectionCopySituation(request.getParameter("collectionCopySituation")));
        }
        return collectionCopyModel;
    }

    private CollectionCopyModel.CollectionCopySituation getCollectionCopySituation(String parameter) {

        if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Disponível.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Disponível;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Devolvido.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Devolvido;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Emprestado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Emprestado;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Extraviado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Extraviado;
        } else if (parameter.equals(CollectionCopyModel.CollectionCopySituation.Reservado.toString())) {
            return CollectionCopyModel.CollectionCopySituation.Reservado;
        }
        return null;
    }
}
