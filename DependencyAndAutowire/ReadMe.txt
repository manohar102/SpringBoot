In Java/Spring if we create an Object then we have to maintain it.
When we are working with big projects it is difficult to handle all 
the Objects. So we are going to ask the Spring Farework to handle it.

To ask the Spring, we use the getBean method which belong to 
Inteface ApplicationContext.
And we need add @Component on top of Hunter class definition.
This is to let Spring to maintain the Classes on which it need to 
handle Object among all.

Where do we find this Interface?

SpringApplication.run(...) internally immplements the ApplicationContext

So we modified SpringApplication.run() as
	ApplicationContext context = SpringApplication.run(...);
	Hunter hunter = getBeans(Hunter.class);
	hunter.hunt();

Now, we have Hunter but he need Gun to hunt. So we need to Create Gun Object
in Hunter Class. As we discussed if we create Object we need to maintain it so
we are going to ask again Spring to handle it. For that we add @Component on top
of Gun class definition. And add @Autowired on top of the Gun variable declaration
Now we dont need to create a Object also. The Spring will handle everything for us.

@Component                      @Componet
Class Hunter{			class Gun{
	@Autowired			public void shoot(){
	Gun gun;				System.out.println("Shooting...");
	gun.shoot();			}										
}				}