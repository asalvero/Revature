import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDao userDao = UserDaoFactory.getUserDao();
        EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
        CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();

        Scanner in = new Scanner(System.in);

        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println("Welcome to Bank of !");
            System.out.println();
            System.out.println("Press 1: Sign Up");
            System.out.println("Press 2: Customer Login");
            System.out.println("Press 3: Employee Login");
            System.out.println("Press 4: Exit System");
            System.out.println();
            System.out.println("Enter number to perform action: ");

            int input = in.nextInt();
            switch(input){
                case 1:{// sign up
                    System.out.println("Enter first name: ");
                    String fname = in.next();
                    System.out.println("Enter last name: ");
                    String lname = in.next();
                    System.out.println("Enter a username: ");
                    String username = in.next();
                    System.out.println("Enter a password: ");
                    String password = in.next();
                    User user = new User();
                    user.setFname(fname);
                    user.setLname(lname);
                    user.setUsername(username);
                    user.setPassword(password);
                    userDao.signUp(user);
                    break;
                }
                case 2:{// sign in as customer
                    System.out.println("Enter username: ");
                    String username = in.next();
                    System.out.println("Enter password: ");
                    String password = in.next();
                    Customer customer = new Customer();
                    customer.setUsername(username);
                    customer.setPassword(password);
                    Customer customer1 = customerDao.signIn(customer);
                    if(customer1.getId() == 0){
                        break;
                    }

                    boolean check1 = true;
                    while(check1) {

                        System.out.println();
                        System.out.println("Select from the following options what you would like to do: ");
                        System.out.println();
                        System.out.println("Press 1: Apply for account");
                        System.out.println("Press 2: View balance");
                        System.out.println("Press 3: Withdraw funds");
                        System.out.println("Press 4: Deposit funds");
                        System.out.println("Press 5: Exit");
                        System.out.println();
                        System.out.println("Enter number to perform action: ");

                        int decision1 = in.nextInt();
                        System.out.println();
                        switch (decision1) {
                            case 1: {// apply for account
                                System.out.println("Would you like a checking or savings?");
                                String accountType = in.next();
                                System.out.println("How much would you like to deposit?");
                                double amount = in.nextDouble();
                                customer1.setAccountType(accountType);
                                customer1.setBalance(amount);
                                customerDao.apply(customer1);
                                break;
                            }
                            case 2: {// view balance
                                customerDao.viewBalance(customer1);
                                break;
                            }
                            case 3: {// withdraw
                                System.out.println("How much would you like to take?");
                                double amount = in.nextDouble();
                                System.out.println("What account would you like to take it out of?");
                                int id = in.nextInt();
                                customerDao.withdraw(amount, id, customer1);
                                break;
                            }
                            case 4: {// deposit
                                System.out.println("How much would you like to deposit?");
                                double amount = in.nextDouble();
                                System.out.println("What account would you like to put it in?");
                                int id = in.nextInt();
                                customerDao.deposit(amount, id, customer1);
                                break;
                            }
                            case 5: {// exit
                                System.out.println("Exiting...");
                                check1 = false;
                                break;
                            }
                            default:{
                                System.out.println("Wrong input selected. Please try again.");
                            }
                        }
                    }

                    break;
                }
                case 3:{// sign in as employee
                    System.out.println("Enter username: ");
                    String username = in.next();
                    System.out.println("Enter password: ");
                    String password = in.next();
                    Employee employee = new Employee();
                    employee.setUsername(username);
                    employee.setPassword(password);
                    Employee employee1 = (Employee) employeeDao.signIn(employee);

                    boolean check2 = true;
                    while(check2) {

                        System.out.println();
                        System.out.println("Select from the following options what you would like to do: ");
                        System.out.println();
                        System.out.println("Press 1: Show list of customers");
                        System.out.println("Press 2: View Customer's account");
                        System.out.println("Press 3: Approve account");
                        System.out.println("Press 4: Deny account");
                        System.out.println("Press 5: Exit system");
                        System.out.println();
                        System.out.println("Enter number to perform action: ");

                        int decision2 = in.nextInt();
                        switch(decision2){
                            case 1:{ // Show list of customer
                                System.out.println("Printing list of employees: ");
                                List<Customer> customers = employeeDao.showCustomers();
                                for(Customer customer : customers){
                                    System.out.println(customer);
                                }
                                break;
                            }
                            case 2:{// view customer account
                                System.out.println("Please insert the ID of the customer: ");
                                int id = in.nextInt();
                                Customer customer = new Customer();
                                employeeDao.viewBalanceOfCustomer(customer, id);
                                break;
                            }
                            case 3:{// approve account
                                System.out.println("What customer's account would you like to approve?");
                                int id = in.nextInt();
                                employeeDao.approveAccount(id);
                                break;
                            }
                            case 4:{// deny account
                                System.out.println("What account would you like to deny?");
                                int id = in.nextInt();
                                employeeDao.denyAccount(id);
                                break;
                            }
                            case 5:{// exit system
                                System.out.println("Exiting...");
                                check2 = false;
                                break;
                            }
                            default:{
                                System.out.println("Incorrect input. Please select a valid option.");
                            }
                        }
                    }
                    break;
                }
                case 4:{// exit the system
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                }
                default:{
                    System.out.println("Wrong input selected. Please try again.");
                    System.out.println();
                }
            }
        }
    }
}
