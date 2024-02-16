package u5d10.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotEmpty(message = "lo username è obbligatorio")
        @Size(min=3,max=20, message ="lo username deve essere compreso tra 3 e 20 caratteri" )
        String username,
        @NotEmpty(message = "lo username è obbligatorio")
        @Size(min=3,max=20, message ="lo username deve essere compreso tra 3 e 20 caratteri" )
        String name,
        @NotEmpty(message = "lo username è obbligatorio")
        @Size(min=3,max=20, message ="lo username deve essere compreso tra 3 e 20 caratteri" )
        String surname,
        @NotEmpty(message = "lo username è obbligatorio")
        @Email(message = "l'email inserita non è un indirizzo valido")
        @Size(min=3,max=20, message ="lo username deve essere compreso tra 3 e 20 caratteri" )
        String email,
        @NotNull
        long dispositivoId

) {
}
