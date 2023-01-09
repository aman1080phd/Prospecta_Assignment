package com.coding.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coding.exception.EntryException;
import com.coding.model.AllEntry;
import com.coding.model.Entry;
import com.coding.model.EntryDTO;
import com.coding.service.EntryService;

@RestController
public class EntryController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EntryService eService;
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDTO>> getEntryByCategory(@PathVariable("category") String category)
	{
		
		AllEntry allEntries =  restTemplate.getForObject("https://api.publicapis.org/entries", AllEntry.class);
		
		List<Entry> list = allEntries.getEntries();
		
		List<EntryDTO> listByCategory = new ArrayList<>();
		
		listByCategory = list.stream()
							 .filter(e->e.getCategory().equals(category))
							 .map(e-> new EntryDTO(e.getApi(), e.getDescription()))
							 .collect(Collectors.toList());
		
		return new ResponseEntity<List<EntryDTO>>(listByCategory, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/entry")
	public ResponseEntity<String> saveEntry(@RequestBody Entry entry) throws EntryException {
		
		AllEntry allEntries = restTemplate.getForObject("https://api.publicapis.org/entries", AllEntry.class);
		
		List<Entry> entries = allEntries.getEntries();
		
		entries.add(entry);
		
		eService.saveEntry(entry);
		
		return new ResponseEntity<String>("Entry saved successfully!!", HttpStatus.CREATED);
		
	}

}
