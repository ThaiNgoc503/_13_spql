package ngoc.spql.practice._13_spql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public SinhVien() {
    }


    public SinhVien(String name) {
        this.name = name;
    }

    public SinhVien(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public SinhVien(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public SinhVien(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = " + age +
                '}';
    }
}
