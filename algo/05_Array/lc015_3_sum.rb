# frozen_string_literal: true

# @param {Integer[]} nums
# @return {Integer[][]}

def three_sum(nums)
  return [] if nums.size < 3

  nums.sort!
  length = nums.size
  result = []
  (0..length - 3).each do |i|
    next unless i.zero? || nums[i] != nums[i - 1]

    lo = i + 1
    hi = length - 1
    while lo < hi
      sum = nums[i] + nums[lo] + nums[hi]
      result.push([nums[i], nums[lo], nums[hi]]) if sum.zero?
      if sum <= 0
        lo += 1 while nums[lo] == nums[lo + 1]
        lo += 1
      end
      if sum >= 0
        hi -= 1 while nums[hi] == nums[hi - 1]
        hi -= 1
      end
    end
  end
  result
end
