package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 计算数组长度
        int len = nums.length;
        if (len == 0) return new ArrayList<>();

        // 对数组进行排序
        Arrays.sort(nums);

        // 第 1 步：动态规划找最大子集的个数
        int[] dp = new int[len];
        int[] prev = new int[len]; // 记录上一个数的索引
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1); // 初始化前驱数组
        int maxSize = 1;
        int maxValIndex = 0; // 存储最大子集的最后一个元素的索引

        // 动态规划计算
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 检查整除条件
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j; // 记录链的前一个索引
                    }
                }
            }
            // 更新最大子集的大小和对应的索引
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxValIndex = i;
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        for (int i = maxValIndex; i >= 0; ) {
            res.add(nums[i]);
            i = prev[i]; // 追踪到前驱元素
        }

        // 因为是从后向前添加的，反转结果
        Collections.reverse(res);
        return res;
    }
}

