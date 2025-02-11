package com.uniovi.notaneitor.complementarios;



import java.util.Objects;


public class Professor {

    private Long id;
    private String DNI;
    private String name;
    private String surname;
    private String categoria;

    public Professor(){}

    public Professor(Long id ,String DNI, String name, String surname, String categoria) {
        this.id = id;
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "DNI='" + DNI + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
