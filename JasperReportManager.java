import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author omidp
 *
 */
public class JasperReportManager
{

    private static final String REPORT_PATH = "/report" + File.separator;

    public static File getReportFile(String fileName) throws URISyntaxException
    {
        String path = REPORT_PATH + fileName;
        URL is = Thread.currentThread().getContextClassLoader().getResource(path);
        if (is == null) is = JasperReportManager.class.getClassLoader().getResource(path);
        if (is == null) is = JasperReportManager.class.getResource(path);
        if (is == null) throw new IllegalArgumentException("file not found " + path);
        return Paths.get(is.toURI()).toFile();
    }

    public static void exportPdf(File file, OutputStream os, JasperPaginationHandler paginationHandler) throws JRException
    {
        try
        {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JRPropertiesUtil props = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
            JasperReportsUtils.renderAsPdf(jasperReport, new HashMap<String, Object>(), new JasperPagingDatasource(paginationHandler),
                    os);
        }
        finally
        {
            
        }
    }

    public static void exportExcel(File file, OutputStream os, JasperPaginationHandler paginationHandler) throws JRException
    {
        try
        {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JRPropertiesUtil props = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
            JasperReportsUtils.renderAsXls(jasperReport, new HashMap<String, Object>(), new JasperPagingDatasource(paginationHandler),
                    os);
        }
        finally
        {
            
        }
    }


    public static class JasperPagingDatasource implements JRRewindableDataSource
    {

        private static final int MAX_RESULT = 100;

        private long totalCount;

        private Object currentBean;

        private JasperPaginationHandler paginationHandler;

        private int firstResult;

        private Collection<?> data;

        private Iterator<?> iterator;

        private int currentItemIndex;

        public JasperWebServiceDatasource(JasperPaginationHandler paginationHandler)
        {
            this.paginationHandler = paginationHandler;
            this.totalCount = paginationHandler.getResultCount();
        }

        @Override
        public boolean next() throws JRException
        {
            if ((this.totalCount == 0) || (this.totalCount >= 0 && this.currentItemIndex >= this.totalCount))
            {
                return false;
            }
            if (this.data == null || this.currentItemIndex >= MAX_RESULT)
            {
                Paging p = new Paging(this.firstResult, MAX_RESULT).getPage();
                this.data = paginationHandler.getResultList(p.getFirstResult(), p.getMaxResult());
                if (this.data != null)
                {
                    this.iterator = this.data.iterator();
                }
                // reset
                this.firstResult++;
                this.currentItemIndex = 0;
            }
            if (this.iterator != null)
            {
                boolean hasNext = this.iterator.hasNext();
                if (hasNext)
                {
                    this.currentBean = this.iterator.next();
                    this.currentItemIndex++;
                    return true;
                }
            }
            //
            return false;
        }

        @Override
        public Object getFieldValue(JRField jrField) throws JRException
        {
            String reportFieldName = jrField.getName();
            Object bean = this.currentBean;
            if (bean != null)
            {
                try
                {
                    return PropertyUtils.getProperty(bean, reportFieldName);
                }
                catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                {
                    throw new JRException(e);
                }
               
            }
            return null;
        }

        @Override
        public void moveFirst()
        {
            if (this.data != null)
            {
                this.iterator = this.data.iterator();
            }
        }

    }

    public static class Paging
    {
        private int firstResult;
        private int maxResult;

        public Paging(int firstResult, int maxResult)
        {
            this.firstResult = firstResult;
            this.maxResult = maxResult;
        }

        public Paging getPage()
        {
            int from = ((firstResult) * maxResult);
            int to = (firstResult * maxResult) + maxResult;
            return new Paging(from, to);
        }

        public int getFirstResult()
        {
            return firstResult;
        }

        public int getMaxResult()
        {
            return maxResult;
        }

    }

    public interface JasperPaginationHandler
    {
        public Collection<?> getResultList(Integer firstResult, Integer maxResult);

        public long getResultCount();
    }

}
