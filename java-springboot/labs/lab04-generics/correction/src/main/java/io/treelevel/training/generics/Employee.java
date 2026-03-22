package io.treelevel.training.generics;

public class Employee {

    private Long id;
    private String name;

    public Employee() {}

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId()            { return id; }
    public String getName()        { return name; }
    public void setId(Long id)     { this.id = id; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
