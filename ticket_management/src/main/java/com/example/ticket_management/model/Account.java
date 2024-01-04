package com.example.ticket_management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @Column(columnDefinition = "int default 0")
    private boolean isDelete;
    @Column(name = "password")
    private String acPassword;

    @ManyToOne
    @JoinColumn(name = "type_account",referencedColumnName = "id")
    private TypeAccount typeAccount;

   @OneToMany(mappedBy = "account")
    private List<CarAttendant> carAttendants;

    public Account() {
    }

    public Account(Integer id, String name, String email, boolean isDelete, String acPassword, TypeAccount typeAccount, List<CarAttendant> carAttendants) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isDelete = isDelete;
        this.acPassword = acPassword;
        this.typeAccount = typeAccount;
        this.carAttendants = carAttendants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getAcPassword() {
        return acPassword;
    }

    public void setAcPassword(String acPassword) {
        this.acPassword = acPassword;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public List<CarAttendant> getCarAttendants() {
        return carAttendants;
    }

    public void setCarAttendants(List<CarAttendant> carAttendants) {
        this.carAttendants = carAttendants;
    }
}
