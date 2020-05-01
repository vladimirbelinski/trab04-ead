package br.com.utfpr.libraryfive.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "OBRA")
public class CollectionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Max(6)
    @Column(name = "ID_OBRA")
    private Integer id;

    @NotNull
    //@Max(300)
    @Column(name = "TITULO")
    private String title;

    @NotNull
    @Column(name = "ANO_PUBLICACAO")
    private Integer publicationYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_OBRA", columnDefinition="ENUM('Literatura','Tese/Monografia','Outros')", nullable = false )
    private CollectionType collectionType;

    public enum CollectionType {
        Literatura, Tese{public String toString(){return "Tese/Monografia";}}, Outros
    }

    // relations
    // obra 1:n autor
    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AuthorModel> authorList;

    // obra 1:n exemplar
    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionCopyModel> collectionCopyList;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public List<AuthorModel> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorModel> authorList) {
        this.authorList = authorList;
    }

    public List<CollectionCopyModel> getCollectionCopyList() {
        return collectionCopyList;
    }

    public void setCollectionCopyList(List<CollectionCopyModel> collectionCopyList) {
        this.collectionCopyList = collectionCopyList;
    }
}
