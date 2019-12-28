# -*- coding: UTF-8 -*-
import copy
import math
# 四种面额的纸币
rewards = [1,2,5,10]
list = []
""" 使用函数的递归（嵌套）调用，找出所有可能的奖赏组合
    Args:
        total_reword: 奖赏总金额
        result: 保存当前的解
    Returns: void
"""
def get_sum_combo(total_reward, result=[]):

    if total_reward == 0:
        list.append(result)
        print(result)
        return
    elif total_reward < 0:
        return
    else:
        for i in range(len(rewards)):
            new_result = copy.copy(result)
            new_result.append(rewards[i])
            get_sum_combo(total_reward - rewards[i], new_result)

""" add 1 to result_list and print multiply combo
    Args:
        result_list (list): 乘积列表
    Returns: None
"""
def print_multiply_combo(result_list):
    for i in range(len(result_list) + 1):
        new_result_list = copy.copy(result_list)
        new_result_list.insert(i, 1)
        print(new_result_list)
    pass

""" 使用函数的递归（嵌套）调用，找出所有可能的乘积组合
    Args:
        product: 乘积结果
        result: 保存当前的解
    Returns: None
"""
def get_multiply_combo(product, result=[]):
    product_factors = get_product_factors(product)
    for i in product_factors:
        if i == 1:
            continue
        new_result = copy.copy(result)
        new_result.append(i)

        if i == product:
            print_multiply_combo(new_result)
        divisor = product // i
        if divisor != 1:
            get_multiply_combo(divisor, new_result)

def get_product_factors(n):
    if n <= 0 or not isinstance(n, int):
        return []
    else:
        product_factors = []
        #for i in range(1, math.ceil(math.sqrt(n))+1):
        for i in range(1, int(math.ceil(math.sqrt(n))+1)):
            if n % i == 0:
                product_factors.append(i)
                product_factors.append(int(n/i))

        return sorted(set(product_factors))

if __name__ == "__main__":

    # get_sum_combo(2)
    # print(len(list))
    # list = []
    # get_sum_combo(3)
    # print(len(list))
    # list = []
    # get_sum_combo(10)
    # print(len(list))

    print(get_product_factors(1))
    print(get_product_factors(2))
    print(get_product_factors(8))


    get_multiply_combo(6)
    get_multiply_combo(8)
