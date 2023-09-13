import axios  from "axios";
import Vue from 'vue'
export function request(config){
  const instance=axios.create({
      baseURL:'http://localhost:8081',
      timeout:5000
})

//拦截器,拦截下来需要再返回，可以自己再配置一些东西，比如token，自己再往配置中添加。再去发送请求
instance.interceptors.request.use(config=>{
  console.log(config); 
  console.log("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
// 带上新的token
config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("token")
  return config},
  err=>{
    console.log(err)})
instance.interceptors.response.use(res=>{
  console.log(res)
  if(res.data.code){
      return res
  }else{
    this.$message.error(res.data.msg);
    return Promise.reject(res.data.msg)
  }
  
},err=>{
  console.log(err.response.data)
  var obj = err.response.data;
  console.log(obj.msg)
  this.$message({
    type: 'success',
    message: obj.msg
  })

  // var str = JSON.parse(obj)
  // console.log(str)
  // alert(obj.msg)
})
return instance(config)
}