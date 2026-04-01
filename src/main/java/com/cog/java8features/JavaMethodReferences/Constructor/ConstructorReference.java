package com.cog.java8features.JavaMethodReferences.Constructor;

interface Messageable{
    Message getMessage(String msg);
}
class Message{
    Message(String msg){
        System.out.print(msg);
    }
}

interface Employee {
    EmployeeDetails display();
}

class EmployeeDetails{
    EmployeeDetails(){
        System.out.println("EmployeeDetails");
    }
}

public class ConstructorReference {
    public static void main(String[] args) {
        Messageable hello = Message::new;
        hello.getMessage("Hello");

        Employee employee = EmployeeDetails::new;
    }
}