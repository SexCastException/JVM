package com.huazai.bytecode;

/**
 * 1.使用命令：javap -verbose命令分析一个字节码文件，将会分析该字节码文件的魔数、版本号、常量池、类信息、类的构造方法、
 *  类中的方法信息、类变量与成员变量等信息。附加：添加参数 -p 可以查看字符查看类的私有方法和私有字段。
 *
 * 2.所有的.class字节码文件的前四个都是魔数。魔数为十六进制的固定值，OxCAFEBABE，翻译成宝贝咖啡。
 *
 * 3.魔数之后的4个字节为版本信息，前两个字节表示minor version（次版本号），后两个字节表示major version（主版本号）。
 *  比如：00 00 00 34，换算成十进制，表示次版本号为0，主版本号为52.对应jdk版本为1.8.0（1.8为主版本号，0为次版本号）。可以通过命令java -version表示
 *
 * 4.常量池（constant pool）：紧接着主版本号之后的就是常量池入口。一个java类中定义的很多信息都是由常量池来维护和描述的，
 *  可以将常量池看做是Class文件的资源仓库，比如说java类中定义的方法与变量信息，都是存储在常量池中。
 *  常量池主要存储两类常量：字面量与符号引用，字面量如文本字符串，java中声明为final的常量值等，而符号引用如类和接口的全局限定名，
 *  字段的名称和描述符，方法的名称和描述符等。
 *
 * 5.常量池的总体结构：java类中所对应的常量池主要由常量池数量和常量池数组（常量表）这两部分构成。常量池数量紧跟在主版本号之后，占据两个字节；
 *  常量池数组紧跟在常量池数量之后。常量池数组与一般的数组不同的是，常量池数组中不同的元素类型、结构都是不同的，长度当然也就不同；
 *  但是，每一种元素的第一个数据都是u1类型，该字节是个标志位，占据一个字节。JVM在解析常量池时，会根据这个u1类型来获取元素的具体类型。
 *  值得注意的是，常量池数组中元素的个数 = 常量池数 - 1（其中0暂时不使用），目的是满足某些常量池索引值的数据在特定情况下需要表达
 *  【不引用任何一个常量池】的含义：根本原因在于，索引为0也是一个常量（保留常量），只不过他不位于常量表中，这个常量就是null值。
 *  所以常量从1而非0开始。
 *
 * 6.在JVM规范中，每个变量/字段都有描述信息，描述信息主要的作用描述字段的数据类型，方法的参数列表（包括数量、类型与顺序）与返回值。
 *  根据描述规则，基本数据类型和代表无返回值的void乐行都用一个大写字符来表示，对象类型则使用字符L加对象的权限定名称类型来表示。
 *  为了压缩字节码文件的体积。对于基本数据类型，JVM都只使用一个大写字母来表示，如下所示：B - byte，C - char，D - double，F - float，
 *  I - int，J - long，S - short，Z - boolean，V - void，L - 对象类型，如：Ljava/lang/String;
 *
 * 7.对于数组类型来说，每一个维度使用一个前置的“[”来表示，如int[]被记录为[I，String[][]被记录为[[Ljava/lang/String；
 *
 * 8.用描述符描述方法时，按照先参数列表，后返回值的顺序来描述。参数列表按照参数的严格顺序放在一组()之内。
 *  如方法：String getXXX(int id,String name)的描述符为：(I,Ljava/lang/String;) L/java/lang/String;
 *
 *
 * 总结：.class文件结构如下：
 * 魔数（magic number，4个字节）-》次版本号（minor_version，2个字节）-》主版本号（major_version，2个字节）-》常量池数量（constant_pool_count，2个字节）-》
 * 常量池数组表（cp_info，n个字节）-》类的访问控制权限（access_flags，2个字节）-》当前类完全限定名(this_class，2个字节）-》
 * 父类完全限定名(super_class，2个字节）-》接口个数（interfaces_count，2个字节）-》接口名（interfaces，n个字节）-》字段个数（fields_count，2个字节）-》
 * 字段表（field_info，n个字节）-》方法个数（methods_count，2个字节）-》方法表（method_info，n个字节，注：attribute_info的attribute_length表示attribute所包含的字节数，不包含code的attribute_name_index和attribute_length字段）-》
 * 附加属性的个数（attributes_count，2个字节)-》附加属性表（attribute_info，n个字节）
 */
public class Test1 {
    private int a;  // 00 02
    int b;  // 00 00
    protected int c;    // 00 04
    public int d;   // 00 01

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
