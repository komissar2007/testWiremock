package utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import com.github.tomakehurst.wiremock.standalone.MappingsLoader;
import com.github.tomakehurst.wiremock.standalone.MappingsSource;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TestUtils {
    static WireMockServer wireMockServer = new WireMockServer(options().port(8089));

    public static void initWiremock() {
        wireMockServer = new WireMockServer(options().port(8089));
        System.out.println("wiremock start");
        wireMockServer.start();

    }

    public static void stopWiremock() {
        wireMockServer.stop();
        System.out.println("wiremock stop");

    }

    public static void initMockMapping(String stubSourceName) {
        wireMockServer.resetMappings();
        wireMockServer.loadMappingsUsing(new JsonFileMappingsSource(new SingleRootFileSource("src/test/resources/stubs/" + stubSourceName + "/")));
        System.out.println(wireMockServer.port());
    }


}
