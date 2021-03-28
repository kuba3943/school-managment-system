package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.repository.HeadMasterRepository;

@Service
@AllArgsConstructor
public class HeadMasterService implements UserDetailsService {

    private final HeadMasterRepository headMasterRepository;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return headMasterRepository.findHeadMasterByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }
}
