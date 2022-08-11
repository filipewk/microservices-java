package br.com.microservices.salesapi.modules.converter;

import br.com.microservices.salesapi.modules.product.dto.ProductDTO;
import com.google.gson.Gson;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Converter
public class JsonConverter implements AttributeConverter<List<ProductDTO>, String> {

    private final Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(List<ProductDTO> attribute) {
        return GSON.toJson(attribute);
    }

    @Override
    public List<ProductDTO> convertToEntityAttribute(String dbData) {
        Type listOfMyClassObject = new TypeToken<ArrayList<ProductDTO>>() {}.getType();
        return GSON.fromJson(dbData, listOfMyClassObject);
    }
}
