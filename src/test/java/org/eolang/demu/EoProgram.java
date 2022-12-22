/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Objectionary
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
