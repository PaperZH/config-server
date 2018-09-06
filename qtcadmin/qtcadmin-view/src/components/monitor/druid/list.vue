<template>
  <iframe :src='url' id='show' width='100%' height="780px" frameborder=0 name='showHere' ></iframe>
</template>

<script>
  const DRUID_SERVER = process.env.DRUID_SERVER+'/druid'
  export default {
    data(){
        return {
          url: DRUID_SERVER
        }
    }
}
</script>

