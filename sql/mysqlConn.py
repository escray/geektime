# -*- coding: utf-8 -*-
import mysql.connector
import traceback
# 打开数据库连接
db = mysql.connector.connect(
    host="localhost",
    user="root",
    # 写上你的数据库密码
    password="escray800201",
    database="geektime",
    auth_plugin="mysql_native_password"
)
# 获取操作游标
cursor = db.cursor()
# 执行SQL语句
cursor.execute("select version();")
# 获取一条数据
data = cursor.fetchone()
print("MySQL版本: %s " % data);
# 插入新球员
try:
    sql = "insert into player (team_id, player_name, height) values (%s, %s, %s)"
    val = (1003, "约翰-科林斯", 2.08)
    cursor.execute(sql, val)
    db.commit()
    print(cursor.rowcount, "记录插入成功。")
except Exception as e:
    # 打印异常信息
    traceback.print_exc()
    # 回滚
    db.rollback()
finally:
    # 关闭数据库连接
    # db.close()
    db.commit()

# 查询身高大于等于2.08的球员
sql = "select player_id, player_name, height from player where height >= 2.08"
cursor.execute(sql)
data = cursor.fetchall()
for each_player in data:
    print(each_player)

# 修改球员约翰-科林斯
sql = "update player set height = %s where player_name = %s"
val = (2.09, "约翰-科林斯")
cursor.execute(sql, val)
db.commit()
print(cursor.rowcount, "记录被修改。")

# 删除约翰·科林斯这个球员的数据
sql = "delete from player where player_name = %s"
val = ("约翰-科林斯",)
cursor.execute(sql,val)
db.commit()
print(cursor.rowcount, "记录删除成功。")

# 关闭游标&数据库连接
cursor.close()
db.close()
