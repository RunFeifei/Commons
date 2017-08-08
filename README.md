##Common  
#Rxbus(支持Sticky事件)  
activity or fragemnt需要继承RxlifeStyle,以便自动解除绑定  
注册事件  
BusSubscriber.bind(activity or fragemnt).bindEvent(eventID).onNext((event) -> {}).create( isSticky )  
触发事件  
普通事件:  BusObservable.bind().sendEvent(new BusEvent(eventID, "content"))  
sticky事件:BusObservable.bind().sendStickyEvent(new BusEvent(eventID, "content"))  
