package com.comcast.pop.http.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

public class URLRequestPerformer
{
    private static Logger logger = LoggerFactory.getLogger(URLRequestPerformer.class);

    /**
     * Makes a request to the specified HttpURLConnection
     * @param connection The connection to operate with
     * @param data The data to write in the POST of the request (may be null)
     * @return The result of the request
     * @throws IOException Thrown if there are any errors while performing the request
     */
    @Deprecated
    public String performURLRequest(HttpURLConnection connection, byte[] data) throws IOException
    {
        // only write data if there is any
        if(data != null)
        {
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            output.write(data);
            output.close();
        }

        logger.info("performURLRequest: {} {}", connection.getRequestMethod(), connection.getURL());

        InputStream is = connection.getInputStream();

        String result = convert(is, StandardCharsets.UTF_8);
        is.close();
        return result;
    }

    /**
     * Makes a request to the specified HttpURLConnection
     * @param connection The connection to operate with
     * @param data The data to write in the POST of the request (may be null)
     * @return The result of the request (wrapped)
     * @throws IOException Thrown if there are any errors while performing the request, other than a bad status code
     */
    public URLResponse performRequest(HttpURLConnection connection, byte[] data) throws IOException
    {
        // only write data if there is any
        if(data != null)
        {
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            output.write(data);
            output.close();
        }

        logger.info("performRequest: {} {}", connection.getRequestMethod(), connection.getURL());

        URLResponse response = new URLResponse();

        InputStream is;
        try
        {
            is = connection.getInputStream();
        }
        catch(Exception ex)
        {
            is = connection.getErrorStream();
            response.setError(true);
            response.setException(ex);
        }
        response.setStatusCode(connection.getResponseCode());
        response.setResponseBody(convert(is, StandardCharsets.UTF_8));
        response.setHeaders(connection.getHeaderFields());
        is.close();
        return response;
    }

    /**
     * Converts an input stream to a String based on the specified charset
     * @param inputStream The input stream to read from
     * @param charset The charset to read using
     * @return The converted String
     * @throws IOException
     */
    protected String convert(InputStream inputStream, Charset charset) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
