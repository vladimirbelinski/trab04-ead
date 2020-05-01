package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "USUARIO_TELEFONE")
public class UserPhoneModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Max(11)
    @Column(name = "NUMERO_TELEFONE")
    private String phoneNumber;

    @Id
    @Column(name = "ID_USUARIO")
    private Integer id;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "PRINCIPAL", columnDefinition = "TINYINT")
    private Integer main;

    // relations
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserModel user;

    // getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMain() {
        return main;
    }

    public void setMain(Integer main) {
        this.main = main;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
