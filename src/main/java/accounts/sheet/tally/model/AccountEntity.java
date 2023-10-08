package accounts.sheet.tally.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ACCOUNT_LIST")
@Table(name="ACCOUNT_LIST")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String eventKey;
    private String title;
    private String account;
}
