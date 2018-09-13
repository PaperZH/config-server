<template>
  <iframe :src='url' id='show' width='100%' height="780px" frameborder=0 name='showHere' ></iframe>
</template>

<script>
  const API_SERVER = process.env.API_ROOT
  export default {
    data(){
        return {
          url: API_SERVER + "/swagger-ui.html"
        }
    }
}
</script>

