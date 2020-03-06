import sqlite3
# 创建数据库连接
conn = sqlite3.connect("wucai.db")
# 获取游标
cur = conn.cursor()
# 创建数据表
cur.execute("create table if not exists heros (id int primary key, name text, hp_max real, mp_max real, role_main text)")
# 插入英雄数据
cur.execute("delete from heros;");
cur.executemany('insert into heros values(?, ?, ?, ?, ?)',
           ((10000, '夏侯惇', 7350, 1746, '坦克'),
            (10001, '钟无艳', 7000, 1760, '战士'),
            (10002, '张飞', 8341, 100, '坦克'),
            (10003, '牛魔', 8476, 1926, '坦克'),
            (10004, '吕布', 7344, 0, '战士')))
cur.execute("select id, name, hp_max, mp_max, role_main from heros")
result = cur.fetchall();
print(result)
# 提交事务
conn.commit()
# 关闭游标
cur.close()
# 关闭数据库连接
conn.close()
