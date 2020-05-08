package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.CollectionCopyDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("collectionCopyDao")
@Transactional
public class CollectionCopyDaoImpl implements CollectionCopyDao {

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {

    }

    @Override
    public void findCollectionCopyByCollectionTitle(String collectionTitle) {

    }
}
