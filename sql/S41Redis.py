import redis
import time
# 创建redis连接
pool = redis.ConnectionPool(host='localhost', port=6379)
r = redis.StrictRedis(connection_pool=pool)
# 记录当前时间
time1 = time.time()
# 1万次写
for i in range(10000):
    data = {'username': 'zhangfei', 'age': 28}
    r.hmset("users" + str(i), data)
# 统计写时间
delta_time = time.time() - time1
print(delta_time)
# 统计当前时间
time1 = time.time()
# 1万次读
for i in range(10000):
    result = r.hmget("user"+str(i), ['username', 'age'])
# 统计读时间
delta_time = time.time() - time1
print(delta_time)
