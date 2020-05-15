package component.examplemodule;

import beans.Properties;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.TestUtils;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = config.Config.class)
public class ExampleBeanTest extends TestUtils {

    @Autowired
    Properties properties;

    @BeforeClass
    public static void beforeClass() {
        TestUtils.initWiremock();

    }

    @AfterClass
    public static void afterClass() {

        TestUtils.stopWiremock();

    }

    @Test
    public void test_mock_200() throws InterruptedException {
        TestUtils.initMockMapping("test_GET");
        System.out.println(properties.getProjectHost());
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://" + properties.getProjectHost() + "/api/v1/employees";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response.toString());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void test_mock_200_2() throws InterruptedException {
        TestUtils.initMockMapping("test_GET_2");
        System.out.println(properties.getProjectHost());
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://" + properties.getProjectHost() + "/api/v1/employees";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response.toString());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test(expected = HttpClientErrorException.class)
    public void test_mock_200_negative() {
        TestUtils.initMockMapping("test_GET");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://" + properties.getProjectWiremockHost() + "/api/v1/employe";
        System.out.println(fooResourceUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response.toString());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

    @Test
    public void test_mock_status_200_POST() {
        TestUtils.initMockMapping("test_POST");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://" + properties.getProjectWiremockHost() + "api/v1/create";
        System.out.println(fooResourceUrl);
        ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, String.class, null);
        System.out.println(response.toString());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }
}
