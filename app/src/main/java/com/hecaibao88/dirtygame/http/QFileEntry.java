package com.hecaibao88.dirtygame.http;

import java.io.File;

/**
 * 要上传的文件实体
 */
public class QFileEntry {
    public QFileEntry(File file, String name) {
        this.file = file;
        this.name = name;
    }

    /**
     * 要上传的文件
     */
    public File file;

    /**
     *  上传时的文件请求名
     */
    public String name;

}
