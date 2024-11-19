import java.util.*;

class Solution {
    public int solution(int n) {

        ArrayList<Integer> result = new ArrayList<>();

        int max = n;
        for (int idx = 1; idx <= Math.sqrt(n); idx++) {

            // 오른쪽 값이 왼쪽 값보다 작을 경우, break
            if (max < idx) {
                break;
            }

            // 왼쪽 값이 n의 약수일 경우
            if (n % idx == 0) {
                max = n / idx;

                // 왼쪽 값과 오른쪽 값이 동일할 경우엔, 한번만 추가 (ex. 6 (왼쪽 값) * 6 (오른쪽 값) = 36)
                if (idx != max) {
                    result.add(idx);
                }

                result.add(max);
            }
        }

        return result.stream().mapToInt(i -> i).sum();
    }
}