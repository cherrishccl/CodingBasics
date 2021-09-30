package com.boot.basics.coding.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2021/9/30 9:40
 * @Version 1.0
 * @Description LotteryTest
 */
@Slf4j
public class LotteryTest {
    private static String tempDayEnd = "2021-09-30";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws IOException, ParseException {
       // getResult2Txt();
        analysisResult();

        analysisResult();
    }

    private static Map<String, List<String>> map = new HashMap<>();

    private static void analysisResult(){
        List<String> list = readLines("D:\\lottery.txt");
        analysisResult(list, true);
        System.out.println("22222222222222222222222222222");
        analysisResult(map.get("(二)"), false);
        System.out.println("44444444444444444444444444444");
        analysisResult(map.get("(四)"), false);
        System.out.println("77777777777777777777777777777");
        analysisResult(map.get("(日)"), false);
    }

    private static void analysisResult(List<String> list, boolean m){
        int[] reds = new int[33];
        int[] blues = new int[16];
        for(String resStr : list){
            if(null != resStr){
                String[] res = resStr.split("\t");
                if(m){
                    String str = res[0];
                    String key;
                    if(str.contains("(二)")){
                        key = "(二)";
                    }else if(str.contains("(四)")){
                        key = "(四)";
                    }else if(str.contains("(日)")){
                        key = "(日)";
                    }else {
                        return;
                    }
                    List<String> strList = map.get(key);
                    if(null == strList){
                        strList = new ArrayList<>();
                    }
                    strList.add(resStr);
                    map.put(key, strList);
                }

                String red = res[2];
                String[] redstr = red.split(",");
                for(String s : redstr){
                    int n = Integer.parseInt(s);
                    reds[n - 1] = reds[n - 1] + 1;

                }
                String blue = res[3];
                int n = Integer.parseInt(blue);
                blues[n - 1] = blues[n - 1] + 1;

            }
        }
        System.out.println(Arrays.toString(reds));
        System.out.println(Arrays.toString(blues));

        List<BallCount> redList = new ArrayList<>(33);
        for(int i = 0; i < reds.length; i++){
            redList.add(new BallCount(i + 1, reds[i]));
        }
        redList.sort(new Comparator<BallCount>() {
            @Override
            public int compare(BallCount o1, BallCount o2) {
                return o1.count - o2.count;
            }
        });
        System.out.println(redList);
        List<BallCount> blueList = new ArrayList<>();
        for(int i = 0; i < blues.length; i++){
            blueList.add(new BallCount(i + 1, blues[i]));
        }
        blueList.sort(new Comparator<BallCount>() {
            @Override
            public int compare(BallCount o1, BallCount o2) {
                return o1.count - o2.count;
            }
        });
        System.out.println(blueList);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @lombok.Data
    private static class BallCount{
        private Integer num;
        private Integer count;
    }

    private static void getResult2Txt() throws ParseException, IOException {
        String dayEnd = "2021-09-30";
        int count = 0;
        while (count == 0 || !tempDayEnd.equals(dayEnd)){
            dayEnd = calDate(tempDayEnd, 1);
            System.out.println(dayEnd);
            if("2012-12-31".equals(dayEnd)){
                break;
            }
            getResult(dayEnd);
            count++;
        }
//        System.out.println(result);
        //System.out.println(calDate(tempDayEnd, 2));
    }

    private static void getResult(String dayEnd) throws IOException {
        String baseUrl = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice";
        String paramStr = "?name=ssq&issueCount=100000&issueStart=&issueEnd=&dayStart=&dayEnd=";
        paramStr = "?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart=2000-01-01&dayEnd=";

        String url = baseUrl.concat(paramStr).concat(dayEnd);
        String result = HttpClientUtils.get(url, null);
        JSONObject resultJsonObj = JSONObject.parseObject(result);
        Set<String> codeSet = new HashSet<>();
        if(null != resultJsonObj){
            JSONArray resultJsonArray = resultJsonObj.getJSONArray("result");
            if(null != resultJsonArray && resultJsonArray.size() > 0){
                for(int i = 0; i < resultJsonArray.size(); i++){
                    JSONObject itemJsonObj = resultJsonArray.getJSONObject(i);
                    if(null != itemJsonObj){
                        String code = itemJsonObj.getString("code");
                        if(codeSet.contains(code)){
                            System.err.println("已经包含该期: " + code);
                            continue;
                        }
                        String date = itemJsonObj.getString("date");
                        String red = itemJsonObj.getString("red");
                        String blue = itemJsonObj.getString("blue");

                        String resultStr = date + "\t" + code + "\t" + red + "\t" + blue + "\n";
                        //System.out.print(resultStr);
                        write("D:\\lottery.txt", resultStr, true);
                        if(i == resultJsonArray.size() - 1){
                            tempDayEnd = date.replace("(二)", "");
                            tempDayEnd = date.replace("(四)", "");
                            tempDayEnd = date.replace("(日)", "");
                        }
                    }
                }
                //System.out.println("共: " + resultJsonArray.size() + "期");
            }
        }
    }



    private static void write(String destPath, String content, boolean append){
        File file = new File(destPath);
        try(FileOutputStream fos = new FileOutputStream(file, append)){
            fos.write(content.getBytes("UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<String> readLines(String srcPath){
        File file = new File(srcPath);
        List<String> list = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(file)){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                list.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private static String calDate(String dateStr, int day) throws ParseException {
        Date date = SDF.parse(dateStr);
        long timeMills = date.getTime();
        date = new Date(timeMills - 86400000 * day);
        return SDF.format(date);
    }
}
