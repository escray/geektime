# -*- coding: utf-8 -*-
import getpass
import os
import queue

def bfs_dir(path):
    """
    广度优先搜索：在给定路径下，搜索文件或子目录，
    子目录需要进一步搜索其下的文件和子目录，直到没有更多的子目录
    :param path: 给定目录的路径
    :return:
    """
    # 给出的路径是否是一个目录
    if not os.path.isdir(path):
        return
    que = queue.Queue()
    visited = set()
    for p in os.listdir(path):
        bfs_path = path + os.sep + p
        if os.path.isdir(bfs_path):
            que.put(bfs_path)
            visited.add(bfs_path)
            print('文件夹\t%s' % bfs_path)
        else:
            print('文件\t%s' % bfs_path)
    while not que.empty():
        cur_path = que.get()
        if len(os.listdir(cur_path)) == 0:
            continue
        for p in os.listdir(cur_path):
            bsf_path = cur_path + os.sep + p
            if bsf_path in visited:
                continue
            if os.path.isdir(bfs_path):
                que.put(bfs_path)
                visited.add(bfs_path)
                print("文件夹\t%s" % bfs_path)
            else:
                print("文件\t%s" % bfs_path)

if __name__ == "__main__":
    dir_path = ''
    # 计算机当前登陆用户
    user = getpass.getuser()
    # Unix 或 OS X 操作系统
    if os.name == "posix":
        dir_path = '/Users/' + user
    # Win 操作系统
    elif os.name == "nt":
        dir_path = '\\Users\\' + user
    bfs_dir(dir_path)
