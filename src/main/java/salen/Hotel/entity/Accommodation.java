package salen.Hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "accommodations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(min = 10)
    @NotBlank
    @Pattern(regexp = "^((?!Free|Offer|Book|Website).)*$")
    private String name;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Category category;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private Location location;
    @URL
    @NotNull
    private String image;
    @Range(min = 0, max = 1000)
    @NotNull
    private Integer reputation;
    @NotNull
    private Integer price;
    @NotNull
    private Integer availability;
    @Min(0)
    @Max(5)
    @NotNull
    private Integer rating;
    @Enumerated(value = EnumType.STRING)
    private ReputationBadge reputationBadge;

    @PrePersist
    void preInsert() {
        setReputationBadge();
    }

    public void setReputationBadge() {
        if (reputation <= 500)
            this.reputationBadge = ReputationBadge.RED;
        else if (reputation <= 799)
            this.reputationBadge = ReputationBadge.YELLOW;
        else
            this.reputationBadge = ReputationBadge.GREEN;
    }
}
