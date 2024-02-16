package u5d10.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u5d10.demo.dto.DispositivoDTO;
import u5d10.demo.entities.Dispositivo;
import u5d10.demo.exception.BadRequestException;
import u5d10.demo.services.DispositivoSer;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoCon {
    @Autowired
    private DispositivoSer dispositivoSer;

    @GetMapping
    public Page<Dispositivo> getallPosts(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String orderBy) {
        return dispositivoSer.getDispositivi(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public Dispositivo getSingleDispositivo(@PathVariable long id) {
        return dispositivoSer.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo seveDisp(@RequestBody @Validated DispositivoDTO dispositivoDTO, BindingResult validation) {
        if (validation.hasErrors()){
            throw  new BadRequestException(validation.getAllErrors());
        }
        return dispositivoSer.save(dispositivoDTO);
    }

    @PutMapping("/{id}")
    public Dispositivo findAndUpdate(@PathVariable long id, @RequestBody @Validated Dispositivo dispositivo,BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return dispositivoSer.findAndUpdate(id,dispositivo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id){
        this.dispositivoSer.findAndDelete(id);
    }

}
