package com.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.model.Entry;


public interface EntryRepo extends JpaRepository<Entry, Integer>{

}
