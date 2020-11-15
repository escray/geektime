public class SpringDP {

}

// Dependence Inversion in Spring
class Client {
    private UserService userService;

    public setUserService(UserService userService) {
        this.userService = userService;
    }
}

private static void parseElement(Element beanElement) throws Exception {
    String id = beanElement.attributeValue("id");
    String clsName = beanElement.attributeValue("class");
    // 获取 Class 对象
    Class<?> cls = Class.forName(clsName);
    // 直接调用无参构造函数，实例化一个对象
    Object beanObj = cls.getDeclaredConstructor().newInstance();
    beanMap.put(id, beanObj);

    // 获取属性节点，并调用 setter 方法设置属性
    List<Element> subElemList = beanElement.elements();
    for (Element subElem : subElemList) {
        // 获取属性名称
        String name = subElem.attributeValue("name");
        // 获取属性值
        String ref = subElem.attributeValue("ref");
        Object refObj = beanMap.get(ref);
        // 根据属性名称构造 setter 方法名：set + 属性名首字母大写 + 属性其他字符
        // 例如 setUserDao
        String methodName = "set" + (char) (name.charAt(0) - 32) + name.substring(1);
        // 获取 Method 对象
        Method method = cls.getDeclaredMethod(methodName, refObj.getClass().getInterfaces()[0]);
        // 调用 setter 方法，设置对象属性
        method.invoke(beanObj, refObj);
    }
}

// Singleton in Spring
private final Map singletonObjects = new HashMap();

protected Object getSingleton(String beanName) {
    // 检查缓存中是否存在实例
    Object singletonObject = this.singletonObjects.get(beanName);

    if (singletonObject == null) {
        // 如果为空，则锁定全局变量并进行处理
        synchronized (this.singletonObjects) {
            // 调用工厂的 getObject 方法
            singletonObject = singletonFactory.getObject();
            // 记录在缓存中
            this.earlySingletonObjects.put(beanName, singletonObject);
        }
    }
    return (singletonObject != NULL_OBJECT ? singletonObject : null);
}

// MVC in Spring
@RestController
@RequestMapping("/user/")
public class QueryUserController extends FlowerController {
    @Autowired
    OrderNoService orderNoService;

    @RequestMapping(value = "query")
    public void hello(String userid) {
        logger.info("Request Receive: {}", userid);
        doProcess(userid);
    }
}

// Spring DispatcherServlet

    public boolean handle(ServletRequest req, ServletResponse response) {
        String uri = ((HttpServletRequest)req).getRequestURI();
        Object[] parameters = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            // 获取参数
            parameters[i] = req.getParameter(args[i]);
        }

        Object ctl = controller.newInstance(uri);
        Object response = method.invoke(ctl, parameters);
        res.getWriter().println(response.toString());
        return true;
    }
