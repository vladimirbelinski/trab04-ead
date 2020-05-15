package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;

import java.util.List;

public interface CollectionCopyDao {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    List<CollectionCopyModel> listAllCollectionCopy();

    void findCollectionCopyByCollectionTitle(String collectionTitle);

    CollectionCopyModel findById(Integer id);
}
