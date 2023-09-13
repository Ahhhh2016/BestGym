let proxyObj={}

proxyObj['/']={
    ws:false,
    target:'http://localhost:8081',
    changeOrigin:true,
    pathReWrite:{
        '^/':'/'
    }
}





module.exports = {
  devServer:{
      port:8080,
      host:'localhost',
      proxy:proxyObj
  },
}
