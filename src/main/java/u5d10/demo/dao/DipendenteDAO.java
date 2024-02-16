package u5d10.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import u5d10.demo.entities.Dipendente;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
