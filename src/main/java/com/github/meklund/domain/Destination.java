package com.github.meklund.domain;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * A bean that extracts values from the incoming header.
 * <p/>
 * Uses <tt>@Component("myBean")</tt> to register this bean with the name <tt>myBean</tt>
 * that we use in the Camel route to lookup this bean.
 */
@Component
public class Destination {

    protected static final Logger logger = LoggerFactory.getLogger(Destination.class);
    public static final String HTTP_SCHEME = "http_x_forwarded_proto";
    public static final String HTTP_PORT = "http_x_forwarded_port";
    public static final String HTTP_PATH = "path";
    public static final String HOST = "http_x_forwarded_host";

    public String getDestination(Map<String, String> headers) {
        String backendDestination = "";

        try {
            logger.info("Extracting backend");
            String scheme = headers.get(HTTP_SCHEME) != null ? headers.get(HTTP_SCHEME) : "https";
            String port = headers.get(HTTP_PORT) != null ? headers.get(HTTP_PORT) : "443";
            String host = headers.get(HOST) != null ? headers.get(HOST) : "cat-fact.herokuapp.com";
            String path = headers.get(HTTP_PATH) != null ? headers.get(HTTP_PATH) : "facts/random";
            backendDestination =  scheme + "://" + host + ":" + port + "/" + path;
            logger.info("Backend destination: " + backendDestination);

        } catch(Exception e) {
            logger.error("Error when extracting backend-service url",e);
        }
        return backendDestination;
    }
}

