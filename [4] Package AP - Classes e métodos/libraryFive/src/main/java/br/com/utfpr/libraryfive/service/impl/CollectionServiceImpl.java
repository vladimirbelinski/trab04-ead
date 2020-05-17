package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.CollectionDao;
import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service("collectionService")
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FormatUtils formatUtils;

    @Override
    public void createCollection(CollectionModel collection) {

        CollectionModel existingCollection = findByTitle(collection.getTitle());
        if (existingCollection == null) {
            collectionDao.createCollection(collection);
        } else {

        }
    }

    @Override
    public void editCollection(CollectionModel collection) {
        collectionDao.editCollection(collection);
    }

    @Override
    public void updateStatus(CollectionModel collection, String bookStatus) {
        collectionDao.updateStatus(collection, bookStatus);
    }

    @Override
    public void deleteCollection(CollectionModel collection) {
        collectionDao.deleteCollection(collection);
    }

    @Override
    public void setActive(CollectionModel collection, Boolean active) {
        collectionDao.setActive(collection, active);
    }

    @Override
    public List<CollectionModel> listAllCollections() {
        return collectionDao.listAllCollections();
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        return collectionDao.findAllAvailableCollection();
    }

    @Override
    public CollectionModel findById(Integer id) {
        return collectionDao.findById(id);
    }

    @Override
    public CollectionModel findByTitle(String title) {
        return collectionDao.findByTitle(title);
    }

    @Override
    public CollectionModel findByType(String type) {
        return collectionDao.findByType(type);
    }

    @Override
    public List<CollectionModel> showCollectionInfo() {
        return collectionDao.showCollectionInfo();
    }

    @Override
    public boolean isAvailable(Integer collectionId, Integer quantity) {
        return collectionDao.isAvailable(collectionId, quantity);
    }

    @Override
    public CollectionModel getCollectionByRegisterForm(HttpServletRequest request, Boolean isNewCollection) {

        CollectionModel collectionModel = new CollectionModel();
        AuthorModel author = new AuthorModel();

        if (isNewCollection) {
            author = authorService.findByName(request.getParameter("author"));

            collectionModel.setTitle(request.getParameter("title"));
            collectionModel.setAuthorCollectionList(getAuthor(author));
            collectionModel.setPublicationYear(formatUtils.getIntegerValue(request.getParameter("publicationYear")));
            collectionModel.setCollectionType(getCollectionType(request.getParameter("collectionType")));

        } else {
            collectionModel = findById(formatUtils.getIntegerValue(request.getParameter("collectionToEditId")));
            author = authorService.findByName(request.getParameter("authorName"));

            collectionModel.setTitle(request.getParameter("title"));
            collectionModel.getAuthorCollectionList().iterator().next().setAuthor(author);
            collectionModel.setPublicationYear(formatUtils.getIntegerValue(request.getParameter("publicationYear")));
            collectionModel.setCollectionType(getCollectionType(request.getParameter("collectionType")));
        }
        return collectionModel;
    }

    private CollectionModel.CollectionType getCollectionType(String parameter) {

        if (parameter.equals(CollectionModel.CollectionType.Literatura.toString())) {
            return CollectionModel.CollectionType.Literatura;
        } else if (parameter.equals(CollectionModel.CollectionType.TeseMonografia.toString())) {
            return CollectionModel.CollectionType.TeseMonografia;
        } else if (parameter.equals(CollectionModel.CollectionType.Outros.toString())) {
            return CollectionModel.CollectionType.Outros;
        }
        return null;
    }

    private List<AuthorCollectionModel> getAuthor(AuthorModel author) {
        AuthorCollectionModel authorCollection = new AuthorCollectionModel();
        authorCollection.setAuthor(author);

        return Arrays.asList(authorCollection);
    }

}
