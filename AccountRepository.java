package com.example.jparevesion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jparevesion.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	//finder method
	//name
	List<Account> findByAccnm(String accnm);
	List<Account> findByAccnmContaining(String pattern); //("sh");
	List<Account> findByAccnmStartingWith(String prefix); //("s");
	List<Account> findByAccnmEndingWith(String suffix); //("kar");
	List<Account> findByAccnmIn(List<String> names); //give name of array list to store multiple names in database
	
	//Type
	List<Account> findByAcctype(String acctype);
	List<Account> findByAcctypeIgnoreCase(String type);// check capital and small
	
	//Balance
	List<Account> findByBalanceGreaterThan(float amount);
	List<Account> findByBalanceLessThan(float amount);
	List<Account> findByBalanceBetween(float min, float max);
	
	//Multiple finding
	//select * from accounts where accnm='praffull' and acctype='saving';
	List<Account> findByAccnmAndAcctype(String name,String type);
	List<Account> findByAccnmOrAcctype(String name,String type);
	//secelct * from accounts where acctype='saving' and balance>800000;
	List<Account> findByAcctypeAndBalanceGreaterThan(String type,float amount);
	
	//Exist and counter methods
	boolean existsByAccnm(String name);
	int countByAcctype(String Type);
	
	//Delete methods
	long deleteByAcctype(String type);
	long deleteByBalanceLessThan(float amount);
	
	//custom queries
	@Query("Select a from Account a where a.balance> :bal") //this query return the accounts where greater than balance which provided by function
	List<Account> getRichAccounts(float bal);
	
	@Query("select a.accnm from Account a where a.accno= :no")
	String getAccountName(int no);
	
	
}
