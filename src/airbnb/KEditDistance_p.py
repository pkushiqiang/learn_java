
def getKdistanceWord( word_list, target, k):
#生成trie树
    nodes = {}
    for word in word_list:
        node = nodes
        for i in range(len(word)):
            if word[i] not in node:
                node[word[i]] = {}
            node = node[word[i]]
            if i == len(word)-1:
                node['#'] = 1

    # 构建递归式 prev_dp 为前一列dp结果, w为当前的字母，node 当前字典树中的节点
    def helper(prev_dp, w, node, target, prev_str, result):
        curr_dp = [prev_dp[0] + 1]
        for i in range(len(target)):
            if target[i] == w:
                curr_dp.append(prev_dp[i])
            else:
                min_dis = min(prev_dp[i]+1, curr_dp[i]+1, prev_dp[i+1]+1)
                curr_dp.append(min_dis)
        if curr_dp[-1] <= k and '#' in node:
            result.append(prev_str+w)
        for nxt in node:
            if nxt != '#':
                helper(curr_dp, nxt, node[nxt], target, prev_str+w, result)
    result = []
    for key in nodes:
        # 初始化第一列
        prev_dp = [i for i in range(len(target) + 1)]
        helper(prev_dp, key, nodes[key], target, "", result)

    return result

word_list = ["same",
"dsafedsf",
"sddsfe",
"abdferda",
"a",
"b",
"ade"]

target = "abcd"
k = 3
res = getKdistanceWord( word_list, target, k)
print(res)