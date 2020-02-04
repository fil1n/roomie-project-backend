package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.Language;

import java.io.IOException;

public class LanguageSerializer extends StdSerializer<Language> {
    protected LanguageSerializer(Class<Language> t) {
        super(t);
    }

    public LanguageSerializer() {
        this(null);
    }

    @Override
    public void serialize(Language language, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", language.getId());
        jsonGenerator.writeStringField("lang_name", language.getName());
        jsonGenerator.writeStringField("code", language.getCode());
        jsonGenerator.writeEndObject();
    }
}
