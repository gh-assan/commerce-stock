package com.commerce.stock.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.commerce.stock.util.date.DateFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>{

	@Autowired
    DateFormatter formatter;
    
	
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
    	
        String formattedDate = formatter.format(date);
        gen.writeString(formattedDate);
        
        System.out.println(formattedDate);
    }
}