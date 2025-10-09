import org.jetbrains.annotations.NotNull;

public class JavaMoney implements Comparable<JavaMoney> {

  private final long amount;

  public JavaMoney(long amount) {
    this.amount = amount;
  }

  public JavaMoney plus(JavaMoney other) {
    return new JavaMoney(this.amount + other.amount);
  }

  @Override
  public int compareTo(@NotNull JavaMoney o) {
    return Long.compare(this.amount, o.amount);
  }

  @Override
  public final boolean equals(Object o) {
    if (!(o instanceof JavaMoney javaMoney)) {
      return false;
    }

    return this.amount == javaMoney.amount;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(amount);
  }
}
