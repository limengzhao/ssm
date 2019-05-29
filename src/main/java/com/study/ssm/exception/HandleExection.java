package com.study.ssm.exception;

public class HandleExection extends Exception {
    private static final long serialVersionUID = 1L; 
    
    //空构造
    public HandleExection(){
        
    }
    
 // 提供一个有参数的构造方法，可自动生成
    public HandleExection(String message) { 
      super(message);// 把参数传递给Throwable的带String参数的构造方法 
    } 
    
   
    public HandleExection(String message,Throwable cause){
        super (message , cause);
    }
    
    public HandleExection(Throwable cause){
        super(cause);
    }
    
}
