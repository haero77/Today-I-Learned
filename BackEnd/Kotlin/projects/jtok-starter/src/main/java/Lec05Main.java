public class Lec05Main {
  public static void main(String[] args) {

  }

  private void validateScoreIsNotNegative(int score) {
    if (score < 0) {
      throw new IllegalArgumentException(String.format("%d는 0보다 작을 수 없습니다.", score));
    }
  }

  private String getPassOrFail(int score) {
    if (score >= 50) {
      return "P";
    } else {
      return "F";
    }
  }
}
