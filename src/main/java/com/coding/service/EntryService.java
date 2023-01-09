package com.coding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exception.EntryException;
import com.coding.model.Entry;
import com.coding.repository.EntryRepo;


@Service
public class EntryService {

	@Autowired
	EntryRepo entryRepo;
	
	public void saveEntry(Entry entry) throws EntryException{
		
		if(entry==null)
		{
			throw new EntryException("Invalid entry!!");
		}
		
		entryRepo.save(entry);
	}
}
