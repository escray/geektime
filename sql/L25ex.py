# -*- coding: utf-8 -*-

import time
# 插入数据
result = []
for i in range(1000000):
    result.append(i)
# 检索数据
time_start = time.time();
for i in range(10000):
    temp = result.index(i)
time_end = time.time();
print('数组检索时间：', time_end - time_start);

# 插入数据
result = {}
for i in range(1000000):
    result[i] = i
# 检索数据
time_start = time.time()
for i in range(10000):
    temp = result[i]
time_end = time.time()
print('哈希检索时间：',time_end-time_start)
