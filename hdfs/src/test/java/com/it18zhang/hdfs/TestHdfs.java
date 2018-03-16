package com.it18zhang.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author hongXkeX
 * @date 3/10/2018 1:40 PM
 */
public class TestHdfs {

    @Test
    public void testRead() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("hdfs://s200/user/centos/hadoop/hello.txt");
        FSDataInputStream fis = fs.open(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copyBytes(fis, baos, 1024);
        fis.close();
        System.out.println(new String(baos.toByteArray()));
    }

    @Test
    public void testWrite() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/centos/a.txt");
        FSDataOutputStream fout = fs.create(path);
        fout.write("how are you?".getBytes());
        fout.close();
    }
}
