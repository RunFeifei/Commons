Common
==  
Rxbus(支持Sticky事件)  
----  
https://github.com/RunFeifei/Commons/tree/master/common/src/main/java/com/fei/root/common/Rxbus  
activity or fragemnt需要继承RxlifeStyle,以便自动解除绑定  
注册事件  
BusSubscriber.bind(activity or fragemnt).bindEvent(eventID).onNext((event) -> {}).create( isSticky )  
触发事件  
普通事件:  BusObservable.bind().sendEvent(new BusEvent(eventID, "content"))  
sticky事件:BusObservable.bind().sendStickyEvent(new BusEvent(eventID, "content"))  

#ViewBInder  
----  
https://github.com/RunFeifei/Commons/tree/master/common/src/main/java/com/fei/root/common/viewbinder  
主要针对ButterKnife对module的支持不好,采用反射获取R文件Id值来减少findViewById代码书写  
初始化 ViewBinder.bindViews(实例对象, rootView); 可用于activity,fragment,viewholder...  
@Binder  
private TextView textView;  
点击时间映射使用OnClick注解  
@OnClick(id = R.id.btn)  
private void onClick(View view)
