package com.algaworks.ecommerce.domain.model;

import javax.persistence.*;

@Entity
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kitchen)) return false;

        Kitchen kitchen = (Kitchen) o;

        return id.equals(kitchen.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
