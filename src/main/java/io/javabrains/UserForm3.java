package io.javabrains;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserForm3 {
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

//	落とし込めていない
//	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以下に設定してください")
	private String password;
//	@Pattern(regexp = "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", 
//	message = "パスワードを大文字・小文字・数字１文字以上に設定してください")
	
	private String newPassword;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return password;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}