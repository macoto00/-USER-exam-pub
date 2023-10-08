package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Drink {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private double price;
    private boolean isForAdult;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
