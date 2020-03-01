# -*- coding: utf-8 -*-
import mysql.connector
import traceback

db = mysql.connector.connect(
    host = 'localhost',
    user = 'root',
    password = 'escray800201',
    database = 'geektime',
    auth_plugin = 'mysql_native_password'
)

cursor = db.cursor()
sql = "select name, hp_max from heros where hp_max > 6000"
cursor.execute(sql)
data = cursor.fetchall()
for player in data:
    print(player)

cursor.close()
db.close()
