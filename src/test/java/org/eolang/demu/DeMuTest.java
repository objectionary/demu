package org.eolang.demu;

import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

final class DeMuTest {

    @Disabled
    @Test
    void convertsEoProgramToEoProgramWitoutCage() throws Exception {
        MatcherAssert.assertThat(
            new DeMu().apply(new TextOf(new ResourceOf("eo/cage_simple_optimized.xmir")).asString()),
            Matchers.equalTo(new TextOf(new ResourceOf("eo/without_simple_optimized.xmir")).asString())
        );
    }

}