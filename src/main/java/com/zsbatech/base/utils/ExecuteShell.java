package com.zsbatech.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExecuteShell {
	/**
	 * java调用shell脚本
	 * @param command
	 * @return
	 * @throws InterruptedException
	 */
	public static List<String> exec(String command) throws InterruptedException {
		System.out.println("command: "+command);
        List<String> list = new ArrayList<>();
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(new String[] { "/bin/sh","-c", command});
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
            	list.add(line);
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShell.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }
	
	
	/**
	 * java调用shell脚本
	 * @param command
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean execReturnNum(String command) throws InterruptedException {
		System.out.println("command: "+command);
        List<String> list = new ArrayList<>();
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(new String[] { "/bin/sh","-c", command});
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
            	list.add(line);
            }
            input.close();
            output.close();
            pro.destroy();
            System.out.println("list: "+list.toString());
            Logger.getLogger(ExecuteShell.class.getName()).log(Level.SEVERE, null, list.toString());
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShell.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
	
	
	/**
	 * java调用shell脚本
	 * @param command
	 * @return
	 * @throws InterruptedException
	 */
	public static Map<String,Object> execReturnMap(String command) throws InterruptedException {
		System.out.println("command: "+command);
		Map<String,Object> resultMap = new HashMap<String,Object>();
        List<String> list = new ArrayList<>();
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(new String[] { "/bin/sh","-c", command});
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
            	list.add(line);
            }
            input.close();
            output.close();
            pro.destroy();
            Logger.getLogger(ExecuteShell.class.getName()).log(Level.SEVERE, null, list.toString());
            System.out.println("list: "+list.toString());
            resultMap.put("status", true);
            resultMap.put("list", list);
            return resultMap;
        } catch (IOException ex) {
            Logger.getLogger(ExecuteShell.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultMap.put("status", false);
        resultMap.put("list", list);
        return resultMap;
    }
    
}
