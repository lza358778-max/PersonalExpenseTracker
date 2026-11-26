package expense;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager implements Reportable {
private static final String DEFAULT_CSV_FILE = "expenses.csv";

private List<Expense> expenses;
private double total;
private double foodTotal;
private double shoppingTotal;
private double transportTotal;

public ExpenseManager() {
    this.expenses = new ArrayList<>();
    resetTotals();
}

private void resetTotals() {
    this.total = 0.0;
    this.foodTotal = 0.0;
    this.shoppingTotal = 0.0;
    this.transportTotal = 0.0;
}

public void addExpense(double amount, String category) {
    Expense expense = new Expense(amount, category); // throws IllegalArgumentException if invalid
    expenses.add(expense);
    calculateTotal();
    calculateCategoryTotal();
    System.out.println("Expense added successfully!");
}

public double calculateTotal() {
    double sum = 0.0;
    for (Expense e : expenses) {
        sum += e.getAmount();
    }
    this.total = sum;
    return this.total;
}

public void calculateCategoryTotal() {
    double food = 0.0;
    double shopping = 0.0;
    double transport = 0.0;

    for (Expense e : expenses) {
        switch (e.getCategory()) {
            case "Food":
                food += e.getAmount();
                break;
            case "Shopping":
                shopping += e.getAmount();
                break;
            case "Transport":
                transport += e.getAmount();
                break;
        }
    }

    this.foodTotal = food;
    this.shoppingTotal = shopping;
    this.transportTotal = transport;
}

public double calculateCategoryTotal(String category) {
    calculateCategoryTotal();
    switch (category) {
        case "Food":
            return foodTotal;
        case "Shopping":
            return shoppingTotal;
        case "Transport":
            return transportTotal;
        default:
            throw new IllegalArgumentException("Error: Invalid category '" + category + "'.");
    }
}

public Map<String, Double> getCategoryTotals() {
    calculateCategoryTotal();
    Map<String, Double> totals = new LinkedHashMap<>();
    totals.put("Food", foodTotal);
    totals.put("Shopping", shoppingTotal);
    totals.put("Transport", transportTotal);
    return totals;
}

public void viewExpenses() {
    calculateTotal();
    calculateCategoryTotal();

    if (expenses.isEmpty()) {
        System.out.println("No expenses recorded yet.");
        return;
    }

    System.out.println("\n===== Your Expenses =====");
    if (foodTotal > 0) {
        System.out.println("Food : " + foodTotal + " SAR");
    }
    if (shoppingTotal > 0) {
        System.out.println("Shopping : " + shoppingTotal + " SAR");
    }
    if (transportTotal > 0) {
        System.out.println("Transport : " + transportTotal + " SAR");
    }
    System.out.println("-------------------------");
    System.out.println("Total: " + total + " SAR");
}

@Override
public void exportCSV() throws IOException {
    exportCSV(DEFAULT_CSV_FILE);
}

public void exportCSV(String fileName) throws IOException {
    calculateTotal();
    calculateCategoryTotal();

    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
        writer.println("Category,Amount");
        if (foodTotal > 0) {
            writer.println("Food," + foodTotal);
        }
        if (shoppingTotal > 0) {
            writer.println("Shopping," + shoppingTotal);
        }
        if (transportTotal > 0) {
            writer.println("Transport," + transportTotal);
        }
    }
    System.out.println("CSV report exported successfully!");
}

public List<Expense> getExpenses() {
    return expenses;
}

public double getTotal() {
    calculateTotal();
    return total;
}

public double getFoodTotal() {
    return foodTotal;
}

public double getShoppingTotal() {
    return shoppingTotal;
}

public double getTransportTotal() {
    return transportTotal;
}
}