package com.demo.java_web.chapter003;

import org.apache.catalina.connector.CoyoteAdapter;
import org.apache.coyote.http11.InternalInputBuffer;

import java.io.IOException;

public class ResolveUrl {
    public static void main(String[] args) throws IOException {
        InternalInputBuffer demo = new InternalInputBuffer(null,0);
        demo.parseRequestLine(false);

        CoyoteAdapter adapter = new CoyoteAdapter(null);

    }
}
