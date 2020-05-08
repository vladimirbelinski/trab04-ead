package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;

public interface CollectionCopyDao {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    void findCollectionCopyByCollectionTitle(String collectionTitle);
}
