package io.javabrains;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
//	暗号化してくれている
	
	private List<UserForm> userList = new ArrayList<UserForm>();
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/regist")
	public String createUser(@ModelAttribute("formModel") UserForm userForm) {
		return "regist";
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/regist")
	@Transactional(readOnly=false)
//	意味の確認：一連のものが終わらないと完了したことにならない
	public String create(
//			public String のStringは戻り値の型になる
			@ModelAttribute("formModel")
//			
			@Valid UserForm userForm,
			BindingResult result,
			Users user
			) {
//		ModelAndView res = null;
		if (result.hasErrors()){
			return "regist";
		}
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		userRepository.save(user);
		return "redirect:/login";
	}
}