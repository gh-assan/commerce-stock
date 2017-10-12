package com.commerce.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;

import com.commerce.stock.util.date.DateFormatter;
import com.commerce.stock.valueObject.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonProductSerializer extends JsonSerializer<Product>{
    
    @Autowired
    DateFormatter formatter;
    
    public void serialize(Product product, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
    	
    	//dateFormat.setTimeZone(TimeZone.getTimeZone("UCT"));
    	
        gen.writeStartObject();
        gen.writeStringField("productId", product.getStock().getProductId());
        gen.writeStringField("requestTimestamp", formatter.format(product.getRequestTimestamp() ));
        
        gen.writeObjectFieldStart("stock");
        	gen.writeStringField("id", product.getStock().getId());
        	gen.writeStringField("timestamp", formatter.format(product.getStock().getTimestamp() ));
        	gen.writeNumberField("quantity", product.getStock().getQuantity());
        gen.writeEndObject();
        
        
        gen.writeEndObject();
    }


}