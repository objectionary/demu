package org.eolang.demu;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.yaml.snakeyaml.Yaml;

public class PacksTest {

    @ParameterizedTest
    @MethodSource("packs")
    public void transformsPack(final Map<String, Object> pack) throws Exception {
        MatcherAssert.assertThat(
            new CheckPack(pack).failures(),
            Matchers.is(true)
        );
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private static Collection<Map<String, Object>> packs() {
        return PacksTest.yamls();
    }

    private static Collection<Map<String, Object>> yamls() {
        return Arrays.stream(
                new UncheckedText(
                    new TextOf(new ResourceOf("org/eolang/demu/packs/")
                    )
                ).asString().split("\n"))
            .filter(path -> path.endsWith(".yaml"))
            .map(PacksTest::yaml)
            .collect(Collectors.toList());
    }

    private static Map<String, Object> yaml(final String path) {
        return new Yaml().load(
            new UncheckedText(
                new TextOf(
                    new ResourceOf(
                        String.format("org/eolang/demu/packs/%s", path)
                    )
                )
            ).asString()
        );
    }
}