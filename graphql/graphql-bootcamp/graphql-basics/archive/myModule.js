import path from 'path';

// Named exports
export const name = 'Alex'
const location = 'New York'
const message = `A message from ${path.basename(__filename)}`

export { location as default,
         message } // Can be located anywhere

export const getGreeting = (name) => `Hello, ${name}!`