package salen.Hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accommodations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;
    private String image;
    private Integer reputation;
    private Integer price;
    private Integer availability;
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
