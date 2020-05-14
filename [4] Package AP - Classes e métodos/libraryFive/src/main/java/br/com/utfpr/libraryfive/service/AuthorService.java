package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.AuthorModel;

import java.util.List;

public interface AuthorService {

    List<AuthorModel> listAllAuthors();

    AuthorModel findById(Integer id);

    AuthorModel findAuthorNameByCollectionTitle(String title);

    void createAuthor(AuthorModel author);

    void editAuthor(AuthorModel author);

    void deleteAuthor(AuthorModel author);
}
