package u5d10.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import u5d10.demo.dao.DispositivoDAO;
import u5d10.demo.entities.Dipendente;
import u5d10.demo.entities.Dispositivo;
import u5d10.demo.exception.NotFoundException;

@Service
public class DispositivoSer {
    @Autowired
    private DispositivoDAO dispositivoDAO;

    public Dispositivo findById(long id) {
        return this.dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
