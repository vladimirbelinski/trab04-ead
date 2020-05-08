package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.CollectionCopyDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("collectionCopyService")
@Transactional
public class CollectionCopyServiceImpl implements CollectionCopyService {

    @Autowired
    private CollectionCopyDao collectionCopyDao;

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {
        collectionCopyDao.createCollectionCopy(collectionCopy);
    }

    @Override
    public String findCollectionCopyByCollectionTitle(String collectionTitle) {
        collectionCopyDao.findCollectionCopyByCollectionTitle(collectionTitle);
        return collectionTitle;
    }
}
