package u5d10.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import u5d10.demo.entities.Dispositivo;

public interface DispositivoDAO extends JpaRepository<Dispositivo, Long> {
}
