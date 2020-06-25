1. springboot 的启动类的run(Application.class) 方法中传递了当前启动类的class对象，在run()方法的执行流程中，调用了一个 reflash(context)方法
2. reflash(context) 方法会去解析springboot启动类中的注解 @SpringBootApplication,  这个注解当中包含一个自动配置类的关键注解@EnableAutoConfiguration
3. @EnableAutoConfiguration 这个注解实现了一个@Inport注解的功能，import 引入了 AutoConfigurationImportSelector这个类
4. AutoConfigurationImportSelector这个类间接实现了ImportSelector 接口,其中有个方法
    selectImports(AnnotationMetadata annotationMetadata) ---> 
    getAutoConfigurationEntry(annotationMetadata) --->
    SpringFactoriesLoader.loadFactoryNames() 
    // 这个方法 loadFactoryNames的作用就是读取Jar包下项目中MATA-INF/spring.factories文件的内容
5. spring.factories 配置了自动装配的类的全限定名称
6. 根据条件给我们完成自动装配Bean
