package com.coding.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllEntry {
	
	private Integer count;
	
	private List<Entry> entries;

}
