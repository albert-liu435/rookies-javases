JVM 内存模型

JVM概念：JVM是可运行java代码的假象计算机，包括一套字节码指令集，一组寄存器，一个栈，一个垃圾回收，堆和存储方法区。JVM是运行在操作系统之上的，它与硬件没有直接的交互。

java程序在JVM上的运行过程

java源文件--》编译器--》字节码文件--》JVM--》机器码

java源文件通过编译器产生相应的字节码文件，即(.Class文件)，字节码文件通过java虚拟机的解释器变异成特定机器上的机器码。

![JVM内存](.\pic\JVM内存.jpg)

##### 程序计数器

是一款较小的内存空间，可以是当前线程所执行的字节码的行号指示器，每条线程都有一个独立的程序计数器，各条线程之间计数器互不影响，独立存储，所以这类内存也成为线程私有内存。
如果执行的是一个java方法，程序计数器记录的是正在执行的虚拟机字节码指令的地址，如果执行的是本地(Native)方法，程序计数器的值则应为空(Undefined).

##### java虚拟机栈

java虚拟机栈也是线程私有的，它的生命周期与线程相同，虚拟机栈描述的是java方法执行的线程内存模型，每个方法被执行的时候，java虚拟机都会同步创建一个栈帧用于存储局部变量表、操作数栈、动态连接、方法出口等信息。每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。
本地方法栈：本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别只是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的本地（Native）方法服。

##### java堆

Java堆（Java Heap）是虚拟机所管理的内存中最大的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，Java世界里“几乎”所有的对象实例都在这里分配内存

##### 方法区

方法区（Method Area）与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载
的类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据。

##### 运行时常量池

运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字
段、方法、接口等描述信息外，还有一项信息是常量池表（Constant Pool Table），用于存放编译期生成的各种字面量与符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中

垃圾收集算法

引用计数算法：在Java 中，引用和对象是有关联的。如果要操作对象则必须用引用进行。简单说就是在对象中添加一个引用计数器，每当有一个地方引用它时，计数器值就加一；当引用失效时，计数器值就减一；任何时刻计数器为零的对象就是不可。这种算法效率较高，但是难以解决循环 引用的问题。
能再被使用的

可达性分析算法：java采用的是这种算法，基本思路就是通过一系列称为“GC Roots”的根对象作为起始节点集，从这些节点开始，根据引用关系向下搜索，搜索过程所走过的路径称为“引用链”（Reference Chain），如果某个对象到GC Roots间没有任何引用链相连，或者用图论的话来说就是从GC Roots到这个对象不可达时，则证明此对象是不可能再被使用的。不可达对象变为可回收对象至少要经过两次标记过程，两次标记后仍然是可回收对象，则将面临回收。

如图：对象object 5、object 6、object 7虽然互有关联，但是它们到GC Roots是不可达的，因此它们将会被判定为可回收的对象

![可达分析](.\pic\可达分析.jpg)