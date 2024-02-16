package u5d10.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;

    public Dipendente(String username, String name, String surname, String email, Dispositivo dispositivo) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dispositivo = dispositivo;
    }
}
