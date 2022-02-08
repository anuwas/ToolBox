package com.org.pack.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Service
public class ConvertionService {
	
	public void JsonToCsv(File jsonFile) throws IOException {
		File csvFile = new File("output.csv");
		JsonNode jsonTree = new ObjectMapper().readTree(jsonFile);
        
        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
        CsvSchema csvSchema = csvSchemaBuilder
            .build()
            .withHeader();
        
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
            .with(csvSchema)
            .writeValue(csvFile, jsonTree);
	}

}
