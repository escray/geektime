import copy

password = 'bacdcd'
classes = ['a', 'b', 'c','d', 'e']

def get_password(n, result = ''):
    if n == 0:
        if result == password:
            print(password)
    else:
        for i in classes:
            new_result = copy.copy(result)
            new_result = new_result + i
            get_password(n-1, new_result)

get_password(6)
