import java.util.Scanner;

public class First {
  static int lines;
  static int columns;

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    lines = -1;
    columns = -1;

    while (lines < 0 || columns < 0) {
      System.out.println("Enter lines then colomns (must be > 0)");
      lines = scan.nextInt();
      columns = scan.nextInt();
    }

    int[][] matrix = new int[lines][columns];
    for (int i = 0; i < lines; i++) {
      matrix[i][0] = 1;
      for (int j = 1; j < columns; j++) {
        matrix[i][j] = 0;
      }
    }

    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    scan.close();
  }
}
