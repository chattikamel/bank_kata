package steps;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "iso-date",
                "\\d{4}-\\d{2}-\\d{2}",
                Date.class,
                (String s) -> new SimpleDateFormat("yyyy-mm-dd").parse(s)
        ));
    }

    @Override
    public Locale locale() {
        return ENGLISH;
    }
}