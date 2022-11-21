package ro.trellteam.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "CARDLOG")
@Table(name = "te_tr_card_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardLog implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "log_text")
    private String text;
    @Column(name = "log_date")
    private Date logDate;
    @Column(name = "id_acc")
    private Long accountID;

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
