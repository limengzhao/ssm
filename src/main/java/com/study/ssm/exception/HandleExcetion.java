package com.study.ssm.exception;

public class HandleExcetion extends Exception {
    private static final long serialVersionUID = 1L; 
    
    //�չ���
    public HandleExcetion(){
        
    }
    
 // �ṩһ���в����Ĺ��췽�������Զ�����
    public HandleExcetion(String message) { 
      super(message);// �Ѳ������ݸ�Throwable�Ĵ�String�����Ĺ��췽�� 
    } 
    
   
    public HandleExcetion(String message,Throwable cause){
        super (message , cause);
    }
    
    public HandleExcetion(Throwable cause){
        super(cause);
    }
    
}
