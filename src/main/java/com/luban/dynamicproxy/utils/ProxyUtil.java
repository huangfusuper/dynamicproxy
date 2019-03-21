package com.luban.dynamicproxy.utils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author 皇甫
 */
public class ProxyUtil {
    public static Object newInstance(Object o){
        Object proxyObject = null;
        Class targetInf = o.getClass().getInterfaces()[0];
        String line  = "\n";
        String tab = "\t";
        String content = "";
        Method[] methods =  targetInf.getDeclaredMethods();
        String clazzName = targetInf.getSimpleName();
        String packageContext = "package com.huangfu.proxy;"+line;
        String importContext = "import "+targetInf.getName()+";"+line;
        String clazzFirstLineContext = "public class $Proxy implements "+clazzName+" {"+line;
        String filedContext = tab+"private "+clazzName+" clazzName;"+ line;
        String constructorContext = tab+"public $Proxy("+clazzName+" clazzName) {"+line
                                    +tab+tab+"this.clazzName = clazzName;"+line+tab+"}"+line;
        //方法体内容
        String methodContext = "";
        for (Method method : methods) {
            //返回值类型名称 比如 String
            String returnTypeName = method.getReturnType().getSimpleName();
            //方法名
            String methodName = method.getName();
            //参数类型数组
            Class[] args = method.getParameterTypes();
            //获取形参列表
            String argsContent = "";
            //获取参数
            String paramsContent = "";
            for (int i = 0; i < args.length; i++) {
                //参数类型名称
                String argsSimpleName = args[i].getSimpleName();
                argsContent+=argsSimpleName+" p"+i+",";
                paramsContent+="p"+i+",";

            }
            //判断是否有参数
            if(argsContent.length()>0){
                argsContent.substring(0, argsContent.lastIndexOf(",")-1);
                paramsContent.substring(0, paramsContent.lastIndexOf(",")-1);
            }

            methodContext+=tab+"public "+returnTypeName+" "+methodName+"("+argsContent+") {"+line
                           +tab+tab+"System.out.println(\"-----------log-------------\");"+line;
            if(!returnTypeName.equals("void")){
                methodContext+=tab+tab+"return clazzName."+methodName+"("+paramsContent+");"+line;
            }else{
                methodContext+=tab+tab+"clazzName."+methodName+"("+paramsContent+");"+line;
            }
            methodContext+=tab+"}"+line;
        }
        methodContext+="}";
        content = packageContext+importContext+clazzFirstLineContext+filedContext+constructorContext+methodContext;
        String path = "c:/com/huangfu/proxy";
        FileWriter fw = null;
        File file = new File(path);
        //判断文件夹是否存在，不存在就创建
        if  (!file .exists()  && !file .isDirectory()){
            file .mkdirs();
        }
        File javaFile = new File(path+"/$Proxy.java");
        //判断文件是否存在，不存在就创建
        try {
            if(!javaFile.exists()){
                javaFile.createNewFile();
            }
            fw = new FileWriter(javaFile);
            fw.write(content);
            fw.flush();
            //创建Class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(javaFile);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class<?> clazz = urlClassLoader.loadClass("com.huangfu.proxy.$Proxy");
            //获取构造方法
            Constructor<?> constructor = clazz.getConstructor(targetInf);
            proxyObject = constructor.newInstance(o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return proxyObject;

    }
}
