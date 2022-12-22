package org.eolang.demu;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.cactoos.io.InputOf;
import org.cactoos.io.OutputTo;
import org.eolang.parser.Syntax;

/**
 * Eo program.
 *
 * @since 0.1.0
 */
final class EoProgram {

    /**
     * The eo program source.
     */
    private final String source;

    /**
     * Ctor.
     * @param src The eo program source
     */
    EoProgram(final String src) {
        this.source = src;
    }

    /**
     * Parse the program to XMIR.
     * @return The XMIR.
     * @throws IOException If fails
     */
    XML parse() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new Syntax("scenario", new InputOf(this.source), new OutputTo(baos)).parse();
        final XML xml = new XMLDocument(baos.toByteArray());
        baos.close();
        return xml;
    }
}
