package com.huazai.classloader;

public class MyCat {
    public MyCat() {
        System.out.println("MyCat" + MyCat.class.getClassLoader());
        /*
            删除classpath的MySample的class文件后去掉该行注释，并在classpath保留MyCat的class文件，变为Mycat由系统加载器加载，
            MySample由自定义加载器加载（父加载器为系统类加载）

            系统类加载器加载MyCat类文件后，由于父加载器对子加载器不可见，系统加载器尝试加载MySample的class文件的时候找不到MySample的class文件，报错
         */
//        System.out.println("MySample+" + MySample.class.getClassLoader());
    }
}
