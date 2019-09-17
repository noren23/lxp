import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
	public static void main(String[] args) throws IOException { 
        String x1= args[0];
        String x2= args[1]; 
	    String[] s3 = {"上海","北京","天津","重庆"}   ;
	    write("[",x2);
        File f  = new File(x1);  
        FileInputStream in = new FileInputStream(f);  
        // 指定读取文件时以UTF-8的格式读取  
        BufferedReader br1 = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = br1.readLine();  
        while(line != null)  
        {  
        	write("    {",x2);
        	String [] _str1 = line.split("!");
        	if(_str1[0].equals("1"))         	//判断难度
        	{
        	   String [] _str2 = _str1[1].split(",");
        	   name(_str2[0],x2);
               String  s1 = getTelnum(_str2[1]);
               phonenumber(s1,x2);
               String  s2=deletephonenumber(_str2[1],s1);
  			   String  _str3 =deletephonenumber(s2,".");
               String  d=_str3;  
        	   write("        \"地址\"：[",x2);
        	   int count=0;
        	   for(int j=0;j<4;j++)
        	   {
        		   if(s2.contains(s3[j]))
        		   {
       				write("            \""+s3[j]+"\",",x2);
       				write("            \""+s3[j]+"市\",",x2);
       				_str3=s3[j]+"省"+d;
       				List<Map<String,String>> table=new ArrayList<Map<String,String>>();
            	    table=addressResolution1(_str3);
         		    for (Map<String, String> map : table) {
         			Set<String> strings = map.keySet();
         			for (String key : strings) {
         				count++;
         				if(count<=2)
         				{
         				write("            \""+map.get(key)+"\",",x2);
         				}
         				else
         				write("            \""+map.get(key)+"\"",x2);
            			}
            		 }
         		    break;
        		   }
        	   }     
        	   if(count==0)
        	   {
        	     List<Map<String,String>> table=new ArrayList<Map<String,String>>();
       	   	     table=addressResolution(_str3);
    		     for (Map<String, String> map : table) {
    			   Set<String> strings = map.keySet();
    			   for (String key : strings) {
    				  count++;
    				  if(count<=4)
    				    write("            \""+map.get(key)+"\",",x2);
    				  else
    				    write("            \""+map.get(key)+"\"",x2);
       			   }
       		     }
        	   }
       		   write("        ]",x2);
        	}
        	else
        	{
        	   String [] _str2 = _str1[1].split(",");
        	   name(_str2[0],x2);
        	   String  s1 = getTelnum(_str2[1]);
        	   phonenumber(s1,x2);
        	   String  s2=deletephonenumber(_str2[1],s1);
  			   String  _str3 =deletephonenumber(s2,".");
               String  d=_str3;  
        	   write("        \"地址\"：[",x2);
        	   int count=0;
        	   for(int j=0;j<4;j++)
        	   {
        		   if(s2.contains(s3[j]))
        		   {
       				write("            \""+s3[j]+"\",",x2);
       				write("            \""+s3[j]+"市\",",x2);
       				_str3=s3[j]+"省"+d;
       				List<Map<String,String>> table=new ArrayList<Map<String,String>>();
            	    table=addressResolution2(_str3);
         		    for (Map<String, String> map : table) {
         			  Set<String> strings = map.keySet();
         			  for (String key : strings) {
         				count++;
         				if(count<=4)
         				  write("            \""+map.get(key)+"\",",x2);
         				else
         				  write("            \""+map.get(key)+"\"",x2);
            		  }
            		 }
        		   }
        	   }  
        	   if(count==0)
        	   {
        	     List<Map<String,String>> table=new ArrayList<Map<String,String>>();
       	   	      table=addressResolution3(d);
    		     for (Map<String, String> map : table) {
    			    Set<String> strings = map.keySet();
    			    for (String key : strings) {
    				  count++;
    				  if(count<=6)
    				     write("            \""+map.get(key)+"\",",x2);
    				  else
    				     write("            \""+map.get(key)+"\"",x2);
       			    }
       		     }
        	   }
       		   write("        ]",x2);
        	}   
           line = br1.readLine(); 
           if(line!=null)
        	   write("    },",x2);
           else
        	   write("    }",x2); 
        }
        write("]",x2);
        br1.close();
}
public static void write(String content,String s) {  //字符串写入文件
	
		String fileFullPath=s;
	    FileOutputStream fos = null;
	    try {
	        //true不覆盖已有内容
	        fos = new FileOutputStream(fileFullPath, true);  
	        //写入
	        fos.write(content.getBytes());
	        // 写入一个换行
	        fos.write("\r\n".getBytes());
	                   
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        if(fos != null){
	            try {
	                fos.flush();
	                fos.close(); 
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        }
	    }
public static void name(String s1,String s) {  //写入名字
	String a="\"姓名 \"：\"";
	String b="\",";
	write("        "+a+s1+b,s);
}
public static void phonenumber(String s1,String s) {    //写入手机号
	String a="\"手机 \"：\"";
  	String b="\",";
    write("        "+a+s1+b,s);
}
public static String getTelnum(String sParam){    //提取手机号
    if(sParam.length()<=0)
    return "";
    Pattern pattern = Pattern.compile("(1|861)(3|5|8)\\d{9}$*");
    Matcher matcher = pattern.matcher(sParam);
    StringBuffer bf = new StringBuffer();
    while (matcher.find()) {
      bf.append(matcher.group()).append(",");
    }
    int len = bf.length();
    if (len > 0) {
     bf.deleteCharAt(len - 1);
    }
    return bf.toString();
}   
public static String deletephonenumber(String str1,String str2) { //删掉手机号
		StringBuffer sb = new StringBuffer(str1);
		String s1;
		while (true) {
			int index = sb.indexOf(str2);
			if(index == -1) {
				break;
			}
			sb.delete(index, index+str2.length());	
		}
		s1= sb.toString();	
		return s1;
	}
public static List<Map<String,String>> addressResolution(String address){     //五级地址解析
	    String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇|.+街道|.+乡)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("省", province==null?"":province.trim());
            city=m.group("city");
            row.put("市", city==null?"":city.trim());
            county=m.group("county");
            row.put("县", county==null?"":county.trim());
            town=m.group("town");
            row.put("镇", town==null?"":town.trim());
            village=m.group("village");
            row.put("详细", village==null?"":village.trim());
            table.add(row);
        }
        return table;
}
public static List<Map<String,String>> addressResolution1(String address){    //直辖市五级
	 String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+镇|.+局|.+市)?(?<town>[^区]+区|.+镇|.+街道|.+乡)?(?<village>.*)";
       Matcher m=Pattern.compile(regex).matcher(address);
		String county=null,town=null,village=null;
       List<Map<String,String>> table=new ArrayList<Map<String,String>>();
       Map<String,String> row=null;
       while(m.find()){
           row=new LinkedHashMap<String,String>();
           county=m.group("county");
           row.put("county", county==null?"":county.trim());
           town=m.group("town");
           row.put("town", town==null?"":town.trim());
           village=m.group("village");
           row.put("village", village==null?"":village.trim());
           table.add(row);
       }
       return table;
}
public static List<Map<String,String>> addressResolution3(String address){   //七级地址解析
    String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+局|.+市)(?<town>[^区]+区|.+镇|.+街道|.+乡)?(?<road>[^路]+路|.+弄|.+街|.+巷)?(?<number>[^号]+号)?(?<village>.*)";
    Matcher m=Pattern.compile(regex).matcher(address);
	String province=null,city=null,county=null,town=null,village=null,road=null,number=null;
    List<Map<String,String>> table=new ArrayList<Map<String,String>>();
    Map<String,String> row=null;
    while(m.find()){
        row=new LinkedHashMap<String,String>();
        province=m.group("province");
        row.put("province", province==null?"":province.trim());
        city=m.group("city");
        row.put("city", city==null?"":city.trim());
        county=m.group("county");
        row.put("county", county==null?"":county.trim());
        town=m.group("town");
        row.put("town", town==null?"":town.trim());
        road=m.group("road");
        row.put("road", road==null?"":road.trim());
        number=m.group("number");
        row.put("number", number==null?"":number.trim());
        village=m.group("village");
        row.put("village", village==null?"":village.trim());
        table.add(row);
    }
    return table;
}
public static List<Map<String,String>> addressResolution2(String address){    //直辖市七级
	String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+局|.+市)(?<town>[^区]+区|.+镇|.+街道|.+乡)?(?<road>[^路]+路|.+弄|.+街|.+巷)?(?<number>[^号]+号)?(?<village>.*)";
      Matcher m=Pattern.compile(regex).matcher(address);
  	  String  county=null,town=null,village=null,road=null,number=null;
      List<Map<String,String>> table=new ArrayList<Map<String,String>>();
      Map<String,String> row=null;
      while(m.find()){
          row=new LinkedHashMap<String,String>();
          county=m.group("county");
          row.put("county", county==null?"":county.trim());
          town=m.group("town");
          row.put("town", town==null?"":town.trim());
          road=m.group("road");
          row.put("road", road==null?"":road.trim());
          number=m.group("number");
          row.put("number", number==null?"":number.trim());
          village=m.group("village");
          row.put("village", village==null?"":village.trim());
          table.add(row);
      }
      return table;
}
}
