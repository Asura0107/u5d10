package u5d10.demo.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import u5d10.demo.dao.DispositivoDAO;
import u5d10.demo.dto.DipendenteDTO;
import u5d10.demo.dto.DispositivoDTO;
import u5d10.demo.entities.Dipendente;
import u5d10.demo.entities.Dispositivo;
import u5d10.demo.enums.DisponibileDisp;
import u5d10.demo.exception.BadRequestException;
import u5d10.demo.exception.NotFoundException;

import java.io.IOException;
import java.util.Random;

@Service
public class DispositivoSer {
    @Autowired
    private DispositivoDAO dispositivoDAO;


    public Dispositivo findById(long id) {
        return this.dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Page<Dispositivo> getDipendenti(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dispositivoDAO.findAll(page);
    }
    public static <T extends Enum<?>> T getRandomEnum(Class<T> enumeration) {
        Random random = new Random();
        T[] values = enumeration.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    public Dispositivo save(DispositivoDTO newdisp) {
        Dispositivo dispositivo= new Dispositivo(newdisp.tipo(),getRandomEnum(DisponibileDisp.class));
        return dispositivo;
    }
    public Dispositivo findAndUpdate (long id, Dispositivo update){
        Dispositivo dispositivo=this.findById(id);
        dispositivo.setTipo(update.getTipo());
        dispositivo.setDisponibileDisp(update.getDisponibileDisp());
        return dispositivoDAO.save(dispositivo);
    }

    public void findAndDelete(long id){
        Dispositivo found=this.findById(id);
        dispositivoDAO.delete(found);
    }

}
