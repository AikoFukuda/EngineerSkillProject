package io.javabrains;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "engineers")
@Where(clause="delflg = 0")
public class Users implements UserDetails {
//	loadbyusernameで認証してもらえる
	/**
	 * 
	 */
	private static final long serialVersionUID = 4716026485682130203L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min = 1, max = 50, message = "名前は0文字以上50文字未満に設定してください")
    private String name;
	
	@Column(unique = true)
//	@NotEmpty
//	@Email(message = "emailの形式ではありません")
//	@Size(min = 1, max = 256, message = "emailの長さを変えてください")
    private String email;

    private int delflg;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	public int getDelflg() {
		return delflg;
	}
	
	public void changeDel() {
		this.delflg =1;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column()
    private String password;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }
    
	public void setPassword(String password) {
		this.password = password;
	}

    @Override
	public String getUsername()
    {
        return this.email;
    }
//    もともと入っているものをオーバーライドさせてemailを軸にさせる

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
