package oi.projet.springboot.ImmobilierApp.Mapper;


import oi.projet.springboot.ImmobilierApp.DTO.LocataireDTO;
import oi.projet.springboot.ImmobilierApp.DTO.PaiementDTO;
import oi.projet.springboot.ImmobilierApp.models.Locataire;
import oi.projet.springboot.ImmobilierApp.models.Paiement;
import oi.projet.springboot.ImmobilierApp.models.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LocataireMapper {

    private final PaiementMapper paiementMapper;

    @Autowired
    public LocataireMapper(PaiementMapper paiementMapper) {
        this.paiementMapper = paiementMapper;
    }

    // Entité -> DTO
    public LocataireDTO toDTO(Locataire locataire) {
        if (locataire == null) return null;

        List<PaiementDTO> paiementDTOs = null;
        if (locataire.getPaiementList() != null) {
            paiementDTOs = locataire.getPaiementList()
                    .stream()
                    .map(paiementMapper::toDTO)
                    .collect(Collectors.toList());
        }

        String nomResidence = locataire.getResidence() != null
                ? locataire.getResidence().getNomResidence()
                : null;

        return new LocataireDTO(
                locataire.getId(),
                locataire.getImageURL(),
                locataire.getNom(),
                locataire.getPrenom(),
                locataire.getAdresse(),
                locataire.getTelephone1(),
                locataire.getTelephone2(),
                locataire.getEmail(),
                locataire.getUsername(),
                locataire.getRole().name(),
                locataire.getNumeroDuLit(),
                locataire.getContrat(),
                nomResidence,
                paiementDTOs
        );
    }

    // DTO -> Entité (paiements et résidence à gérer à part si besoin)
    public Locataire toEntity(LocataireDTO dto, Paiement paiement, Residence residence) {
        if (dto == null) return null;

        Locataire locataire = new Locataire();
        locataire.setId(dto.getId());
        locataire.setImageURL(dto.getImageURL());
        locataire.setNom(dto.getNom());
        locataire.setPrenom(dto.getPrenom());
        locataire.setAdresse(dto.getAdresse());
        locataire.setTelephone1(dto.getTelephone1());
        locataire.setTelephone2(dto.getTelephone2());
        locataire.setEmail(dto.getEmail());
        locataire.setUsername(dto.getUsername());
        locataire.setRole(Locataire.Role.valueOf(dto.getRole()));
        locataire.setNumeroDuLit(dto.getNumeroDuLit());
        locataire.setContrat(dto.getContrat());
        locataire.setPaiementList((List<Paiement>) paiement);
        locataire.setResidence(residence);


        return locataire;
    }
}
