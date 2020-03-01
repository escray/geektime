from sqlalchemy import and_, or_, func
from sqlalchemy import create_engine
from sqlalchemy import Column, String, Integer, Float
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
# 初始化数据库连接，修改为你的数据库用户名和密码
engine = create_engine('mysql+mysqlconnector://root:escray800201@localhost:3306/geektime')

Base = declarative_base()

# 增加to_dict()方法到Base类中
def to_dict(self):
    return {c.name: getattr(self, c.name, None)
            for c in self.__table__.columns }

# 将对象可以转化为dict类型
Base.to_dict = to_dict
# 查询身高>=2.08的球员有哪些

# 定义Player对象:
class Player(Base):
    # 表的名字:
    __tablename__ = "player"
    # 表的结构:
    player_id = Column(Integer, primary_key=True, autoincrement=True)
    team_id = Column(Integer)
    player_name = Column(String(255))
    height = Column(Float(3,2))

# 创建DBSession类型:
DBSession = sessionmaker(bind=engine)
# 创建session对象:
session = DBSession()
# 创建Player对象:
new_player = Player(team_id = 1003, player_name = "约翰-科林斯", height = 2.08)
# 添加到session:
session.add(new_player)
# 提交即保存到数据库:
session.commit()

# 查询身高>=2.08的球员有哪些
rows = session.query(Player).filter(Player.height >= 2.08).all()
print([row.to_dict() for row in rows])

# 按照 team_id 进行分组，同时筛选分组后数据行数大于 5 的分组，
# 并且按照分组后数据行数递增的顺序进行排序，显示 team_id 字段，以及每个分组的数据行数
rows = session.query(Player.team_id, func.count(Player.player_id)).group_by(Player.team_id).having(func.count(Player.player_id)>5).order_by(func.count(Player.player_id).asc()).all()
print(rows)

# 把球员约翰·科林斯的身高改成 2.17
try:
    target_obj = {'height':2.17}
    session.query(Player).filter(Player.player_name=="约翰·科林斯").update(target_obj);
    session.commit()
    print("update success")
except Exception as e:
    print(e)

# row = session.query(Player).filter(Player.player_name=='索恩-马克').first()
# row.height = 2.17

# row = session.query(Player).filter(and_(Player.height >= 2.08, Player.player_name == "约翰·科林斯")).first();
# print(row)

# 删除姓名为约翰·科林斯的球员
try:
    row = session.query(Player).filter(Player.player_name=="约翰-科林斯").first()
    session.delete(row)
    session.commit()
    print("delete success")
except Exception as e:
    print(e)
# 关闭session:
session.close()
