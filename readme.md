#什么是代理
    增强一个对象的功能
    买火车票，app就是一个代理，他代理了火车站，小区当中的代售窗口
##java当中如何实现代理 java实现的代理的两种办法
###代理的名词
    代理对象    增强后的对象
    目标对象    被增强的对象
    他们不是绝对的，会根据情况发生变化
####静态代理
#####继承
    代理对象继承目标对象，重写需要增强的方法；
    缺点：会代理类过多，非常复杂
#####聚合
    目标对象和代理对象实现同一个接口，代理对象当中要包含目标对象。
    缺点：也会产生类爆炸，只不过比继承少一点点


 >总结:如果在不确定的情况下，尽量不要去使用静态代理。因为一旦你写代码，就会产生类，一旦产生类就爆炸。

