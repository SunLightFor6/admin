package com.lamport.admin.fileupload;

import java.io.Serializable;

public interface FileManagerConfig extends Serializable {

    public static final String FILE_DEFAULT_AUTHOR = "Lamport";

    public static final String PROTOCOL = "http://";

    public static final String SEPARATOR = "/";

//    public static final String TRACKER_NGNIX_ADDR = "172.16.100.11";
    public static final String TRACKER_NGNIX_ADDR = "192.168.252.3";

    public static final String TRACKER_NGNIX_PORT = "";

    public static final String CLIENT_CONFIG_FILE = "/etc/fdfs/client.conf";
}
