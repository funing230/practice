package test.funing;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**   
 * CSV操作(导出和导入)
 *
 */
public class CSVUtils {
    
    /**
     * 导出
     * 
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out,"GBK");
//            osw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
        	e.printStackTrace();
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file){
        return importCsv(file,true);
    }
    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @param isHaveHeader 是否含有表头
     * @return
     */
    public static List<String> importCsv(File file,Boolean isHaveHeader){
        List<String> dataList=new ArrayList<String>();
        BufferedReader in = null;
        try {
            String code = codeString(file);
            FileInputStream fInputStream = new FileInputStream(file);
            //code为上面方法里返回的编码方式
            in = new BufferedReader(new InputStreamReader(fInputStream, code));
            String strTmp = "";
            //按行读取
            if(isHaveHeader){
                in.readLine();
            }
            while (( strTmp = in.readLine()) != null) {
                dataList.add(strTmp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }
    public static void main(String[] args) {
    	List<String> dataList=CSVUtils.importCsv(new File("C:\\Users\\sun\\Desktop\\mingzhong\\百融（你我金融）0506M8.csv"));
    	while(true){
    		
    	}
    }
    /** 
     * 判断文件的编码格式 
     * @param fileName :file 
     * @return 文件编码格式 
     * @throws Exception 
     */  
    public static String codeString(File file) throws Exception{  
        String code = null;
        BufferedInputStream bin = null ;
		try {
			bin = new BufferedInputStream(new FileInputStream(file));  
			int p = (bin.read() << 8) + bin.read();  
			code = null;  
			  
			switch (p) {  
			    case 0xefbb:  
			        code = "UTF-8";  
			        break;  
			    case 0xfffe:  
			        code = "Unicode";  
			        break;  
			    case 0xfeff:  
			        code = "UTF-16BE";  
			        break;  
			    default:  
			        code = "GBK";  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bin!=null){
					bin.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}  
          
        return code;  
    } 
}