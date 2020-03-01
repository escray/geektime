# 使用 SQLAlalchemy 工具查询身高为 2.08 米的球员，并且将这些球员的身高修改为 2.09
from sqlalchemy import create_engine
from sqlalchemy import Column, String, Integer, Float
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

engine = create_engine('mysql+mysqlconnector://root:escray800201@localhost:3306/geektime')

Base = declarative_base()

def to_dict(self):
    return { c.name: getattr(self, c.name, None) for c in self.__table__.columns }

Base.to_dict = to_dict

class Player(Base):
    __tablename__ = "player"
    player_id = Column(Integer, primary_key=True, autoincrement=True)
    height = Column(Float(3,2))

DBSession = sessionmaker(bind=engine)
session = DBSession()

target_obj = {'height':2.09}

try:
    session.query(Player).filter(Player.height == 2.08).update(target_obj);
    session.commit()
    print("update success")
except Exception as e:
    print(e)
