package oi.projet.springboot.ImmobilierApp.Services;

import oi.projet.springboot.ImmobilierApp.models.CompteUser;
import oi.projet.springboot.ImmobilierApp.repository.CompteUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CompteUserRepository compteUserRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recherche de l'utilisateur dans la base de données
        CompteUser compteUser = compteUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));

        // Transformer le rôle en liste de GrantedAuthority
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(compteUser.getRoleEntities().getName()));

        // Retourner un UserDetails basé sur CompteUser
        return new User(
                compteUser.getUsername(),
                compteUser.getPassword(),
                authorities
        );
    }

    }


