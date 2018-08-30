var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // API_ROOT: '"http://10.103.13.242:8002"',
  // EUREKA_SERVER: '"http://10.103.13.242:8001"',
  API_ROOT: '"http://10.104.102.225:8002"',
  EUREKA_SERVER: '"http://10.104.102.225:8001"',
})
