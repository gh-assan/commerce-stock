package com.commerce.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.commerce.stock.valueObject.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonProductSerializer extends JsonSerializer<Product>{

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddKK:mm:ss.SSS zzz");
    
    public void serialize(Product product, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
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