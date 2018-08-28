'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API_ROOT: '"http://10.103.13.242:8002"',
  EUREKA_SERVER: '"http://10.103.13.242:8001"'
})
