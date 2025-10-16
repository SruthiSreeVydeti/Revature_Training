import java.util.Scanner;

class Employee {
    int id;
    String name;
    int age;
    String position;
}

public class Details{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            employees[i] = new Employee();

            System.out.println("\nEnter details for Employee " + (i + 1) + ":");

            System.out.print("ID: ");
            employees[i].id = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            employees[i].name = sc.nextLine();

            System.out.print("Age: ");
            employees[i].age = sc.nextInt();
            sc.nextLine();

            System.out.print("Position: ");
            employees[i].position = sc.nextLine();
        }

        System.out.println("***Employee Details***");
        for (Employee e : employees) {
            System.out.println("Emplyee ID: " + e.id + ", Name: " + e.name + ", Age: " + e.age + ", Position: " + e.position);
        }

    }
}
