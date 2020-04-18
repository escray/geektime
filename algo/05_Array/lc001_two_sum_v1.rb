# frozen_string_literal: true

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum_v1(nums, target)
  nlength = nums.length
  (0..(nlength - 1)).each do |i|
    (0..(nlength - 1)).each do |j|
      return [i, j] if nums[i] + nums[j] == target
    end
  end
end

def two_sum_v2(nums, target)
  (0..(nlength - 1)).each do |i|
    return i, nums.index(target - nums[i]) if nums.include?(target - nums[i])
  end
end

def two_sum_v3(nums, target)
  (0..(nums.length -1)).each do |i|
    num = nums[i]
    remainder = target - num
    next unless nums[(i + 1)..-1].include?(remainder)

    j = nums.index(remainder)
    j = nums.rindex(remainder) if i == j
    return [i, j]
  end
end

def two_sum_copy(nums, target)
  dict = {}
  nums.each_with_index do |n, i|
    return dict[target - n], i if dict[target - n]

    dict[n] = i
  end
end

def two_sum(nums, target)
  h = {}
  nums.each_with_index.reduce(nil) do |_, (n, i)|
    break h[target - n], i if h[target - n]

    h[n] = i
  end
end
