package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
