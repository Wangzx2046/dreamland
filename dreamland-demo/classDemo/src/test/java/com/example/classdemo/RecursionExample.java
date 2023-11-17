public class RecursionExample {
   public static void main(String[] args) {
       int n = 10;
       printNums(n);
   }
   
   public static void printNums(int n) {
       if(n > 0) {
           printNums(n - 1);
           System.out.println(n);
       }
   }
}