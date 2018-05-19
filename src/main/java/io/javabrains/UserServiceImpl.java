package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(email))
        {
            throw new UsernameNotFoundException("Email is empty");
        }

        Users users = userRepository.findByEmail(email);
        if (users == null)
        {
            throw new UsernameNotFoundException("Email is not registered");
        }

        return users;
//        userの中身を見つける
    }
	
	@Autowired
    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
