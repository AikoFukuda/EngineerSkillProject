package io.javabrains;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {

	public static final String PAGE = "/";
    private static final String HTML = "top";

    @Autowired
	UserRepository userRepository;
    
    @ModelAttribute
    UserForm userForm() {
    	return new UserForm();
    }  
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage(@RequestParam String name, ModelAndView mav, Pageable pageable) {
		mav.setViewName("top");
		Page<Users> list = userRepository.findByNameContainingOrderByName(pageable, ("%" + name + "%"));
		mav.addObject("name", name);
//		tagprocessorのところで受け取ったもの
		mav.addObject("size", pageable.getPageSize());
		mav.addObject("page", pageable.getPageNumber());
//		なぜかっこに入れるのか　
//		検索+昇順
//		listに20分割するものを渡してあげる
//		名前でとってくれるSQL文を作る
//		現状では昇順ではない
		mav.addObject("datalist", list);
//		listだけでは足りない
		return mav;
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(method=RequestMethod.GET, path="/editPassword/{id}")
	public String pass(@ModelAttribute("formModel") UserForm3 userform3) {
		return "editPassword";
	}
            
   @RequestMapping(method=RequestMethod.POST, path="/editPassword")
   public ModelAndView editpass(@ModelAttribute("formModel")
                                @Valid UserForm3 userForm3,
                                BindingResult result,
                                Users user,
                                ModelAndView mov) {
       if (result.hasErrors()) { //入力NG
    	   mov.addObject("title", "失敗");
           mov.setViewName("editPassword");
       } else if (passwordEncoder.matches(userForm3.getPassword(), user.getPassword())) {
           user.setPassword(passwordEncoder.encode(userForm3.getNewPassword()));
           userRepository.saveAndFlush(user);
           mov = new ModelAndView("redirect:/find?size=20&page=0&fstr=");
       }                
       return mov;
  }

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit mydata.");
		Optional<Users> user = userRepository.findById(Long.valueOf(id));
		mav.addObject("formModel", user.get());
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly=false)
//	意味の確認：一連のものが終わらないと完了したことにならない
	public String change(
//			public String のStringは戻り値の型になる
			@ModelAttribute("formModel")
			@Valid Users user,
			BindingResult result
			) {
		if (result.hasErrors()){
			return "edit";
		}else {
		userRepository.saveAndFlush(user);
		return "redirect:/?size=20&page=0&name=";
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "delete mydata.");
		Optional<Users> user = userRepository.findById(Long.valueOf(id));
		mav.addObject("formModel", user.get());
		return mav;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam Long id, Users user, ModelAndView mav) {
		user.changeDel();
		userRepository.save(user);
		return new ModelAndView("redirect:/?size=20&page=0&name=");
	}
}