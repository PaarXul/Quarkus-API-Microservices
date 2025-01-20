// task-service/src/main/java/org/acme/entity/Task.java
package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {
    public String title;
    public String description;
    public boolean completed;
    public Long categoryId; // Solo mantenemos el ID de la categoría
}