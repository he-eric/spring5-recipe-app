package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public String getUnitOfMeasure() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
}
