require 'rubygems'
require 'redis'

def bench(descr)
  start = Time.now
  yield
  puts "#{descr} #{Time.now - start} seconds"
end

def without_pipelining
  r = Redis.new
  10000.times {
    r.ping
  }
end

def with_pipelining
  r = Redis.new
  r.pipelined {
    10000.times {
      r.ping
    }
  }
end

bench("without_pipelining") {
  without_pipelining
}
bench("with_pipelining") {
  with_pipelining
}
