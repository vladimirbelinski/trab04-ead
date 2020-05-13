package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.AuthorModel;

import java.util.List;

public interface AuthorDao {

    List<AuthorModel> listAllAuthors();

    AuthorModel findById(Integer id);

    AuthorModel findByCollectionTitle(String collectionTitle);

    void createAuthor(AuthorModel author);

    void editAuthor(AuthorModel author);

    void deleteAuthor(AuthorModel author);
}
