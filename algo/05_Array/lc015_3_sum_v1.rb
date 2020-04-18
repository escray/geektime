# frozen_string_literal: true

require 'set'
# @param {Integer[]} nums
# @return {Integer[][]}
def three_sum(nums)
  return [] if nums.size < 3

  nums.sort!
  # puts nums
  result = Set.new

  i = 0

  until i > nums.size - 3
    lo = i + 1
    puts lo
    hi = nums.size - 1
    puts hi
    sum = 0 - nums[i]
    puts sum
    while lo < hi
      if nums[lo] + nums[hi] == sum
        result.add([nums[i], nums[lo], nums[hi]])
        lo += 1 while nums[lo] == nums[lo + 1]
        hi -= 1 while nums[hi] == nums[hi - 1]
        lo += 1
        hi -= 1
      elsif nums[lo] + nums[hi] < sum
        lo += 1
      else
        hi -= 1
      end
    end
    i += 1
  end
  result
end

puts three_sum([-1, 0, 1, 2, -1, -4])
