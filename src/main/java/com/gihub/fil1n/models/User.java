package com.gihub.fil1n.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_s")
public class User {

    public enum Sex {MALE, FEMALE};

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String name;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Group ownedGroup;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Image avatar;

    @Column(name = "sex")
    private Sex sex;

    @Column(name = "rental_period")
    private Integer rentalPeriod;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "user_info")
    private String userInfo;

    @Column(name = "phone")
    private String phone;

    @Column(name = "max_roommates_number")
    private Integer maxRoommatesNumber;

    @Column(name = "language")
    private String lang;

    @Column(name = "max_neighbors_num")
    private Long maxNeighborsNum;

//    @OneToMany(mappedBy = "person")
//    private List<Question> polls;

//    @OneToOne
//    private UserPreferences userPreferences;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_habbits",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "habbit_id", referencedColumnName = "id")}
    )
    private List<Habbit> habbitList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "trusted_knot",
            joinColumns = {@JoinColumn(name = "trusted_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trusted_group_id", referencedColumnName = "id")}
    )
    private List<Group> whereIsTrusted = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "untrusted_knot",
            joinColumns = {@JoinColumn(name = "untrusted_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "untrusted_group_id", referencedColumnName = "id")}
    )
    private List<Group> whereIsUntrusted = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "current_city_id"
    )
    private City currentCity;

    @Column(name = "add_info")
    private String additionalInfo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "native_city_id"
    )
    private City nativeCity;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_language",
            joinColumns = {@JoinColumn(name = "curr_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lang_id")}
    )
    private List<Language> userLanguageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getAvatar() { return avatar; }

    public Faculty getFaculty() { return faculty; }

    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public Integer getMaxRoommatesNumber() {
        return maxRoommatesNumber;
    }

    public void setMaxRoommatesNumber(Integer maxRoommatesNumber) {
        this.maxRoommatesNumber = maxRoommatesNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Group getOwnedGroup() {
        return ownedGroup;
    }

    public void setOwnedGroup(Group ownedGroup) {
        this.ownedGroup = ownedGroup;
    }

    public List<Habbit> getHabbitList() {
        return habbitList;
    }

    public void setHabbitList(List<Habbit> habbitList) {
        this.habbitList = habbitList;
    }

    public List<Group> getWhereIsTrusted() {
        return whereIsTrusted;
    }

    public void setWhereIsTrusted(List<Group> whereIsTrusted) {
        this.whereIsTrusted = whereIsTrusted;
    }

    public List<Group> getWhereIsUntrusted() {
        return whereIsUntrusted;
    }

    public Long getMaxNeighborsNum() {
        return maxNeighborsNum;
    }

    public void setMaxNeighborsNum(Long maxNeighborsNum) {
        this.maxNeighborsNum = maxNeighborsNum;
    }

    public void setWhereIsUntrusted(List<Group> whereIsUntrusted) {
        this.whereIsUntrusted = whereIsUntrusted;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(City nativeCity) {
        this.nativeCity = nativeCity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Language> getUserLanguageList() {
        return userLanguageList;
    }

    public void setUserLanguageList(List<Language> userLanguageList) {
        this.userLanguageList = userLanguageList;
    }

    public User() {}
}
