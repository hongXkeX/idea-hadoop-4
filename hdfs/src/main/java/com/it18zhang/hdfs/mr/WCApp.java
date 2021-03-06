package com.it18zhang.hdfs.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author hongXkeX
 * @date 3/16/2018 5:22 PM
 */
public class WCApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 设置job的各种属性
        job.setJobName("WCApp");                         // 作业名称
        job.setJarByClass(WCApp.class);                  // 搜索类
        job.setInputFormatClass(TextInputFormat.class);  // 设置输入格式

        // 添加输入路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WCMapper.class);              // mapper类
        job.setReducerClass(WCReducer.class);            // reducer类

        job.setNumReduceTasks(1);                        // reduce个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.waitForCompletion(false);
    }
}
