import currentLocation, { getGreeting, name, message } from './myModule'
import add, { subtract } from './math'

console.log(getGreeting(name))
console.log(`Your current location is ${currentLocation}`)
console.log(message)

console.log(`5 + 7 - 4 = ${subtract(add(5, 7), 4)}`)