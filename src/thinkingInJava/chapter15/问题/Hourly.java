package thinkingInJava.chapter15.问题;

//编译器擦除意味着 Hourly重复两次实现Payable
//public class Hourly extends Employee implements Payable<Hourly>{
//
//}

//基类覆盖接口
public class Hourly extends Employee implements Payable<Employee>{

}
