<template>
  <iframe :src='url' id='show' width='100%' height="780px" frameborder=0 name='showHere' ></iframe>
</template>

<script>
  const EUREKA_SERVER = process.env.EUREKA_SERVER
  export default {
    data(){
        return {
          url: EUREKA_SERVER
        }
    }
}
</script>

