package oi.projet.springboot.ImmobilierApp.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.DiscriminatorValue;
import lombok.RequiredArgsConstructor;
import oi.projet.springboot.ImmobilierApp.models.CustomUserDetails;
import oi.projet.springboot.ImmobilierApp.models.Utilisateur;
import oi.projet.springboot.ImmobilierApp.repository.UtilisateurRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        // Charger l'utilisateur depuis la base de données en utilisant le repository
        Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé: " + nomUtilisateur);
        }

        // Récupérer le type d'utilisateur (qui correspond à la valeur de la colonne discriminante)
        String userType = utilisateur.getClass().getAnnotation(DiscriminatorValue.class).value();

        // Créer une autorité basée sur le type d'utilisateur
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(userType));

        // Retourner un UserDetails personnalisé qui inclut l'id de l'utilisateur
        return new CustomUserDetails(utilisateur.getId(), utilisateur.getNomUtilisateur(), utilisateur.getMotDePasse(), authorities);
    }
}
