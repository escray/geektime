# coding: UTF-8
import copy
'''
参数说明：
all_person： 全体人员列表
sel_person: 可选人员列表
award_rank: 当前正在抽奖的等级
award_result： 各奖项的人员列表
award_psn_num：各奖项的人数列表
'''
def assemble(all_person, sel_person, award_rank, award_result, award_psn_num):
    global schema_num
    global schema_limit

    #超出组合限制，则不再作组合运算
    if schema_limit > 0 and schema_num > schema_limit:
        return

    #输出结果
    if sum(award_psn_num) == 0:
        schema_num += 1
        print('-'*10, '方案'+str(schema_num), '-'*10)
        for i in range(len(award_result)):
            print('{}等奖：{}'.format(i+1,','.join(award_result[i])))
        print('-'*30)
        print()
        return

    #挑选当前奖项的人员
    i = award_rank - 1

    if i >= 0 and award_psn_num[i] > 0:
        new_award_psn_num = award_psn_num.copy()
        new_award_psn_num[i] -= 1
        for j in range(len(sel_person)):
            new_award_result = copy.deepcopy(award_result)
            new_award_result[i].append(sel_person[j])
            new_sel_person = sel_person[j+1:].copy()
            assemble(all_person, new_sel_person, award_rank, new_award_result, new_award_psn_num)
        pass
    else:
        #初始化下一奖项的可选人员
        if i > 0 and len(award_result[i-1]) == 0:
            new_sel_person = all_person.copy()

            award_psn = []
            for result in award_result:
                award_psn += result

            for psn in award_psn:
                new_sel_person.remove(psn)
            #抽取下一奖项人员
            assemble(all_person, new_sel_person, award_rank-1, award_result, award_psn_num)


def main():
    #总人数
    person_num = 100
    #三等奖人数
    award3_num = 10
    #二等奖人数
    award2_num = 3
    #一等奖人数
    award1_num = 1
    person_list = ['P'+str(i) for i in range(person_num)]
    assemble(person_list, person_list.copy(), 3, [[],[],[]],[award1_num, award2_num, award3_num])

if __name__ == '__main__':
    schema_num = 0
    #限制组合的次数
    schema_limit = 10
    main()
