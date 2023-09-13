import {getRequest} from "@/utils/api";

//菜单请求工具类
//初始化菜单
export const initMenu = (router, store)=>{
    if (store.state.routes.length>0){
        return;
    }

    getRequest('').then(data=>{
        if(data){
            let fmtRoutes = formatRoutes(data);
            router.addRoutes(fmtRoutes);
            store.commit('initRoutes', fmtRoutes);
        }
    })
}


export const formatRoutes = (routes) =>{
    let fmtRoutes = [];
    routes.forEach(router=>{
        let{
            path,
            component,
            name,
            iconCls,
            children,
        } = router;
        if(children && children instanceof Array){
            children = formatRoutes(children);
        }
        let fmRouter = {
            path:path,
            name:name,
            iconCls:iconCls,
            children:children,
            component(resolve){
                if (component.startsWith('fac')){
                    require(['../views/Fac_manage/'+component+'.vue'], resolve);
                }else if (component.startsWith('userm')){
                    require(['../views/User_manage/'+component+'.vue'], resolve);
                }else if (component.startsWith('graph')){
                    require(['../views/Statistics/'+component+'.vue'], resolve);
                }else if (component.startsWith('orders')){
                    require(['../views/order_manage/'+component+'.vue'], resolve);
                }
            }
        }
        fmtRoutes.push(fmRouter)
    });
    return fmtRoutes;

}