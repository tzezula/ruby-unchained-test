package org.me;

import org.graalvm.polyglot.*;

import java.util.Map;

public class Main {

    private static final String SOURCE_RB = """
    "Hello".force_encoding("ASCII-8BIT")
    """;

    public static void main(String[] args) throws Exception {
        testRuby(Map.of());
    }

    private static void testRuby(Map<String,String> options) {
        Context.Builder builder = Context.newBuilder();
        builder.options(options);
        try (Context ctx = builder.build()) {
            Value res = ctx.eval("ruby", SOURCE_RB);
            assert res.isString();
            System.out.println(res.asString());
        }
    }
}
