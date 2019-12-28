class Result:
    current_wheat = 1
    total_wheat = 1

def proveWithPow(k, result):
    if k == 1:
        if (pow(2, 1) - 1) == 1:
            return True
        else:
            return False
    else:
        provePrevious = proveWithPow(k-1, result)
        result.current_wheat *= 2
        result.total_wheat += result.current_wheat
        proveCurrent = False
        if (pow(2,k)-1) == result.total_wheat:
            proveCurrent = True
        if provePrevious & proveCurrent:
            return True
        else:
            return False

def get_result(n):
    return 1 if n==1 else get_result(n-1)*2 + 1

def prove(k, result):
    if k == 1:
        if  (2 ** 1 - 1) == 1:
            result.current_wheat = 1
            result.total_wheat = 1
            return True
        else:
            return False
    else:
        provePrevious = prove(k-1, result)
        result.current_wheat *= 2
        result.total_wheat += result.current_wheat
        proveCurrent = False
        if result.total_wheat == (2**k - 1):
            proveCurrent = True
        if provePrevious & proveCurrent:
            return True
        else:
            return False
    return

if __name__ == '__main__':
    result = Result()
    print(proveWithPow(53, result))
    print(proveWithPow(54, result))

    print(get_result(63))

    grid = 63
    result = Result()
    print(prove(grid, result))
    print(prove(64, result))
    print(prove(127, result))
    print(prove(999, result))
