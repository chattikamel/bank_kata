package com.katabank.steps;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.util.Locale.FRENCH;

public class ParameterTypes implements TypeRegistryConfigurer {

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<Date>(
                "iso-date",
                "\\d{2}-\\d{2}-\\d{4}",
                Date.class,
                (String s) -> new SimpleDateFormat("dd-mm-yyyy").parse(s)
        ));

       /* typeRegistry.setDefaultDataTableCellTransformer(new TableCellByTypeTransformer() {
            @Override
            public  Date transform(String s, Class<Date> aClass) throws Throwable {
                return null;
            }
        });*/
    }

    @Override
    public Locale locale() {
        return FRENCH;
    }
}