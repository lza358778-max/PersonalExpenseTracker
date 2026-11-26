package expense;
import java.util.Arrays;
import java.util.List;

public class Expense {
private static final List<String> VALID_CATEGORIES = Arrays.asList("Food", "Shopping", "Transport");

private double amount;
private String category;

public Expense(double amount, String category) {
    setAmount(amount);
    setCategory(category);
}

public double getAmount() {
    return amount;
}

public void setAmount(double amount) {
    if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
        throw new IllegalArgumentException("Error: Amount must be a number greater than zero.");
    }
    this.amount = amount;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    if (category == null || !VALID_CATEGORIES.contains(category)) {
        throw new IllegalArgumentException(
            "Error: Invalid category '" + category + "'. Choose Food, Shopping, or Transport."
        );
    }
    this.category = category;
}
}