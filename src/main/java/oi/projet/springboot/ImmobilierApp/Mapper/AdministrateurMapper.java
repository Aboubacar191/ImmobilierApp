package oi.projet.springboot.ImmobilierApp.Mapper;

import oi.projet.springboot.ImmobilierApp.DTO.AdministrateurDTO;
import oi.projet.springboot.ImmobilierApp.models.Administrateur;

public class AdministrateurMapper {

    public static AdministrateurDTO toDTO(Administrateur admin) {
        AdministrateurDTO dto = new AdministrateurDTO();
        dto.setId(admin.getId());
        dto.setImageURL(admin.getImageURL());
        dto.setNom(admin.getNom());
        dto.setPrenom(admin.getPrenom());
        dto.setAdresse(admin.getAdresse());
        dto.setTelephone1(admin.getTelephone1());
        dto.setTelephone2(admin.getTelephone2());
        dto.setEmail(admin.getEmail());
        dto.setUsername(admin.getUsername());
        dto.setRole(admin.getRole());
        return dto;
    }
}
