package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum Difficulty {
    EASY, MODERATE, KIND_OF_HARD, HARD
}
