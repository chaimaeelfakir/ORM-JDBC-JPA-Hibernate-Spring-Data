package ma.enset.jpatest.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Entity

@Data //Ajoute les getters et les setters
@NoArgsConstructor //Constructeur sans paramètre
@AllArgsConstructor  //Constructeur avec paramètres
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Crée une entité JPA
    private Long id;

    @Column(length = 50)  //pour effectue des changements sur une colonne
    private String nom;

    @Temporal(TemporalType.DATE) //Est une annotation JPA qui a faite pour les types date
    private Date dateNaissance;
    private boolean malade;
    private int score;

}
