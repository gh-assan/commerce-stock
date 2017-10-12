package com.commerce.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.commerce.stock.valueObject.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonProductSerializer extends JsonSerializer<Product>{

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    
    public void serialize(Product product, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
    	
    	dateFormat.setTimeZone(TimeZone.getTimeZone("UCT"));
    	
        gen.writeStartObject();
        gen.writeStringField("productId", product.getStock().getProductId());
        gen.writeStringField("requestTimestamp", dateFormat.format(product.getRequestTimestamp() ));
        
        gen.writeObjectFieldStart("stock");
        	gen.writeStringField("id", product.getStock().getId());
        	gen.writeStringField("timestamp", dateFormat.format(product.getStock().getTimestamp() ));
        	gen.writeNumberField("quantity", product.getStock().getQuantity());
        gen.writeEndObject();
        
        
        gen.writeEndObject();
    }


}