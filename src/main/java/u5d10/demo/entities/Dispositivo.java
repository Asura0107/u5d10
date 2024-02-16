package u5d10.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import u5d10.demo.enums.DisponibileDisp;

import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    @Enumerated(EnumType.STRING)
    private DisponibileDisp disponibileDisp;

    public Dispositivo(String tipo, DisponibileDisp disponibileDisp) {
        this.tipo = tipo;
        this.disponibileDisp = disponibileDisp;
    }

}
