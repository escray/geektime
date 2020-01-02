var chars = ['a', 'b', 'c', 'd', 'e']
var result = []

function getPassword(passwordChars, num, password) {
  if (num == 0) {
    return result.push(password)
  } else {
    for (var i = 0; i < passwordChars.length; i++) {
      getPassword(passwordChars, num-1, password+passwordChars[i])
    }
  }
}

getPassword(chars, 4, '')
