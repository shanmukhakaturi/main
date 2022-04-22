package com.ros.inventory.springsecurity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account,UUID>{

	@Query(value="select s from Account s where s.name=:username")
	Optional<Account> findByUserName(String username);

	@Query(value="select s from Account s where s.name=:username")
	Account findByUsername(String username);

}
