    package expense;

    import java.util.Scanner;

    import java.io.IOException;

    public class Main {

        public static void main(String[] args) throws IOException {

            Scanner input = new Scanner(System.in);

            ExpenseManager manager = new ExpenseManager();

            int choice = 0;

            do {

                System.out.println("===== Expense Tracker =====");

                System.out.println("1. Add Expense");

                System.out.println("2. View Expenses");

                System.out.println("3. Export CSV Report");

                System.out.println("4. Show Chart");

                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");

                try {

                    choice = Integer.parseInt(input.nextLine());

                    switch (choice) {

                        case 1:

                            System.out.print("Enter amount: ");

                            double amount =

                                Double.parseDouble(input.nextLine());

                            System.out.println("Choose category:");

                            System.out.println("1. Food");

                            System.out.println("2. Shopping");

                            System.out.println("3. Transport");

                            System.out.print("Enter category: ");

                            int categoryChoice =

                                Integer.parseInt(input.nextLine());

                            String category;

                            switch (categoryChoice) {

                                case 1:

                                    category = "Food";

                                    break;

                                case 2:

                                    category = "Shopping";

                                    break;

                                case 3:

                                    category = "Transport";

                                    break;

                                default:

                                    throw new IllegalArgumentException(

                                        "Invalid category!"

                                    );

                            }

                            manager.addExpense(amount, category);

                            break;

                        case 2:

                            manager.viewExpenses();

                            break;

                        case 3:

                            manager.exportCSV();

                            break;

                        case 4:

                            ExpenseChart chart = new ExpenseChart();

                            chart.displayChart(

                                manager.getFoodTotal(),

                                manager.getShoppingTotal(),

                                manager.getTransportTotal()

                            );

                            break;

                        case 5:

                            System.out.println("Goodbye!");

                            break;

                        default:

                            System.out.println("Invalid choice!");

                    }

                } catch (NumberFormatException e) {

                    System.out.println("Please enter a valid number.");

                } catch (IllegalArgumentException e) {

                    System.out.println(e.getMessage());

                }

                System.out.println();

            } while (choice != 5);

            input.close();

        }

    }