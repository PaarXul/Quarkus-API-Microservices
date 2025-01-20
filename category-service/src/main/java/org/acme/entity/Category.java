// category-service/src/main/java/org/acme/entity/Category.java
package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends PanacheEntity {
    public String name;
}