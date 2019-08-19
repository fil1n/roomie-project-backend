package com.gihub.fil1n.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "group_s")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "max_num_of_users")
    private Long MAX_NUM_OF_USERS;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "whereIsTrusted", fetch = FetchType.LAZY)
    private List<User> trustedUsers;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "whereIsUntrusted", fetch = FetchType.LAZY)
    private List<User> untrustedFollowers;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "knot")
    private List<CommunicationType> communicationTypes;

    @Column(name = "settlement_date")
    private LocalDate settlementDate;

    @Column(name = "ejection_date")
    private LocalDate ejectionDate;

    public boolean isDateOfLivingProper(LocalDate start, LocalDate end) {

        if(getSettlementDate().equals(start) && getEjectionDate().equals(end)) {
            return true;
        }

        if(getSettlementDate().isBefore(start) && getEjectionDate().isAfter(end)) {
            return true;
        }

        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getMAX_NUM_OF_USERS() {
        return MAX_NUM_OF_USERS;
    }

    public void setMAX_NUM_OF_USERS(Long MAX_NUM_OF_USERS) {
        this.MAX_NUM_OF_USERS = MAX_NUM_OF_USERS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<User> getTrustedUsers() {
        return trustedUsers;
    }

    public void setTrustedUsers(List<User> trustedUsers) {
        this.trustedUsers = trustedUsers;
    }

    public List<User> getUntrustedFollowers() {
        return untrustedFollowers;
    }

    public void setUntrustedFollowers(List<User> untrustedFollowers) {
        this.untrustedFollowers = untrustedFollowers;
    }

    public List<CommunicationType> getCommunicationTypes() {
        return communicationTypes;
    }

    public void setCommunicationTypes(List<CommunicationType> communicationTypes) {
        this.communicationTypes = communicationTypes;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public LocalDate getEjectionDate() {
        return ejectionDate;
    }

    public void setEjectionDate(LocalDate ejectionDate) {
        this.ejectionDate = ejectionDate;
    }

    public Group() {}
}
