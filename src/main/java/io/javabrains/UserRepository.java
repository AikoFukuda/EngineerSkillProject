package io.javabrains;

import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByEmail(String email);
	public Optional<Users> findById(Long id);
	public Page<Users> findByNameContainingOrderByName(Pageable pageable, String name);
	public void saveAndFlush(UserForm userForm);

	@Query("select u from Users u order by u.name")
	public Page<Users> findAllOrderByName(Pageable pageable);
	
}