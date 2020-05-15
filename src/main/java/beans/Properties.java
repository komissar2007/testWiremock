package beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

@Data
@PropertySource(value = "app.properties")
public class Properties {

    @Value("${project.host}")
    String projectHost;

    @Value("${project.wiremockhost}")
    String projectWiremockHost;
}
