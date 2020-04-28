class Solution {
  public static double sqrt(double x, double precision) {
    if (x < 0) {
      return Double.NaN;
    }
    double low = 0;
    double high = x;
    if (x < 1 && x > 0) {
      // 小于1的时候
      low = x;
      high = 1;
    }
    double mid = low + (high - low)/2;
    while (high - low > precision) {
      // TODO: mid * mid 可能溢出
      if (mid * mid > x) {
        high = mid;
      } else if (mid * mid < x) {
        low = mid;
      } else {
        return mid;
      }
      mid = low + (high - low)/2;
    }
    return mid;
  }

  public static float invSqrt(float x) {
    float xhalf = 0.5f * x;
    int i = Float.floatToIntBits(x);
    i = 0x5f3759df - (i>>1);
    x = Float.intBitsToFloat(i);
    x *= (1.5f - xhalf * x * x);
    return x;
  }

  public static double invSqrt(double x) {
    double xhalf = 0.5d * x;
    long i = Double.doubleToLongBits(x);
    i = 0x5fe6ec85e7de30daL - (i >> 1);
    x = Double.longBitsToDouble(i);
    x *= (1.5d - xhalf * x * x);
    return x;
  }
}
