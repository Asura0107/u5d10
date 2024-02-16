package u5d10.demo.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import u5d10.demo.dao.DipendenteDAO;
import u5d10.demo.dto.DipendenteDTO;
import u5d10.demo.entities.Dipendente;
import u5d10.demo.entities.Dispositivo;
import u5d10.demo.enums.DisponibileDisp;
import u5d10.demo.exception.BadRequestException;
import u5d10.demo.exception.NotFoundException;

import java.io.IOException;

@Service
public class DipendenteSer {
    @Autowired
    private DipendenteDAO dipendenteDAO;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private DispositivoSer dispositivoSer;

    public Page<Dipendente> getDipendenti(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dipendenteDAO.findAll(page);
    }

    public Dipendente findById(long id) {
        return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente save(DipendenteDTO newuser) {
        dipendenteDAO.findByEmail(newuser.email()).ifPresent(u->{
            throw new BadRequestException("l'email "+ u.getEmail()+" è già esistente");
        });
        Dispositivo dispositivo=dispositivoSer.findById(newuser.dispositivo());
        if (dispositivo.getDisponibileDisp()==DisponibileDisp.DISMESSO||dispositivo.getDisponibileDisp()==DisponibileDisp.IN_MANUNTENZIONE){
            dispositivo=null;
        }
        return dipendenteDAO.save(
                new Dipendente(newuser.avatar(),newuser.username(),newuser.name(),newuser.surname(),newuser.email(), dispositivo)
        );
    }
    public Dipendente findAndUpdate (long id, Dipendente update){
        Dipendente dipendente=this.findById(id);
        dipendente.setAvatar(update.getAvatar());
        dipendente.setUsername(update.getUsername());
        dipendente.setName(update.getName());
        dipendente.setSurname(update.getSurname());
        dipendente.setEmail(update.getEmail());
        return dipendenteDAO.save(dipendente);
    }

    public void findAndDelete(long id){
        Dipendente found=this.findById(id);
        dipendenteDAO.delete(found);
    }

    public String findAndPostAvatar(long id, MultipartFile image)throws IOException {
        Dipendente dipendente=this.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        dipendente.setAvatar(url);
        dipendenteDAO.save(dipendente);
        return url;
    }

    public void findAndPostDisp(long id, long dispid){
        Dipendente dipendente=this.findById(id);
        Dispositivo dispositivo=dispositivoSer.findById(dispid);
        dipendente.setDispositivo(dispositivo);
        dipendenteDAO.save(dipendente);
    }
}

