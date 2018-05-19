package io.javabrains;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserForm {
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotEmpty
	@Size(min = 1, max = 50, message = "名前は0文字以上50文字未満に設定してください")
	private String name;
	
	@NotEmpty
	@Email(message = "emailの形式ではありません")
	@Size(min = 1, max = 256, message = "emailの長さを変えてください")
	private String email;
	

//	落とし込めていない
	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以下に設定してください")
	private String password;
//	@Pattern(regexp = "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", 
//	message = "パスワードを大文字・小文字・数字１文字以上に設定してください")

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return password;
	}

}