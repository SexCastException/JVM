/**
 * 堆初始大小：-Xms
 * 堆最大大小：-Xmx 或 -XX:MaxHeapSize=size
 * 新生代大小：-Xmn 或 (-XX:NewSize=size + -XX:MaxNewSize=size )
 * 幸存区比例：（动态） -XX:InitialSurvivorRatio=ratio 和 -XX:+UseAdaptiveSizePolicy
 * 幸存区比例：-XX:SurvivorRatio=ratio
 * 晋升老年代阈值 -XX:MaxTenuringThreshold=threshold
 * 晋升老年代详情 -XX:+PrintTenuringDistribution
 * 打印GC详情 -XX:+PrintGCDetails -verbose:gc
 * FullGC 前 MinorGC -XX:+ScavengeBeforeFullGC
 */
package com.huazai.itcast.t2.gc;
