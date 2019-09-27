import java.util.Scanner;

public class First {
  static int lines_;
  static int colomns_;

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    lines_ = scan.nextInt();
    colomns_ = scan.nextInt();

    int[][] matrix = new int[lines_][colomns_];
    for (int i = 0; i < lines_; i++) {
      matrix[i][0] = 1;
      for (int j = 1; j < colomns_; j++) {
        matrix[i][j] = 0;
      }
    }

    scan.close();
  }
}
