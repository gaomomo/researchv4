package com.xjtu.edu.research.controller;

import com.xjtu.edu.research.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class TestController {
    @RequestMapping({"/","/index.html"})
    public String index(){
        return "index";
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(value = "/getResult",method = RequestMethod.POST)
    public String getResult(Record rcd, Model model) {
        if (rcd.getChildName() == "" || rcd.getParName()== ""  || rcd.getSchool() == "" || rcd.getPicked().isEmpty()||null==rcd.getPicked() ||rcd.getPhone()=="") {
            model.addAttribute("msg1", "*为必填项，请将所有内容填写完整");
            model.addAttribute("rcd", rcd);
            return "index";
        }else if(!validateMobilePhone(rcd.getPhone())){
            model.addAttribute("phone_error", "手机号码格式错误");
            model.addAttribute("rcd", rcd);
            return "index";
        } else {
            Map<String, List<String>> res = new HashMap<>();
            res.put("主动,讨好型人格（不安）", Arrays.asList("11", "12", "25", "26", "31", "36", "39", "44"));
            res.put("主动,表现型人格（不安）", Arrays.asList("10", "19", "20", "23", "27", "35", "40", "43"));
            res.put("主动,控制型人格（不安）", Arrays.asList("7", "8", "24", "28", "37", "45", "46", "48"));
            res.put("被动,依赖型人格（不安）", Arrays.asList("3", "5", "9", "14", "16", "18", "32", "42"));
            res.put("主动,暴躁型人格（不安）", Arrays.asList("6", "17", "22", "30", "33", "38", "41", "47"));
            res.put("主动,冷漠型人格（不安）", Arrays.asList("1", "2", "4", "13", "15", "21", "29", "34"));
            List<String> pickedList = Arrays.asList(rcd.getPicked().substring(2,rcd.getPicked().length()-2).replaceAll("\"","").split(","));
            Map<String, Integer> countmap = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : res.entrySet()) {
                List<String> values = entry.getValue();
                int count = 0;
                for (String v : values) {
                    if (pickedList.contains(v)) {
                        count++;
                    }
                }
                countmap.put(entry.getKey(), count);
            }
            List<Integer> l1 = new ArrayList<>();
            for (Integer val : countmap.values()) {
                l1.add(val);
            }
            Collections.sort(l1);
            StringBuffer result = new StringBuffer();
            Map<String, Integer> linkMap = new LinkedHashMap<>();
            for (int i = l1.size() - 1; i >= 0; i--) {
                for (String key : countmap.keySet()) {
                    if (countmap.get(key) == l1.get(i)) {
                        linkMap.put(key, countmap.get(key));
                    }
                }
            }
            String sqlstr = "INSERT INTO record(childName,childAge,parName,phone,date,school,picked,result2) VALUES(?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sqlstr, rcd.getChildName(), rcd.getChildAge(),rcd.getParName(),rcd.getPhone(),rcd.getDate(),rcd.getSchool(),rcd.getPicked(),result);
            model.addAttribute("msg", linkMap);
            return "result";
        }
    }
    public static boolean validateMobilePhone(String in) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(in).matches();
    }

}
