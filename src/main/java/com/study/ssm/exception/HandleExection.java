package com.study.ssm.exception;

public class HandleExection extends Exception {
    private static final long serialVersionUID = 1L; 
    
    //�չ���
    public HandleExection(){
        
    }
    
 // �ṩһ���в����Ĺ��췽�������Զ�����
    public HandleExection(String message) { 
      super(message);// �Ѳ������ݸ�Throwable�Ĵ�String�����Ĺ��췽�� 
    } 
    
   
    public HandleExection(String message,Throwable cause){
        super (message , cause);
    }
    
    public HandleExection(Throwable cause){
        super(cause);
    }
    
}
