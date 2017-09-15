'''
a,b = map(int, input().split())

x = []
for i in range(a):
    x.append([0] * b)

l = []
for i in range(a):
    l.append(['.'] * b)

for i in range(a):
    for j in range(b):
        x[i][j] = input()
        if x[i][j] == 'X':
            l[j][i] = 'X'

for i in range(a):
    for i in range(b):
        print(l[i][j], end = ' ')
    print()
'''
'''
string = ' abcdefghijklmnopqrstuvwxyz'
a = int(input())
flag = False
if a < 0:
    flag = True
a = abs(a) % len(string)
string1 = string
if flag:
    string1 = string[len(string)- a:] + string[:len(string)-a]
else:
    string1 = string[a:] + string[:a]

result = ''
for i in input().strip():
    result += string1[string.index(i)]
print('Result: "', result, '"', sep='')
'''
'''
ls = [int(i) for i in input().split('/')]
result = []
result.append(ls[0] // ls[1])
result.append(ls[1] // (ls[0] % ls[1]))
result.append(ls[0] % ls[1])
for i in result:
    print(i, end = ' ')
'''
