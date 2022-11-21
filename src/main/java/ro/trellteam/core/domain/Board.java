package ro.trellteam.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BOARD")
@Table(name = "te_tr_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "date_created")
    private Date dateCreated;
    @Basic
    @Column(name = "id_dep")
    private Long idDep;
    @Basic
    @Column(name = "`version`")
    private String version;
    @Basic
    @Column(name = "`release`")
    private Date release;

    @OneToMany(
            targetEntity = Card.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "te_tr_card_board_link",
            joinColumns = @JoinColumn(name = "id_board"),
            inverseJoinColumns = @JoinColumn(name = "id_card")
    )
    private List<Card> cards;

    @Transactional
    public void addCard(Card card) {
        if(cards == null) cards = new ArrayList<>();
        cards.add(card);
    }

    @Transactional
    public void removeCard(Card card) {
        if(cards != null) cards.remove(card);
    }

    @Transactional
    public void purgeCards() {
        if(cards != null) cards.clear();
    }
}
