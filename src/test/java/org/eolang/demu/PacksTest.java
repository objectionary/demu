package org.eolang.demu;

import java.io.IOException;
import java.util.Map;
import org.eolang.jucs.ClasspathSource;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.yaml.snakeyaml.Yaml;

class PacksTest {

    @ParameterizedTest
    @ClasspathSource(value = "org/eolang/demu/packs/", glob = "**.yaml")
    void transformsPack(final String pack) throws IOException {
        final Map<String, Object> script = new Yaml().load(pack);
        MatcherAssert.assertThat(
            new DeMu(
                new EoProgram(script.get("before").toString()).parse()
            ).transform(),
            Matchers.equalTo(
                new EoProgram(script.get("after").toString()).parse()
            )
        );
    }

}