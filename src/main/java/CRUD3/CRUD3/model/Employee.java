package CRUD3.CRUD3.model;


import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    public Employee(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    /// <summary>
    /// Имя
    /// </summary>
    @Column(name = "Name")
    public String name;
    /// <summary>
    /// Фамилия
    /// </summary>
    @Column(name = "Surname")
    public String surname;

    /// <summary>
    /// Отчество
    /// </summary>
    @Column(name = "Patronymic")
    public String patronymic;
    /// <summary>
    /// Статус
    /// </summary>

    @Column(name = "Status")
    public String status;

    /// <summary>
    /// Должность
    /// </summary>
    @Column(name = "Position")
    public String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /// <summary>
    /// Фото
    /// </summary>
    @Column(name = "Photopath")
    public  String photoPath;

}

