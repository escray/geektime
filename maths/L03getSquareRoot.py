def getSquareRoot1(x):
    y = x
    if x > 1 and isinstance(x, int):
        flag, num = 1, 0
        global middle
        while num <= 100:
            middle = (x + flag) / 2
            if middle * middle > y:
                x = middle
            elif middle * middle < y:
                flag = middle
            else:
                print('exactly value:', middle)
                break
            num += 1
        else:
            print('deferenct value:', middle)
    elif x == 1:
        print('exactly value:', 1)
    else:
        print('TypeError')

getSquareRoot1(81)
getSquareRoot1(10)

def getSquareRoot(n, deltaThreshold, maxTry):
    if not isinstance(n, int):
        print('TypeError')
    if n <= 1:
        return -1.0
    min, max = 1.0, n
    for i in range(1,maxTry):
        middle = min + (max - min) / 2
        square = middle * middle
        delta = abs((square/n) - 1)
        if delta <= deltaThreshold:
            return middle
        else:
            if square > n:
                max = middle
            else:
                min = middle
    return -2.0

if __name__ == '__main__':
    num = 10
    squareRoot = getSquareRoot(num, 0.0000001, 10000)

    if squareRoot == -1.0:
        print(" please input number greater 1 ")
    elif squareRoot == -2.0:
        print(" cannot find square root ")
    else:
        print("{} square root is {}".format(num, squareRoot));
