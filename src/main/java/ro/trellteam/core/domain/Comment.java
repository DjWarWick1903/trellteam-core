package ro.trellteam.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "COMMENT")
@Table(name = "te_tr_card_comm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comm_text")
    private String text;
    @Column(name = "comm_date")
    private Date commentDate;
    @Column(name = "id_acc")
    private Long accountID;

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
