package com.mr.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;

    public User() {
        id = 0L;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }


}
