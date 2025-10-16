interface A{
    public void add();
    int num1=5;
}

interface B {
    public void sub();
    int num2=2;
}

class demo implements A,B{
    public void add(){
        System.out.println("Addition of numbers: "+(num1+num2));

    }
    public void sub(){
        System.out.println("Subtraction of numbers: "+(num2-num1));
    }

}

public class exampleInterface {
    public static void main(String[]args){

        demo d=new demo();
        d.add();
        d.sub();

    }
}
