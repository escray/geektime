import java.util.ArrayList;
/**
 * @Auther: yli
 * @Date: 2018/12/19 17:19
 * @Description:
 */
 public class L05Iteration {
   public static void main(String[] args){
     long total = 8;
     recursion(total, new ArrayList<Long>());
   }

   public static void recursion(long total, ArrayList<Long> result) {
     if (total == 1) {
       if (!result.contains(1L)) {
          result.add(1L);
       }
       System.out.println(result);
       return;
     } else {
       for (long i = 1; i <= total ; i++) {
         if ((i==1) && result.contains(1L)) {
           continue;
         }
         ArrayList<Long> newList = (ArrayList<Long>)(result.clone());
         //if (total % i == 0) {
            newList.add(Long.valueOf(i));
         //}
         if (total % 1 != 0) {
           continue;
         }
         recursion(total/i, newList);
       }
     }
   }
 }
