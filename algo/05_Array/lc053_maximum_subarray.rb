# frozen_string_literal: true

# @param {Integer[]} nums
# @return {Integer}
def max_sub_array_v1(nums)
  len = nums.size
  ans = -2_147_483_647
  sum = 0
  (0..(len - 1)).each do |i|
    sum += nums[i]
    ans = sum if sum > ans
    sum = 0 if sum.negative?
  end
  ans
end

def max_sub_array(nums)
  pre = cur = nums[0]
  (1...arr.length).each do |i|
    pre = [pre + nums[i], nums[i]].max
    cur = [pre, cur].max
  end
  cur
end

puts max_sub_array([-1, -2])
