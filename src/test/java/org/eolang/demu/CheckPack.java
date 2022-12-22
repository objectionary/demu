package org.eolang.demu;

import com.jcabi.xml.XML;
import com.yegor256.xsline.Shift;
import com.yegor256.xsline.StClasspath;
import com.yegor256.xsline.StEndless;
import com.yegor256.xsline.TrDefault;
import com.yegor256.xsline.Train;
import com.yegor256.xsline.Xsline;
import java.util.Map;
import org.eolang.parser.XMIR;

public class CheckPack {

    /**
     * The scenario in YAML.
     */
    private final Map<String, Object> script;

    /**
     * Ctor.
     *
     * @param scr Yaml script
     */
    CheckPack(final Map<String, Object> scr) {
        this.script = scr;
    }

    /**
     * Make a run check equivality of taken EO sources.
     *
     * @return True if "before" + "xsls" equals "after"
     */
    @SuppressWarnings("unchecked")
    public boolean failures() {
        final XML before = new DeMu(script.get("before").toString()).transform();
        final XML after = RemoveGoto.getParsedXml(script.get("after").toString());
        final Iterable<String> xsls = (Iterable<String>) map.get("xsls");
        Train<Shift> train = new TrDefault<>();
        if (xsls != null) {
            for (final String xsl : xsls) {
                if (xsl.lastIndexOf("flags-to-memory.xsl") == -1
                    && xsl.lastIndexOf("return-value.xsl") == -1) {
                    train = train.with(new StEndless(new StClasspath(xsl)));
                } else {
                    train = train.with(new StClasspath(xsl));
                }
            }
        }
        train = train.with(
            new StEndless(new StClasspath("/org/eolang/dejump/rmv-meaningless.xsl"))
        );
        final Train<Shift> strip = new TrDefault<Shift>()
            .with(new StEndless(new StClasspath("/org/eolang/dejump/strip-xmir.xsl")));
        final XML xml = new Xsline(strip).pass(
            RemoveGoto.getParsedXml(
                new XMIR(
                    new Xsline(train).pass(before)
                ).toEO()
            )
        );
        return xml.equals(new Xsline(strip).pass(after));
    }

}
