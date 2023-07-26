package manga.controller;

import manga.DTO.RegistreRentrant;
import manga.model.Utilisateur;
import manga.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminControleur {

    private AdminService adminService;

    public AdminControleur(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/inscrption")
    public ResponseEntity<Utilisateur> creatAdmin(@RequestBody RegistreRentrant Info) {
        try {
            Utilisateur admin = adminService.createAdmin(Info.getNom(), Info.getPrenom(), Info.getIdentifiant(),
                    Info.getAge(), Info.getNumero(), Info.getEmail(), Info.getMdp(), Info.getMdp2());
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            e.printStackTrace(); // Il vous dit ce qui s'est passé et où dans le code cela s'est produit.
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
	
	
}
